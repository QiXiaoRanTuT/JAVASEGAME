package gok.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加载静态图片，常量
 */
public class StaticValue {
	
	//获取项目的根目录
	public static final String ImagePath = System.getProperty("user.dir") + "/res/";
	//状态图片
	public static BufferedImage sp01=null;
	public static BufferedImage so01=null;

	//背景图片01
	public static BufferedImage bg01 = null;
	public static BufferedImage bg02 = null;
	public static BufferedImage bg03 = null;
	public static BufferedImage bg04 = null;
	//人物图片集合
	public static List<BufferedImage> leftPersonImgs = new ArrayList<>();
	public static List<BufferedImage> rightPersonImgs = new ArrayList<>();
	
	//敌人图片集合
	public static List<BufferedImage> leftEnemyImgs = new ArrayList<>();
	public static List<BufferedImage> rightEnemyImgs = new ArrayList<>();
	
	//数字图片
	public static Map<String,BufferedImage> numberImgs  = new HashMap<>();

	static {

		try {
			//获取状态图片
			sp01 =ImageIO.read(new File(ImagePath+"state/pause01.png"));
			so01 =ImageIO.read(new File(ImagePath+"state/over01.png"));

			//获取图片
			bg01 = ImageIO.read(new File(ImagePath + "background/bg01.png"));
			bg02 = ImageIO.read(new File(ImagePath + "background/bg02.png"));
			bg03 = ImageIO.read(new File(ImagePath + "background/bg03.png"));
			bg04 = ImageIO.read(new File(ImagePath + "background/bg04.png"));
			
			//获取数字图片
			BufferedImage numberImg = ImageIO.read(new File(ImagePath + "number/score_pink.png"));
			for(int i=0;i<10;i++) {
				numberImgs.put(i+"", numberImg.getSubimage(i*22, 0, 22, 28));
			}
			
			//获取人物图片
			for(int i=1;i<=14;i++) {//遍历图片数1-14
				DecimalFormat decimalFormat = new DecimalFormat("00");//数据格式化模板
				String num = decimalFormat.format(i);//数据格式化
				//添加人物图片到集合中
				leftPersonImgs.add(ImageIO.read(new File(ImagePath + "person/left/"+num+".png")));
				rightPersonImgs.add(ImageIO.read(new File(ImagePath + "person/right/"+num+".png")));
			}
			
			//加载敌人图片
			for(int i=0;i<=5;i++) {
				File rightfile = new File(ImagePath + "enemy/right/141_move_"+i+".png");
				File leftfile = new File(ImagePath + "enemy/left/141_move_"+i+".png");
				leftEnemyImgs.add(ImageIO.read(leftfile));
				rightEnemyImgs.add(ImageIO.read(rightfile));
			}
			
			for(int i=0;i<=7;i++) {
				File rightfile = new File(ImagePath + "enemy/right/141_skill_1104_"+i+".png");
				File leftfile = new File(ImagePath + "enemy/left/141_skill_1104_"+i+".png");
				leftEnemyImgs.add(ImageIO.read(leftfile));
				rightEnemyImgs.add(ImageIO.read(rightfile));
			}
			
			for(int i=0;i<=5;i++) {
				File rightfile = new File(ImagePath + "enemy/right/141_stand_"+i+".png");
				File leftfile = new File(ImagePath + "enemy/left/141_stand_"+i+".png");
				leftEnemyImgs.add(ImageIO.read(leftfile));
				rightEnemyImgs.add(ImageIO.read(rightfile));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

