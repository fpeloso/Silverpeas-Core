#
# Copyright (C) 2000 - 2011 Silverpeas
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as
# published by the Free Software Foundation, either version 3 of the
# License, or (at your option) any later version.
#
# As a special exception to the terms and conditions of version 3.0 of
# the GPL, you may redistribute this Program in connection with Free/Libre
# Open Source Software ("FLOSS") applications as described in Silverpeas's
# FLOSS exception.  You should have recieved a copy of the text describing
# the FLOSS exception, and it is also available here:
# "http://repository.silverpeas.com/legal/licensing"
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
#
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#

# SQL driver
# DataBase Access
# ----------------
database.SQLClassName          = $SQLClassName$
database.SQLJDBCUrl            = $SQLJDBCUrl$
database.SQLAccessLogin        = $SQLAccessLogin$
database.SQLAccessPasswd       = $SQLAccessPasswd$

database.SQLUserTableName      = $SQLUserTableName$
database.SQLGroupTableName     = $SQLGroupTableName$
database.SQLUserGroupTableName = $SQLUserGroupTableName$

# Generic Properties
# ------------------

# For Users
database.SQLUserSpecificIdColumnName        = id
database.SQLUserLoginColumnName             = login
database.SQLUserFirstNameColumnName         = firstName
database.SQLUserLastNameColumnName          = lastName
database.SQLUserEMailColumnName             = email
database.SQLUserPasswordColumnName          = password
database.SQLUserPasswordValidColumnName     = passwordValid

# For Groups
database.SQLGroupSpecificIdColumnName       = id
database.SQLGroupNameColumnName             = name
database.SQLGroupDescriptionColumnName      = description
database.SQLGroupParentIdColumnName         = superGroupId

# For Users-Groups relations
database.SQLUserGroupUIDColumnName          = userId
database.SQLUserGroupGIDColumnName          = groupId

# Database encryption
# -------------------
# Available values : ClearText, CryptUnix, CryptMd5
database.SQLPasswordEncryption = $SQLPasswordEncryption$


# Users synchronisation
# -------------------

# Available values : true, false

ExternalSynchro = false
ExternalSynchroClass =


# Specific Properties
# -------------------

# Available types : STRING, BOOLEAN, USERID
# Property number : from 1 to N

property.Number = 9
property.ResourceFile = com.stratelia.silverpeas.domains.multilang.templateDomainSQLBundle

property_1.Name = title
property_1.Type = STRING
property_1.MapParameter = title

property_2.Name = company
property_2.Type = STRING
property_2.MapParameter = company

property_3.Name = position
property_3.Type = STRING
property_3.MapParameter = position

property_4.Name = boss
property_4.Type = USERID
property_4.MapParameter = boss

property_5.Name = phone
property_5.Type = STRING
property_5.MapParameter = phone

property_6.Name = homePhone
property_6.Type = STRING
property_6.MapParameter = homePhone

property_7.Name = fax
property_7.Type = STRING
property_7.MapParameter = fax

property_8.Name = cellularPhone
property_8.Type = STRING
property_8.MapParameter = cellPhone

property_9.Name = address
property_9.Type = STRING
property_9.MapParameter = address

property.Grouping =