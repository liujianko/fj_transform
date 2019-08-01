package com.transform.html;

import com.transform.util.B64ImgReplacedElementFactory;
import com.transform.util.PdfHelper;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Html2PdfUtil {

    /**
     * html 填充数据并生成 html
     * @param tempPath html 模板文件路径
     * @param tempName html 模板文件名称
     * @param imgPath img 图片路径
     * @param data 填充到 html 里的数据
     * @param pdfPath 生成 pdf 的路径
     */
    public static void html2Pdf(String tempPath, String tempName, String imgPath, Object data, String pdfPath) throws Exception {
        String html = PdfHelper.getPdfContent(tempPath, tempName, data);
        OutputStream out;
        ITextRenderer render;
        out = new FileOutputStream(pdfPath);
        render = PdfHelper.getRender();
        render.setDocumentFromString(html);
        // base64图片编码处理
        render.getSharedContext().setReplacedElementFactory(new B64ImgReplacedElementFactory());
        if (imgPath != null && !imgPath.equals("")) {
            // html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径
            render.getSharedContext().setBaseURL("file:/" + imgPath);
        }
        render.layout();
        render.createPDF(out);
        render.finishPDF();
        out.close();
    }

    public static void main(String[] args) {
        String tempPath = "E:/fj_transform/html/html2pdf";
        String tempName = "tzskqrs.html";
        String imgPath = "E:/fj_transform/html/html2pdf/123.gif";
        Map<String,Object> data = new HashMap<>();
        // 图片 base64 编码
        String dataBase64 = "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";
        data.put("ah","11111");
        data.put("bankcardholder","11111");
        data.put("bankaccountnumber","11111");
        data.put("bankname","11111");
        data.put("sjhm","11111");
        data.put("bankbydh","11111");
        data.put("dzqm",dataBase64);
        String pdfPath = "E:/fj_transform/html/html2pdf/createPdf.pdf";
        try {
            Html2PdfUtil.html2Pdf(tempPath, tempName, imgPath, data, pdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
