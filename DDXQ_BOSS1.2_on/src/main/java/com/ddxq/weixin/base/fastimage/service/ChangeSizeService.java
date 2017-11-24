package com.ddxq.weixin.base.fastimage.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/** 
* @author 作者:jkc E-mail:754438390@qq.com 
* @version 创建时间：2016年12月13日 下午8:58:44 
* 类说明 
*/
@Service("changeSizeService")
public class ChangeSizeService {
	
	private Image img;
	private int width;
	private int height;
	
	public byte[] changeImageSize(MultipartFile file, int w, int h) throws IllegalStateException, IOException{
		File file1 = new File("123");
		file.transferTo(file1);
		img = ImageIO.read(file1);      // 构造Image对象
		width = img.getWidth(null);    // 得到源图宽
		height = img.getHeight(null);  // 得到源图长
		System.out.println("width:"+width);
		System.out.println("height:"+height);
		return resizeFix(w,h);
	}
	public  byte[] imageToBytes(BufferedImage bImage, String format) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, format, out);
		} catch (IOException e) {
		e.printStackTrace();
		}
		return out.toByteArray();
	}
	/**
	 * 按照宽度还是高度进行压缩
	 * @param w int 最大宽度
	 * @param h int 最大高度
	 */
	public byte[] resizeFix(int w, int h) throws IOException {
		if (width / height > w / h) {
			return resizeByWidth(w);
		} else {
			return resizeByHeight(h);
		}
	}
	/**
	 * 以宽度为基准，等比例放缩图片
	 * @param w int 新宽度
	 */
	public byte[] resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		return resize(w, h);
	}
	/**
	 * 以高度为基准，等比例缩放图片
	 * @param h int 新高度
	 */
	public byte[] resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		return resize(w, h);
	}
	public  byte[] resize(int w, int h) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB ); 
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		return imageToBytes(image,"jpg");
	}
}
 