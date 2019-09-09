package com.transform.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PdfUtil
 * @Description TODO
 * @Author liujianko
 * @Date 2019/9/9
 **/
public class PdfUtil {

    /**
     * 检查 pdf 是否完整
     */
    public static boolean check(String filePath){
        boolean flag = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(filePath).getPageSize(1));
            document.open();
            PdfReader reader = new PdfReader(filePath);
            int n = reader.getNumberOfPages();
            if (n != 0) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    /**
     * 获取 pdf 文件页数
     * @param filePath
     * @return
     */
    public static int getPdfPageNum(String filePath){
        try {
            PdfReader reader = new PdfReader(filePath);
            int n = reader.getNumberOfPages();
            return n;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 多个 pdf 文件合并
     * @param files
     * @param savePath
     */
    public static void pdfHb(List<String> files, String savePath){
        try {
            Document document = new Document(new PdfReader(files.get(0)).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savePath));
            document.open();
            for (int i = 0; i < files.size(); i++) {
                PdfReader reader = new PdfReader(files.get(i));
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * pdf 截取
     * @param filePath 要操作的
     * @param savePath 生成的
     * @param fromPage 从哪页开始
     * @param toPage   到哪页结束
     */
    public static void pdfSplit(String filePath, String savePath, int fromPage, int toPage){
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(filePath);
            int n = reader.getNumberOfPages();
            if (fromPage > toPage) {
                return;
            }
            if (n < toPage) {
                return;
            }
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savePath));
            document.open();
            for (int i = fromPage; i <= toPage; i++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, i);
                copy.addPage(page);
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    /**
     * pdf 拆分，拆成 N 份
     * @param filepath
     * @param N
     */
    public static void PdfSplit2(String filepath, int N) {
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(filepath);
            int n = reader.getNumberOfPages();
            if (n < N) {
                return;
            }
            int size = n / N;
            String staticpath = filepath.substring(0, filepath.lastIndexOf("\\") + 1);
            String savepath = null;
            ArrayList<String> savepaths = new ArrayList<String>();
            for (int i = 1; i <= N; i++) {
                if (i < 10) {
                    savepath = filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length() - 4);
                    savepath = staticpath + savepath + "0" + i + ".pdf";
                    savepaths.add(savepath);
                } else {
                    savepath = filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length() - 4);
                    savepath = staticpath + savepath + i + ".pdf";
                    savepaths.add(savepath);
                }
            }
            for (int i = 0; i < N - 1; i++) {
                document = new Document(reader.getPageSize(1));
                copy = new PdfCopy(document, new FileOutputStream(savepaths.get(i)));
                document.open();
                for (int j = size * i + 1; j <= size * (i + 1); j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
                document.close();
            }
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(N - 1)));
            document.open();
            for (int j = size * (N - 1) + 1; j <= n; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
    }

    public static void addSYText(String sourcePath, String savePath, String text1, String text2, String waterMarkName) {
        try {
            PdfReader reader = new PdfReader(sourcePath);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
            PdfStamper stampser = new PdfStamper(reader, bos);
            int n = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            BaseFont base = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", "Identity-H", true);// 使用系统字体
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.2f);// 设置透明度
            gs.setStrokeOpacity(0.2f);
            Rectangle pageRect = null;
            float x;
            float y;
            float w;
            float h;
            float wsize, hsize;
            for (int i = 1; i < n; i++) {
                pageRect = stampser.getReader().getPageSizeWithRotation(i);
                w = pageRect.getWidth();// 取PDF页面宽度
                h = pageRect.getHeight();// 取PDF页面高度
                x = w / 2;
                y = h / 2;
                wsize = w / 12;
                hsize = h / 12;
                content = stampser.getOverContent(i);
                content.setGState(gs);
                content.beginText();
                // content.setGrayFill(Color.GRAY);//设置灰色
                content.setFontAndSize(base, wsize);// 设置字体大小
                // 设置文字居中，水印内容，x坐标，y坐标，倾斜度
                if (StringUtils.isNotBlank(text1)) {
                    content.showTextAligned(Element.ALIGN_CENTER, text1, x, (y - (hsize + (hsize / 2))), -35);
                }
                if (StringUtils.isNotBlank(text2)) {
                    content.showTextAligned(Element.ALIGN_CENTER, text2, x, (y + (hsize + (hsize / 2))), -35);
                }
                content.setColorFill(BaseColor.RED);
                content.setFontAndSize(base, 10);
                content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x, 50, 0);

                content.endText();
            }
            stampser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addSY(String sourcePath, String savePath, String imageFile) {
        try {
            PdfReader reader = new PdfReader(sourcePath);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
            PdfStamper stampser = new PdfStamper(reader, bos);
            int n = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            PdfGState gs = new PdfGState();
            for (int i = 1; i < n; i++) {
                Image image = Image.getInstance(imageFile);
                // 图片位置

                image.setAlignment(Image.UNDERLYING);
                image.setAbsolutePosition(370, 1200);
                content = stampser.getOverContent(i);
                gs.setFillOpacity(10f);
                content.setGState(gs);
                // 添加水印图片
                content.addImage(image);
            }
            stampser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean jpgToPdf(List<String> paths, String savepath) {
        try {
            Document doc = new Document(PageSize.A4);
            // 定义输出文件的位置
            PdfWriter.getInstance(doc, new FileOutputStream(savepath));
            // 开启文档
            doc.open();
            // 设定字体 为的是支持中文
            // BaseFont bfChinese = BaseFont.createFont("STSong-Light",
            // "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            // 向文档中加入图片
            for (int i = 0; i < paths.size(); i++) {
                doc.newPage();
                Image image = Image.getInstance(paths.get(i));
                // 设置图片居中显示
                image.setAlignment(Image.MIDDLE);
                // 图片压缩
                int percent = getPercent2(image.getHeight(), image.getWidth());
                // doc.setPageSize(new Rectangle(image.getWidth(),
                // image.getHeight()));
                // image.setAbsolutePosition(0.0F, 0.0F);//取消偏移
                image.scalePercent(percent + 3);// 表示是原来图像比例
                doc.add(image);
            }
            doc.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 第一种解决方案 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩
     *
     * @param h
     * @param w
     * @return
     */

    public static int getPercent(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        if (h > w) {
            p2 = 297 / h * 100;
        } else {
            p2 = 210 / w * 100;
        }
        p = Math.round(p2);
        return p;
    }

    /**
     * 第二种解决方案，统一按照宽度压缩 这样来的效果是，所有图片的宽度是相等的，自我认为给客户的效果是最好的
     *
     * @param
     */
    public static int getPercent2(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        p2 = 530 / w * 100;
        p = Math.round(p2);
        return p;
    }

}
