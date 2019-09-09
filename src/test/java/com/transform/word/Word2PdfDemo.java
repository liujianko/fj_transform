package com.transform.word;

/**
 * @ClassName Word2PdfDemo
 * @Description TODO
 * @Author liujianko
 * @Date 2019/9/2
 **/
public class Word2PdfDemo {

    public static void main(String[] args) {
        String fromFile = "E:/fj_transform/word/from2.doc";
        String toFile = "E:/fj_transform/word/to2.pdf";
        Word2PdfUtil.word2Pdf(fromFile, toFile);
    }
}
