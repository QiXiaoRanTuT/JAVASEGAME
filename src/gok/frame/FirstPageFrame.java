package gok.frame;

import gok.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @作者: liwang
 * @时间: 2024/12/16
 * 首页
 * button   登陆按钮
 * button_1   注册按钮
 * button_2   排行榜按钮
 */
public class FirstPageFrame extends MyFrame {
    private JPanel contentPane;

    public FirstPageFrame() {
        super();

        this.setTitle("首页");

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JButton button = new JButton("登录");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new LoginFrame();
                System.out.println("登录");
                dispose();  //关闭当前窗口，释放资源
            }
        });

        JButton button_1 = new JButton("注册");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
                System.out.println("注册");
                dispose();
            }
        });

        JButton button_2 = new JButton("排行榜");
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TheChartsFrame();
                System.out.println("排行榜");
                dispose();
            }
        });
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(button)
                                        .addComponent(button_2)
                                        .addComponent(button_1))
                                .addContainerGap(341, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(button)
                                .addGap(13)
                                .addComponent(button_1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_2)
                                .addContainerGap(159, Short.MAX_VALUE))
        );
//        panel.setLayout(gl_panel);

//        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); // 确保框架可见

    }
}
