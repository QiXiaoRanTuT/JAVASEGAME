package gok.frame;



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * @作者: liwang
 * @时间: 2024/12/16
 * 自定义背景图片
 */
public class BackgroundPanel extends JPanel{
    private Image image;
    private String str;//文本内容

    public BackgroundPanel(Image image,String str) {
        this.image = image;
        this.str = str;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            //设置游戏文本字体
            JLabel label = new JLabel(str);
            label.setForeground(Color.RED);
            label.setFont(new Font("宋体", Font.PLAIN, 30));

            //设置文本位置
            GroupLayout gl_contentPane = new GroupLayout(this);
            gl_contentPane.setHorizontalGroup(
                    gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addGap(400)
                                    .addComponent(label)
                                    .addContainerGap(226, Short.MAX_VALUE))
            );
            gl_contentPane.setVerticalGroup(
                    gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addGap(200)
                                    .addComponent(label)
                                    .addContainerGap(166, Short.MAX_VALUE))
            );
            this.setLayout(gl_contentPane);
        }
    }
}
