package gok.action;

import gok.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("按钮，你好");

        Test.isOk = true;   //点击之后切换场景标识为true
    }

}
