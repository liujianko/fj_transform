package com.transform.util;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import org.bouncycastle.util.encoders.Base64;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import java.io.IOException;

/**
 * 
 * 项目名称：k12py_dev <br>
 * 类名称：B64ImgReplacedElementFactory <br>
 * 类描述：TODO(解决iText+flying saucer+freemark导出pdf不支持base64) <br>
 * 创建人：<br>
 * 创建时间：2016年11月1日 下午7:11:49 <br>
 * 
 * @version V1.0
 */
public class B64ImgReplacedElementFactory implements ReplacedElementFactory {

	/*
	 * 
	 * 数据渲染
	 *
	 * @author
	 * 
	 * @param c 上下文
	 * 
	 * @param box 盒子
	 * 
	 * @param uac 回调
	 * 
	 * @param cssWidth css宽
	 * 
	 * @param cssHeight css高
	 * 
	 * @return
	 * 
	 * @see
	 * org.xhtmlrenderer.extend.ReplacedElementFactory#createReplacedElement(org
	 * .xhtmlrenderer.layout.LayoutContext, org.xhtmlrenderer.render.BlockBox,
	 * org.xhtmlrenderer.extend.UserAgentCallback, int, int)
	 */
	@Override
	public ReplacedElement createReplacedElement(LayoutContext c, BlockBox box, UserAgentCallback uac, int cssWidth,
			int cssHeight) {
		Element e = box.getElement();
		if (e == null) {
			return null;
		}
		String nodeName = e.getNodeName();
		// 找到img标签
		if (nodeName.equals("img")) {
			String attribute = e.getAttribute("src");
			FSImage fsImage;
			try { // 生成itext图像
				fsImage = buildImage(attribute, uac);
			} catch (BadElementException e1) {
				fsImage = null;
			} catch (IOException e1) {
				fsImage = null;
			}
			if (fsImage != null) { // 对图像进行缩放
				if (cssWidth != -1 || cssHeight != -1) {
					fsImage.scale(cssWidth, cssHeight);
				}
				return new ITextImageElement(fsImage);
			}
		}
		return null;
	}

	/**
	 * 
	 * TODO(将base64编码解码并生成itext图像)
	 * 
	 * @author 2016年11月1日 下午7:08:57
	 * @param srcAttr
	 *            属性
	 * @param uac
	 *            回调
	 * @return
	 * @throws IOException
	 * @throws BadElementException
	 */
	protected FSImage buildImage(String srcAttr, UserAgentCallback uac) throws IOException, BadElementException {
		FSImage fsImage;
		if (srcAttr.startsWith("data:image/")) {
			String b64encoded = srcAttr.substring(srcAttr.indexOf("base64,") + "base64,".length(), srcAttr.length()); // 解码
			byte[] decodedBytes = Base64.decode(b64encoded);
			Image image = Image.getInstance(decodedBytes);
			/*
			 * System.out.println("旋转弧度："+image.getRotation());
			 * System.out.println("旋转角度："+image.getImageRotation());
			 * //image.setRotation(90);//旋转 弧度
			 * image.setRotationDegrees(-350f);//旋转 角度
			 * System.out.println("旋转弧度："+image.getRotation());
			 * System.out.println("旋转角度："+image.getImageRotation());
			 */
			fsImage = new ITextFSImage(image);
		} else {
			fsImage = uac.getImageResource(srcAttr).getImage();
		}
		return fsImage;
	}

	@Override
	public void remove(Element arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void reset() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void setFormSubmissionListener(FormSubmissionListener arg0) {
		// TODO 自动生成的方法存根

	}
}
