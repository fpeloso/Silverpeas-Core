/**
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
 * FLOSS exception.  You should have received a copy of the text describing
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

package com.stratelia.silverpeas.pdc.model;

import com.silverpeas.util.i18n.Translation;

/**
 * This class contains headers of axis. And uses the persistence class for the DAO. The user can
 * access to the axis main information.
 */
public class AxisHeaderI18N extends Translation implements java.io.Serializable {

  // Class version identifier
  private static final long serialVersionUID = -1418233065462620219L;
  private String name = null;
  private String description = null;

  public AxisHeaderI18N() {
  }

  public AxisHeaderI18N(int axisId, String lang, String name, String description) {
    if (lang != null)
      super.setLanguage(lang);
    setObjectId(Integer.toString(axisId));
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}