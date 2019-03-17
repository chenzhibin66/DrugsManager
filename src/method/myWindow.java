package method;

import javax.swing.*;

public class myWindow {
    public static void
    run(final JFrame f, final int width, final int height){
        SwingUtilities.invokeLater(new Runnable(){

            public void run(){

                f.setIconImage(new ImageIcon("C:\\Users\\Administrator\\Desktop\\timg.jpg").getImage());
                f.setTitle("欢迎来到药房管理系统");
                //窗口退出行为
                f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                //设置窗口大小不可变
                f.setResizable(false);
                //设置窗口打开居中
                f.setLocationRelativeTo(null);
                //窗口大小
                f.setSize(width, height);
                //展示窗口
                f.setVisible(true);
            }
        });
    }

}
