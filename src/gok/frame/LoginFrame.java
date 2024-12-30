package gok.frame;

import gok.dao.impl.GameDaoImpl;
import gok.dao.impl.UserDaoImpl;
import gok.model.Game;
import gok.model.User;
import gok.myView.MyGameFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @作者: liwang
 * @时间: 2024/12/17
 * 登录按钮
 * login_username  //用户名输入框
 * login_password //密码输入框
 * loginButton  //登录按钮
 */
public class LoginFrame extends MyFrame {
    public static Integer userid = 0; //全局用户id
    public static Integer gameid = 0;//全局游戏id
    public static Integer zsScore=0;    // 在这里定义不会进行频繁更新，并且可以更新最高分数
    private JPanel contentPane;
    private JTextField login_username;//用户名输入框
    private JTextField login_password;//密码输入框

    public LoginFrame() {
        super();
        this.setTitle("登录页面");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        JLabel lblNewLabel = new JLabel("用户名");

        JLabel label = new JLabel("密码");

        login_username = new JTextField();
        login_username.setColumns(10);

        login_password = new JTextField();
        login_password.setColumns(10);

        JButton loginButton = new JButton("登录");//登录按钮
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("登录");
                User user = new User();
                user.setUsername(login_username.getText());
                user.setUserid(null);
                user.setPassword(login_password.getText());
                UserDaoImpl userDao = new UserDaoImpl();

                try {
                    List<User> userAll = userDao.getUserAll(user);

                    if (userAll.size() > 0) {
                        userid = userAll.get(0).getUserid();//更新全局用户id

                        //查询到用户id后根据当前用户查询用户的游戏id
                        GameDaoImpl gameDao = new GameDaoImpl();
                        Game game = new Game();
                        game.setId(userid);
                        List<Game> gameList = gameDao.getGame(game);
                        System.out.println(gameList);
                        gameid = gameList.get(0).getGameid();//更新全局游戏id
                        zsScore = gameList.get(0).getScore();//获取最高分
                        System.out.println("登录成功");
                        JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        new MyGameFrame();
                        dispose();
                    } else {
                        System.out.println("登录失败");
                        JOptionPane.showMessageDialog(null, "登录失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

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
                                                        .addComponent(login_password)
                                                        .addComponent(login_username, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(180)
                                                .addComponent(loginButton)))
                                .addContainerGap(95, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(40)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(login_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(31)
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label)
                                        .addComponent(login_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(37)
                                .addComponent(loginButton)
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
