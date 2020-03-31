package com.transform.word;

/**
 * @ClassName Word2PdfUtil2
 * @Description TODO
 * @Author liujianko
 * @Date 2020/3/31
 **/
public class Word2PdfUtil2 {

    //将Word文档直接转换成PDF
//    public static void test1(String[] args) {
//
//        //加载Word示例文档
//        Document document = new Document();
//        document.loadFromFile("C:\\Users\\Test1\\Desktop\\Sample.docx");
//
//        //保存结果文档
//        document.saveToFile("output/toPDF", FileFormat.PDF);
//    }

    //将Word文档转换成加密的PDF文档
//    public static void test2(String[] args) {
//
//        //加载Word示例文档
//        Document document = new Document();
//        document.loadFromFile("C:\\Users\\Test1\\Desktop\\Sample.docx");
//
//        //创建一个参数
//        ToPdfParameterList toPdf = new ToPdfParameterList();
//
//        //设置密码
//        String password = "abc123";
//        toPdf.getPdfSecurity().encrypt(password, password, PdfPermissionsFlags.None, PdfEncryptionKeySize.Key_128_Bit);
//
//        //保存文档.
//        document.saveToFile("output/toPDFWithPassword", toPdf);
//    }
}
