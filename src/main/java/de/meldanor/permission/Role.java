/*
 * Copyright (C) 2013 Kilian Gaertner
 * 
 * This file is part of PermissionSystem.
 * 
 * PermissionSystem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * PermissionSystem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PermissionSystem.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.meldanor.permission;

import de.meldanor.permission.datastructure.PermissionTree;

public class Role {

    private final String name;

    private PermissionTree permissions;

    public Role(String name) {
        this.name = name;
        this.permissions = new PermissionTree();
    }

    public String getName() {
        return name;
    }

    public boolean hasPermission(String permissionNode) {
        return permissions.hasNode(permissionNode);
    }

    public void grantPermission(String permissionNode) {
        permissions.addNode(permissionNode);
    }

    public void revokePermission(String permissionNode) {
        permissions.removeNode(permissionNode);
    }

}
