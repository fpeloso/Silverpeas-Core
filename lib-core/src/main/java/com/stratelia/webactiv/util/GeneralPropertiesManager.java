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

package com.stratelia.webactiv.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author Norbert CHAIX
 * @version
 */
public class GeneralPropertiesManager {

  public static final int DVIS_ALL = 0;
  public static final int DVIS_ONE = 1;
  public static final int DVIS_EACH = 2;
  public static final String GENERAL_PROPERTIES_FILE = "org.silverpeas.multilang.generalMultilang";
  static final ResourceLocator generalProperties = new ResourceLocator("org.silverpeas.general", "");
  static int dvis = Integer.parseInt(generalProperties.getString("domainVisibility", "0"));
  static final Map<String, Collection<String>> listProperties = new HashMap<String, Collection<String>>();


  /**
   * You can access the data directly.
   * @return
   * @deprecated
   */
  @Deprecated
  public static ResourceLocator getGeneralResourceLocator() {
    return generalProperties;
  }

  public static int getInteger(String property, int defaultValue) {
    return generalProperties.getInteger(property, defaultValue);
  }

  public static String getString(String property, String defaultValue) {
    return generalProperties.getString(property, defaultValue);
  }

  public static String getString(String property) {
    return generalProperties.getString(property);
  }

  public static boolean getBoolean(String property, boolean defaultValue) {
    return generalProperties.getBoolean(property, defaultValue);
  }

  public static Collection<String> getStringCollection(String property) {
    return getStringCollection(property,"[ ,;]");
  }

  public static Collection<String> getStringCollection(String property, String regexValueSeparator) {
    Collection<String> propertyValues = listProperties.get(property);
    if (propertyValues == null) {
      propertyValues = new LinkedHashSet<String>();
      listProperties.put(property, propertyValues);
      final String stringValues = generalProperties.getString(property, null);
      if (stringValues != null && !"".equals(stringValues.trim())) {
        for (String value : stringValues.split(regexValueSeparator)) {
          if (value != null && !"".equals(value.trim())) {
            propertyValues.add(value.trim());
          }
        }
      }
    }
    return propertyValues;
  }

  public static int getDomainVisibility() {
    return dvis;
  }

  public static ResourceLocator getGeneralMultilang(String language) {
    return new ResourceLocator(GENERAL_PROPERTIES_FILE, language);
  }

  private GeneralPropertiesManager() {
  }
}
