/*
 * Copyright (C) 2000 - 2012 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.quota.service;

import javax.inject.Inject;

import org.silverpeas.quota.QuotaKey;
import org.silverpeas.quota.contant.QuotaLoad;
import org.silverpeas.quota.exception.QuotaException;
import org.silverpeas.quota.exception.QuotaFullException;
import org.silverpeas.quota.exception.QuotaNotEnoughException;
import org.silverpeas.quota.exception.QuotaOutOfBoundsException;
import org.silverpeas.quota.model.Quota;
import org.silverpeas.quota.repository.QuotaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yohann Chastagnier
 */
@Transactional
public abstract class AbstractQuotaService<T extends QuotaKey> implements QuotaService<T> {

  @Inject
  private QuotaRepository quotaRepository;

  /*
   * (non-Javadoc)
   * @see org.silverpeas.quota.service.QuotaService#initialize(org.silverpeas.quota.QuotaKey, int)
   */
  @Override
  public Quota initialize(final T key, final int maxCount) throws QuotaException {
    return initialize(key, 0, maxCount);
  }

  /*
   * (non-Javadoc)
   * @see org.silverpeas.quota.service.QuotaService#initialize(org.silverpeas.quota.QuotaKey, int,
   * int)
   */
  @Override
  public Quota initialize(final T key, final int minCount, final int maxCount)
      throws QuotaException {

    // Checking that it does not exist a quota with same key
    Quota quota = getByQuotaKey(key);
    if (quota == null) {
      // Initializing the quota
      quota = new Quota();
      quota.setType(key.getQuotaType());
      quota.setResourceId(key.getResourceId());
    }

    // Setting the quota
    quota.setMinCount(minCount);
    quota.setMaxCount(maxCount);

    // Validating
    quota.validate();

    // Saving
    quotaRepository.saveAndFlush(quota);

    // Returning the initialized quota
    return quota;
  }

  /*
   * (non-Javadoc)
   * @see org.silverpeas.quota.service.QuotaService#get(org.silverpeas.quota.QuotaKey)
   */
  @Override
  public Quota get(final T key) throws QuotaException {
    final Quota quota = getByQuotaKey(key);
    if (quota != null) {
      final int currentCount = getCurrentCount(key);
      if (quota.getCount() != currentCount) {
        quota.setCount(currentCount);
        quotaRepository.saveAndFlush(quota);
      }
    }
    return quota;
  }

  /**
   * Private method to retrieve a Quota
   * @param key
   * @return
   */
  private Quota getByQuotaKey(final T key) {
    return quotaRepository.getByTypeAndResourceId(key.getQuotaType().name(), key.getResourceId());
  }

  /*
   * (non-Javadoc)
   * @see org.silverpeas.quota.service.QuotaService#verify(org.silverpeas.quota.QuotaKey)
   */
  @Override
  public Quota verify(final T key) throws QuotaException {
    final Quota quota = get(key);
    if (quota != null) {
      final QuotaLoad quotaLoad = quota.getLoad();
      if (QuotaLoad.OUT_OF_BOUNDS.equals(quotaLoad)) {
        throw new QuotaOutOfBoundsException(quota);
      } else if (QuotaLoad.FULL.equals(quotaLoad)) {
        throw new QuotaFullException(quota);
      } else if (QuotaLoad.NOT_ENOUGH.equals(quotaLoad)) {
        throw new QuotaNotEnoughException(quota);
      }
    }

    // Returning the quota used by this verify process
    return quota;
  }

  /*
   * (non-Javadoc)
   * @see org.silverpeas.quota.service.QuotaService#remove(org.silverpeas.quota.QuotaKey)
   */
  @Override
  public void remove(final T key) {
    quotaRepository.delete(getByQuotaKey(key));
  }
}
