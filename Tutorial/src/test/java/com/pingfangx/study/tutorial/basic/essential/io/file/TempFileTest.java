package com.pingfangx.study.tutorial.basic.essential.io.file;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pingfangx
 * @date 2019/10/9
 */
public class TempFileTest {
    @Test
    public void test_createTempFile() throws IOException {
        Path tempFile = Files.createTempFile("prefix", ".suffix");
        System.out.println(tempFile);
        Path tempFile2 = Files.createTempFile(Paths.get("D:\\workspace\\Test\\java"), "prefix", ".suffix");
        System.out.println(tempFile2);
    }
}
