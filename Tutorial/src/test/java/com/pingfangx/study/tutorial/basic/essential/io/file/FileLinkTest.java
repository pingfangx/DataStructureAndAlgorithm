package com.pingfangx.study.tutorial.basic.essential.io.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pingfangx
 * @date 2019/10/9
 */
public class FileLinkTest {
    Path folder = Paths.get("D:\\workspace\\Test\\java");

    /**
     * 创建的快捷方式不是符号链接
     * 通过 createSymbolicLink 创建的是符号链接
     */
    @Test
    public void test_isSymbolicLink() {
        //windows 创建的快捷方式不是
        Assert.assertFalse(Files.isSymbolicLink(folder.resolve("test.txt")));
        Assert.assertFalse(Files.isSymbolicLink(folder.resolve("test.txt.lnk")));
        // createSymbolicLink 创建的是符号链接
        Assert.assertTrue(Files.isSymbolicLink(folder.resolve("test.txt.symlink")));
        // createLink 创建的不是符号链接
        Assert.assertFalse(Files.isSymbolicLink(folder.resolve("test.txt.link")));
    }

    /**
     * 创建后是 .symlink 类型
     */
    @Test
    public void test_createSymbolicLink() {
        Path newLink = folder.resolve("test.txt.symlink");
        Path target = folder.resolve("test.txt");
        try {
            Files.createSymbolicLink(newLink, target);
        } catch (IOException | UnsupportedOperationException x) {
            System.err.println(x);
        }
    }

    /**
     * 硬链接文件必须存在
     */
    @Test
    public void test_createLink() {
        Path newLink = folder.resolve("test.txt.link");
        Path target = folder.resolve("test.txt");
        try {
            Files.createLink(newLink, target);
        } catch (IOException | UnsupportedOperationException x) {
            System.err.println(x);
        }
    }
}
