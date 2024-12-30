package gok;

import gok.frame.*;
import gok.myView.MyGameFrame;

public class Test {
    public static boolean isOk = false;
    public static void main(String[] args) {
       int a = 0;
//        0 首页；1 登录页面；2 注册页面；3 排行榜；4 开始游戏；5 暂停游戏
        switch (a) {
            case 0:
                System.out.println("首页");
                new FirstPageFrame();   //首页
                break;
            case 1:
                System.out.println("登录");
                new LoginFrame();   //登录页面
                break;
            case 2:
                System.out.println("注册");
                new RegisterFrame();    //注册页面
                break;
            case 3:
                System.out.println("排行榜");
                new TheChartsFrame();   //排行榜页面
                break;
            case 4:
                System.out.println("开始");
                new MyGameFrame();
                break;
            case 5:
                System.out.println("暂停");
                new PauseFrame();   //暂停页面
                break;
            default:
                System.out.println("其他");
                break;
        }
    }
}
