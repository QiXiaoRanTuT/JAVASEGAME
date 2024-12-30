package gok.frame;


import gok.dao.GameDao;
import gok.dao.impl.GameDaoImpl;
import gok.model.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者: liwang
 * @时间: 2024/12/17
 * 排行榜页面
 * theChartsTable   //排行榜列表显示控件
 * theChartsButton  //排行榜查询按钮
 * theChartsTableScrollPane  //滚动条
 */
public class TheChartsFrame extends MyFrame {
    private JPanel contentPane;
    private JTable theChartsTable;//排行榜列表显示控件
    private JScrollPane theChartsTableScrollPane;

    public TheChartsFrame() {
        super();
        this.setTitle("排行榜页面");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);


        JButton theChartsButton = new JButton("排行榜");
        theChartsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("排行榜");
                DefaultTableModel model = new DefaultTableModel();
                theChartsTable.setModel(model);

                //获取数据
                GameDao gameDao = new GameDaoImpl();
                List<Game> gameList = new ArrayList<>();
                try {

                    gameList = gameDao.getGame();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //添加表头
                model.addColumn("编号");
                model.addColumn("用户编号");
                model.addColumn("用户名");
                model.addColumn("游戏得分");
                model.addColumn("游戏时间");

                //添加数据
//                model.addRow(new Object[]{1, "John Doe", 30});
//                model.addRow(new Object[]{2, "Jane Smith", 25});
                if (gameList.size()>0){
                    for (Game game:gameList){
                        //每次循环，就添加数据到model里面
                        Object[] o = new Object[5];
                        o[0] = game.getGameid();
                        o[1] = game.getId();
                        o[2] = game.getUsername();
                        o[3] = game.getScore();
                        o[4] = game.getGametime();
                        model.addRow(o);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "没有查询到数据", "提示", JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("没有查询到数据");
                }
            }
        });

        theChartsTableScrollPane = new JScrollPane();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(10)
                                                .addComponent(theChartsTableScrollPane, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(theChartsButton))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(theChartsButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(theChartsTableScrollPane, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                .addContainerGap())
        );

        theChartsTable = new JTable();
        theChartsTableScrollPane.setViewportView(theChartsTable);
        contentPane.setLayout(gl_contentPane);

    }
}
