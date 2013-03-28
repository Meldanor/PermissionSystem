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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class GeneralTest {

    @Test
    public void test() {
        PermissionTree tree = new PermissionTree();
        tree.put("de");
        tree.put("de.meldanor");
        tree.put("de.minestar");
        tree.put("de.meldanor.test");
        tree.put("com.minestar");
        System.out.println(toStringTree(tree));
    }

    // Start of
    // © Connor Garvey at January 30th, 2009.
    public static String toStringTree(PermissionTree node) {
        final StringBuilder buffer = new StringBuilder();
        return toStringTreeHelper(node, buffer, new LinkedList<Iterator<PermissionTree>>()).toString();
    }

    private static String toStringTreeDrawLines(List<Iterator<PermissionTree>> parentIterators, boolean amLast) {
        StringBuilder result = new StringBuilder();
        Iterator<Iterator<PermissionTree>> it = parentIterators.iterator();
        while (it.hasNext()) {
            Iterator<PermissionTree> anIt = it.next();
            if (anIt.hasNext() || (!it.hasNext() && amLast)) {
                result.append("   |");
            } else {
                result.append("    ");
            }
        }
        return result.toString();
    }

    private static StringBuilder toStringTreeHelper(PermissionTree node, StringBuilder buffer, List<Iterator<PermissionTree>> parentIterators) {
        if (!parentIterators.isEmpty()) {
            boolean amLast = !parentIterators.get(parentIterators.size() - 1).hasNext();
            buffer.append("\n");
            String lines = toStringTreeDrawLines(parentIterators, amLast);
            buffer.append(lines);
            buffer.append("\n");
            buffer.append(lines);
            buffer.append("- ");
        }
        buffer.append(node.toString());
        if (node.getChilds() != null) {
            Iterator<PermissionTree> it = node.getChilds().iterator();
            parentIterators.add(it);
            while (it.hasNext()) {
                PermissionTree child = it.next();
                toStringTreeHelper(child, buffer, parentIterators);
            }
            parentIterators.remove(it);
        }
        return buffer;
    }
    // End of
    // © Connor Garvey at January 30th, 2009.

}
