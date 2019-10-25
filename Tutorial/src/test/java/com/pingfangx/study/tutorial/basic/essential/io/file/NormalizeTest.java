package com.pingfangx.study.tutorial.basic.essential.io.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pingfangx
 * @date 2019/10/9
 */
public class NormalizeTest {
    @Test
    public void test_createSymbolicLink() {
        Path folder = Paths.get("D:\\workspace\\Test\\test.txt");
        Path link = Paths.get("D:\\workspace\\Test\\java\\test.txt");
        try {
            Files.createSymbolicLink(link, folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_normalize() throws IOException {
        Path path = Paths.get("D:\\workspace\\Test\\java\\test.txt\\..\\");
        //未检查链接
        Assert.assertEquals("D:\\workspace\\Test\\java", path.normalize().toString());
        Assert.assertEquals("D:\\workspace\\Test\\java", path.toRealPath().toString());
        //这里与文档不一致
        //如果将 true 传递给此方法并且文件系统支持符号链接，则此方法将解析路径中的所有符号链接。
        Assert.assertEquals("D:\\workspace\\Test\\java", path.toRealPath(LinkOption.NOFOLLOW_LINKS).toString());
    }
}
