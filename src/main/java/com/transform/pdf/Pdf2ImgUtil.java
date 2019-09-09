package com.transform.pdf;

import cn.hutool.json.JSONObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
@ClassName pdf2ImgUtil
@Description pdf 转图片工具
@Author liujianko
@Date 2019/7/25
**/
class Pdf2ImgUtil {

    /**
     * pdf 转图片
     * @param root 根目录
     * @param sourcePath pdf 相对路径
     * @return 图片相对路径的 json 数组
     */
    static String pdf2Img(String root, String sourcePath){
        try {
            File file = new File(root + sourcePath);
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            String[] imgArr = new String[pageCount];
            String[] split = sourcePath.split("\\.");
            String preFjgs = split[0];
            BufferedImage image;
            File dir;
            for (int i = 0; i < pageCount; i++) {
                String filePath = preFjgs + "_" + i + ".png";
                dir = new File(root + filePath);
                if (!dir.exists()) {
                    image = renderer.renderImageWithDPI(i, 296);
                    ImageIO.write(image, "png", dir);
                }
                imgArr[i] = filePath;
            }

            JSONObject json = new JSONObject();
            json.put("imgArr", imgArr);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
