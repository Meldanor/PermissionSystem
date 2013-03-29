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
        this(name, new PermissionTree());
    }

    public Role(String name, PermissionTree permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public Role(String name, Role parentRole) {
        this(name, new PermissionTree());
        PermissionTree parentTree = parentRole.permissions;
        this.permissions = parentTree.copy();
    }

    public Role(String name, Role parentRole, PermissionTree additionalPermission) {
        this(name, parentRole);
        this.permissions = additionalPermission.copyAndAdd(permissions);
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
