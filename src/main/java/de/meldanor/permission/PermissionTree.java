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

import java.util.Collections;
import java.util.List;

public class PermissionTree implements Comparable<PermissionTree> {

    private PermissionTree root;
    private List<PermissionTree> childs;

    private String node;

    public PermissionTree() {
        this(null, null);
    }

    public PermissionTree(String node) {
        this(null, node);
    }

    public PermissionTree(PermissionTree root, String node) {
        this.root = root;
        this.node = node;
    }

    public PermissionTree getRoot() {
        return root;
    }

    public List<PermissionTree> getChilds() {
        return childs;
    }

    public String getNode() {
        return node;
    }

    private PermissionTree add(String node) {
        if (childs == null)
            childs = new SortedList<PermissionTree>();
        if (node.equals("*")) {
            childs.clear();
        }
        if (Collections.binarySearch(childs, new PermissionTree("*")) >= 0)
            return this;
        PermissionTree newNode = new PermissionTree(this, node);

        if (Collections.binarySearch(childs, newNode) >= 0)
            return this;
        else {
            childs.add(newNode);
            return newNode;
        }
    }

    public void put(String node) {
        // Split at first fullstop
        int pointIndex = node.indexOf('.');
        // Node is a leaf - insert at this tree
        if (pointIndex == -1)
            add(node);
        else {
            // Split node at first fullstop
            // Prefix
            String prefix = node.substring(0, pointIndex);
            String suffix = node.substring(pointIndex + 1);
            PermissionTree childNode = null;
            if (childs == null) {
                childNode = add(prefix);

            } else {
                int i = Collections.binarySearch(childs, new PermissionTree(prefix));
                if (i < 0)
                    childNode = add(prefix);
                else
                    childNode = childs.get(i);
            }
            childNode.put(suffix);
        }
    }

    public boolean hasPermission(String node) {

        if (childs == null)
            return false;
        int pointIndex = node.indexOf('.');
        if (pointIndex == -1) {
            if (childs.size() == 1 && childs.get(0).getNode().equals("*")) {
                return true;
            }
            // Node must be a child of this subtree
            return Collections.binarySearch(childs, new PermissionTree(node)) >= 0;
        } else {
            // Node must be a child of a subtree of this subtree

            // Split the node at the first .
            String prefix = node.substring(0, pointIndex);
            String suffix = node.substring(pointIndex + 1);
            if (childs.size() == 1 && childs.get(0).getNode().equals("*")) {
                return true;
            }

            // Search for possible subtree with the node
            int i = Collections.binarySearch(childs, new PermissionTree(prefix));
            return i < 0 ? false : childs.get(i).hasPermission(suffix);
        }
    }

    @Override
    public String toString() {
        return node;
    }

    public int compareTo(PermissionTree other) {
        return this.node.compareTo(other.node);
    }

}
