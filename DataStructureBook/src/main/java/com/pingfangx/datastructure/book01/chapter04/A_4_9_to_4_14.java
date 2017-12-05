package com.pingfangx.datastructure.book01.chapter04;


import com.pingfangx.datastructure.common.util.LogUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2017/12/4
 */
public class A_4_9_to_4_14 {
    private List<KeyWordItem> keyWordItemList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new A_4_9_to_4_14().doMain();
    }

    public void doMain() throws IOException {
        createFile();
        FileReader fileReader = new FileReader("D:\\BookInfo.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            int bookNo = getBookNo(line);
            List<String> keywordList = extractKeyWord(line);
            insIdxList(keywordList, bookNo);
            line = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();
        FileWriter fileWriter = new FileWriter("D:\\BookIdx.txt");
        for (KeyWordItem keyWordItem : keyWordItemList) {
            fileWriter.write(keyWordItem.toString() + "\n");
            LogUtils.d(keyWordItem);
        }
        fileWriter.close();
    }

    private void createFile() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("010 Introduction to Data Structures");
        lines.add("005 Computer Data Structures");
        lines.add("023 Fundamentals of Data Structures");
        lines.add("034 The Design and Analysis of Computer Algorithms");
        lines.add("050 Introduction to Numerical Analysis");
        lines.add("067 Numerical Analysis");
        FileWriter fileWriter = new FileWriter("D:\\BookInfo.txt");
        for (String line : lines) {
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }

    private void insIdxList(List<String> keywordList, int bookNo) {
        for (int i = 0; i < keywordList.size(); i++) {
            String keyword = getWord(keywordList, i);
            if (!contain(keyword)) {
                //添加关键字
                insertNewKeyword(locate(keyword), keyword);
            }
            //插入书号，此时肯定有关键字
            insertBook(locate(keyword), bookNo);
        }
    }

    /**
     * A_4_11
     * 关键字中的第 i 个
     */
    private String getWord(List<String> list, int i) {
        return list.get(i);
    }


    /**
     * 是否含有关键字，和下面的 locate 方法，一起对应 A_4_12
     */
    private boolean contain(String keyword) {
        for (KeyWordItem keyWordItem : keyWordItemList) {
            if (keyWordItem.keyword.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    private int locate(String keyword) {
        for (int i = keyWordItemList.size() - 1; i >= 0; i--) {
            int m = keyWordItemList.get(i).keyword.compareTo(keyword);
            if (m == 0) {
                return i;
            } else if (m < 0) {
                //按顺序
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * A_4_13
     * 插入新的关键字
     */
    private void insertNewKeyword(int i, String keyword) {
        //添加一个
        if (keyWordItemList == null || keyWordItemList.isEmpty()) {
            keyWordItemList = new ArrayList<>();
            keyWordItemList.add(new KeyWordItem());
        } else {
            keyWordItemList.add(null);
        }
        //后移，可以用 insert 的
        for (int j = keyWordItemList.size() - 2; j >= i; j--) {
            keyWordItemList.set(j + 1, keyWordItemList.get(j));
        }
        //赋值
        KeyWordItem keyWordItem = new KeyWordItem();
        keyWordItem.keyword = keyword;
        keyWordItem.bookNoList = new ArrayList<>();
        keyWordItemList.set(i, keyWordItem);
    }

    /**
     * A_4_14
     * 插入新的书号
     */
    private void insertBook(int i, int bookNo) {
        keyWordItemList.get(i).bookNoList.add(bookNo);
    }


    /**
     * 获取书号，与下面的 extractKeyWord 分开
     */
    private static int getBookNo(String line) {
        return Integer.parseInt(line.split(" ", 2)[0]);
    }

    private static List<String> extractKeyWord(String line) {
        String[] keywordArray = line.split(" ", 2)[1].split(" ");
        String[] ignoreArray = new String[]{"to", "of", "and", "the"};
        List<String> keywordList = new ArrayList<>();
        for (String keyword : keywordArray) {
            boolean ignore = false;
            for (String ignoreWord : ignoreArray) {
                if (ignoreWord.equalsIgnoreCase(keyword)) {
                    ignore = true;
                    break;
                }
            }
            if (!ignore) {
                keywordList.add(keyword.toLowerCase());
            }
        }
        return keywordList;
    }

    class KeyWordItem {
        public String keyword;
        public List<Integer> bookNoList;

        @Override
        public String toString() {
            return keyword + bookNoList.toString();
        }
    }
}
