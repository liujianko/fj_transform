package com.transform.pdf;

public class Pdf2ImgUtilDemo {

    public static void main(String[] args) {
        String root = "E:";
        String sourcePath = "/fj_transform/pdf/pdf2Img/pdf2img.pdf";
        String result = Pdf2ImgUtil.pdf2Img(root, sourcePath);
        System.out.println(result);
    }
}
