package com.transform.word;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;

/**
 * @ClassName Word2PdfUtil
 * @Description word 文档转成 pdf 格式
 * @Author liujianko
 * @Date 2019/9/2
 **/
public class Word2PdfUtil {

    //PDF 格式
    private static final int wdFormatPDF = 17;

    public static void word2Pdf(String formFile, String toFile){
        System.out.println("启动 Word...");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            ComThread.InitSTA();
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();
            doc = Dispatch.call(docs, "Open", formFile, false, true).toDispatch();
            System.out.println("打开文档..." + formFile);
            System.out.println("转换文档到 PDF..." + toFile);
            File tofile = new File(toFile);
            if (tofile.exists()) {
                tofile.delete();
            }
            // word保存为pdf格式宏，值为17
            Dispatch.call(doc, "ExportAsFixedFormat", toFile, wdFormatPDF);
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");

        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
            ComThread.Release();
            ComThread.quitMainSTA();
        } finally {
            Dispatch.call(doc, "Close", false);
            System.out.println("关闭文档");
            if (app != null)
                app.invoke("Quit", new Variant[] {});
        }
        // 如果没有这句话,winword.exe进程将不会关闭
        ComThread.Release();
        ComThread.quitMainSTA();
    }
}
