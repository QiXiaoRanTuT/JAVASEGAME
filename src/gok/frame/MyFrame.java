package gok.frame;



import javax.swing.*;
import java.awt.*;

/**
 * @作者: liwang
 * @时间: 2024/12/16
 */
public class MyFrame extends JFrame {
    public MyFrame(){
        //窗体标题
        this.setTitle("game");
        //窗体大小
        this.setSize(900, 600);
        //设置窗体居中
        //Toolkit.getDefaultToolkit().getScreenSize()获取显示器屏幕大小
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width-900)/2, (height-600)/2);//设置窗体位置

        //设置关闭按钮
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭方式:直接关闭应用程序
        //设置可见
        this.setVisible(true);

    }
}
