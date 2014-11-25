/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chillenious.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Tests for {@link Files}
 */
public class FilesTest extends Assert {

    /**
     * Tests for {@link Files#remove(java.io.File)}
     *
     * @throws java.io.IOException
     */
    @Test
    public void remove() throws IOException {
        assertFalse("'null' files are not deleted.", Files.remove(null));

        assertFalse("Non existing files are not deleted.", Files.remove(new File(
                "/somethingThatDoesntExistsOnMostMachines-111111111111111111111111111111")));

        File file = File.createTempFile("wicket-test--", ".tmp");
        assertTrue("The just created file should exist!", file.isFile());

        boolean removed = Files.remove(file);
        assertFalse("The just removed file should not exist!", file.exists());
        assertTrue("Files.remove(file) should remove the file", removed);

        // try to remove non-existing file
        removed = Files.remove(file);
        assertFalse("The just removed file should not exist!", file.exists());
        assertFalse("Files.remove(file) should not remove the file", removed);

        // try to remove a folder
        File folder = new File(System.getProperty("java.io.tmpdir"), "wicket-test-folder");
        Files.mkdirs(folder);
        assertTrue(folder.isDirectory());
        assertFalse("Should not be able to delete a folder, even empty one.", Files.remove(folder));
        assertTrue("Should not be able to delete a folder.", Files.removeFolder(folder));
    }

    /**
     * Tests for {@link Files#removeFolder(java.io.File)}
     *
     * @throws Exception
     */
    @Test
    public void removeFolder() throws Exception {
        assertFalse("'null' folders are not deleted.", Files.removeFolder(null));

        assertFalse("Non existing folders are not deleted.", Files.removeFolder(new File(
                "/somethingThatDoesntExistsOnMostMachines-111111111111111111111111111111")));

        File folder = new File(System.getProperty("java.io.tmpdir"), "wicket-test-folder");
        Files.mkdirs(folder);
        assertTrue(folder.isDirectory());
        File file = new File(folder, "child");
        file.createNewFile();
        assertTrue(file.exists());

        assertTrue("Should be able to delete a folder.", Files.removeFolder(folder));
    }

    @Test
    public void fileWithWhitespace() throws Exception {
        URL url = new URL("file:/file%20with%20whitespace");

        assertEquals(File.separator + "file with whitespace", Files.getLocalFileFromUrl(url).getPath());
    }
}
