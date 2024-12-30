package gok.frame;

import gok.util.StaticValue;

import javax.swing.*;

/**
 * @作者: liwang
 * @时间: 2024/12/17
 */
public class ErrorFrame extends MyFrame{
    public ErrorFrame() {
        super();
        this.setTitle("错误页面");
        BackgroundPanel backgroundPanel = new BackgroundPanel(StaticValue.so01,"错误页面");
        setContentPane(backgroundPanel);//设置内容为自定义背景页面
        GroupLayout gl_contentPane = new GroupLayout(backgroundPanel);//设置布局方式为分组布局
    }
}
