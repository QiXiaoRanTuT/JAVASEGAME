package gok.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class NumberUtils {

	/**
	 * 获取字符图片
	 */
	public static BufferedImage getNumberImage(String number, ImageObserver observer) {
		//获取字符串的字符数组
		char[] charArray = number.toCharArray();
		//创建空白图片，宽根据number长度*22 高度固定30
		BufferedImage image = new BufferedImage(22*number.length(), 30, BufferedImage.TYPE_3BYTE_BGR);
		
		//背景透明
		Graphics2D g2d = image.createGraphics();
		image = g2d.getDeviceConfiguration().createCompatibleImage(220, 30,
				Transparency.TRANSLUCENT);
		
		//获取画笔
		Graphics graphics = image.getGraphics();

		//遍历每个字符
		int index = 0;
		for (char subNum : charArray) {
			//获取每个字符的图片
			BufferedImage bufferedImage = StaticValue.numberImgs.get(subNum + "");
			//画到空白图片中,每个字符起始位置间隔22像素
			graphics.drawImage(bufferedImage, 22 * index, 0, observer);
			index++;
		}
		return image;
	}

}
