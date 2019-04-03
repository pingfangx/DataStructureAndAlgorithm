package com.pingfangx.study.tutorial.essential_java_classes.exceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author pingfangx
 * @date 2019/3/7
 */
public class TryWithResourcesTest {
    private void test1(FileReader fileReader) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(fileReader);
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void test2(FileReader fileReader) {
        try (BufferedReader br = new BufferedReader(fileReader)) {
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
