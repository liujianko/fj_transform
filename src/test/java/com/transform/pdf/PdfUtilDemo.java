package com.transform.pdf;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PdfUtilDemo
 * @Description TODO
 * @Author liujianko
 * @Date 2019/9/9
 **/
public class PdfUtilDemo {

    @Test
    public void test1(){
        String filePath1 = "E:/fj_transform/有签章的.pdf";
        String filePath2 = "E:/fj_transform/word/from2.doc";
        boolean ret = PdfUtil.check(filePath1);
        assert(ret == true);
    }

    @Test
    public void test2(){
        String filePath1 = "E:/fj_transform/有签章的.pdf";
        String filePath2 = "E:/fj_transform/word/from2.doc";
        int ret = PdfUtil.getPdfPageNum(filePath1);
        System.out.println(ret);
    }

    @Test
    public void test3(){
        List<String> list = new ArrayList<>();
        list.add("E:/fj_transform/pdf/pdfhb/test1.pdf");
        list.add("E:/fj_transform/pdf/pdfhb/test2.pdf");
        list.add("E:/fj_transform/pdf/pdfhb/test3.pdf");

        PdfUtil.pdfHb(list,"E:/fj_transform/pdf/pdfhb/testHb.pdf");
    }

    @Test
    public void test4(){
        String filePath = "E:/fj_transform/pdf/pdfsplit/from.pdf";
        String savePath = "E:/fj_transform/pdf/pdfsplit/to.pdf";

        PdfUtil.pdfSplit(filePath,savePath,1,2);
    }

    @Test
    public void test5(){
        String filePath = "E:/fj_transform/pdf/pdfcf/test.pdf";
        String savePath = "E:/fj_transform/pdf/pdfcf/save.pdf";

        PdfUtil.PdfSplit2(filePath,3);
    }

    @Test
    public void test6(){
        String filePath = "E:/fj_transform/pdf/pdfsy/test.pdf";
        String savePath = "E:/fj_transform/pdf/pdfsy/save.pdf";
        PdfUtil.addSYText(filePath,savePath,"1","2","3");
    }
}
