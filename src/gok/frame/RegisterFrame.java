package gok.frame;

import gok.dao.impl.GameDaoImpl;
import gok.dao.impl.UserDaoImpl;
import gok.model.Game;
import gok.model.User;
import gok.myView.MyGameFrame;
import gok.util.JDBCUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @作者: liwang
 * @时间: 2024/12/17
 * 登录按钮
 * register_username  //用户名输入框
 * login_password //密码输入框
 * registerButton  //注册按钮
 */
public class RegisterFrame extends MyFrame {
    private JPanel contentPane;
    private JTextField register_username;//用户名输入框
    private JTextField register_password;//密码输入框
    public RegisterFrame() {
        super();
        this.setTitle("注册页面");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        JLabel lblNewLabel = new JLabel("用户名");

        JLabel label = new JLabel("密码");

        register_username = new JTextField();
        register_username.setColumns(10);

        register_password = new JTextField();
        register_password.setColumns(10);

        JButton registerButton = new JButton("注册");//登录按钮
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setUsername(register_username.getText());
                user.setPassword(register_password.getText());

                UserDaoImpl userDao = new UserDaoImpl();
                try {
                    int status = userDao.addUser(user);

                    if (status == 1) {

                        //如果注册成功,同步添加到游戏用户中
                        Game game = new Game();
                        game.setUsername(register_username.getText());
                        game.setId(userDao.getUserAll(user).get(0).getUserid());
                        game.setScore(0);//防止空值报错
                        GameDaoImpl gameDao = new GameDaoImpl();
                        int gameStatus = gameDao.addUser(game);
                        if (gameStatus == 1){

                            System.out.println("注册成功");
                            JOptionPane.showMessageDialog(null, "注册成功，即将跳转登录页", "提示", JOptionPane.INFORMATION_MESSAGE);
                            new LoginFrame();
                            dispose();
                        }else {

                                System.out.println("游戏用户注册失败");
                                JOptionPane.showMessageDialog(null, "游戏用户注册失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else {
                        System.out.println("注册失败");
                        JOptionPane.showMessageDialog(null, "注册失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("注册");
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(72)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNewLabel)
                                                        .addComponent(label))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(register_password)
                                                        .addComponent(register_username, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(180)
                                                .addComponent(registerButton)))
                                .addContainerGap(95, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(40)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(register_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(31)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label)
                                        .addComponent(register_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(37)
                                .addComponent(registerButton)
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
