package jiemian;

import zhujiemian.HomeScreen;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static jiemian.myWindow.run;

public class myDesign extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;

    //设置一个Panel容器面板和Label标签存放背景图片
    private JPanel contentPanel = new JPanel();
    private JLabel label2;
    //设置按钮组件
    private JButton login = new JButton("登录");
    private JButton registered = new JButton("注册");
    private ButtonGroup group;
    private JLabel b1=new JLabel("账号");
    private JLabel b2=new JLabel("密码");

    //设置文本框组件
    private  static  JTextField admin = new JTextField();
    private  static  JPasswordField password = new JPasswordField(20);
    public myDesign() {
        ImageIcon image2 = new ImageIcon("C:\\Users\\Administrator\\Desktop\\2.jpg");
        JLabel backLabel = new JLabel();
        backLabel.setIcon(image2);
        label2 = new JLabel(image2);
        //设置标签大小与位置
        label2.setBounds(0, 0, 501, 350);
        //在LayeredPane最底层上添加1个带图片的标签，并且label2在label上方
        this.getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE));
        //将内容面板设置为透明，就能够看见添加在LayeredPane上的背景。
        ((JPanel) this.getContentPane()).setOpaque(false);

/*
             * 添加组件到contentPanel容器中
             * 布局方式为自由布局*/
        contentPanel.setLayout(null);
        add(admin);
        add(password);
        add(login);
        add(registered);
        add(b1);
        add(b2);
        admin.setBounds(95, 130, 300, 25);
        password.setBounds(95, 154, 300, 25);
        registered.setBounds(95, 225, 90, 20);
        login.setBounds(315, 225, 90, 20);
        b1.setBounds(60, 130, 60, 25);
        b2.setBounds(60, 158, 60, 20);
        login.addActionListener(this);
        registered.addActionListener(this);
        admin.setOpaque(false);
        password.setOpaque(false);
        contentPanel.setOpaque(false);
        getContentPane().add(contentPanel);
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("登录"))
        {
            this.denglu();
        }
        else if(e.getActionCommand().equals("注册"))
        {
            this.Regis();
        }
    }
    public void denglu()
    {
         SQLsever s=new SQLsever();
          s.ConnectSQL();
          if(s.SQLverify(admin.getText(),String.valueOf(password.getPassword())))
          {
              this.dispose();
          }
          this.admin.setText("");
          this.password.setText("");

    }
    public void Regis()
    {
        run(new Sign(),500,380);
    }

    public static void main(String[] args)
    {
        run(new myDesign(),500,350);
    }

}




