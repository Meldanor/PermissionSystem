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

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.junit.Test;

public class BasicRoleTest {

    @Test
    public void createBigRole() {
        try {
            File f = new File("src/test/resources/permissions.txt");
            List<String> lines = Files.readAllLines(f.toPath(), Charset.defaultCharset());

            Role adminRole = new Role("Admin");
            for (String line : lines) {
                adminRole.grantPermission(line);
            }

            for (String line : lines) {
                assertTrue(adminRole.hasPermission(line));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
