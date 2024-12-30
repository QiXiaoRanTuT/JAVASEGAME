package gok.frame;



import gok.util.StaticValue;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @作者: liwang
 * @时间: 2024/12/17
 * 暂停页面
 */
public class PauseFrame extends MyFrame implements KeyListener {
    public PauseFrame() {
        super();
        this.setTitle("暂停页面");

        BackgroundPanel backgroundPanel = new BackgroundPanel(StaticValue.sp01,"暂停游戏!按y键继续。n键退出游戏");
        setContentPane(backgroundPanel);//设置内容为自定义背景页面
        GroupLayout gl_contentPane = new GroupLayout(backgroundPanel);//设置布局方式为分组布局

        //打开监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //System.out.println("按键:  "+e.getKeyCode());
        if (keyCode==89){//按y键
            System.out.println("继续");
//            frame.MyFrame.ifwait=true;//线程继续
//            TestApp.mygame.setVisible(true);//打开游戏窗体
            this.setVisible(false);//隐藏当前窗体
        }
        if (keyCode==78){//按n键
            System.out.println("退出");
            new FirstPageFrame();
            this.setVisible(false);//隐藏当前窗体
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
