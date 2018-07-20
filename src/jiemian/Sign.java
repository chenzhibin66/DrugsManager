package jiemian;

import com.bean.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sign extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel panel = new JPanel();
    private JButton ok = new JButton("确定注册");
    private JLabel b1 = new JLabel("账号");
    private JLabel b2 = new JLabel("密码");
    private JLabel b3 = new JLabel("姓名");
    private JLabel b4 = new JLabel("电话");
    private JLabel b5 = new JLabel("邮件");
    private JLabel b6 = new JLabel("地址");
    public JTextField yonghu = new JTextField(20);
    public JPasswordField mima = new JPasswordField(20);
    public JTextField name = new JTextField(20);
    public JTextField phone = new JTextField(20);
    public JTextField email = new JTextField(20);
    public JTextField address = new JTextField(20);

    public Sign() {
        ImageIcon image = new ImageIcon("C:\\Users\\Administrator\\Desktop\\timg.jpg");
        JLabel backLabel1 = new JLabel();
        JLabel label2 = new JLabel();
        backLabel1.setIcon(image);
        label2 = new JLabel(image);
        label2.setBounds(0, 0, 501, 350);
        this.getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE));
        //将内容面板设置为透明，就能够看见添加在LayeredPane上的背景。
        ((JPanel) this.getContentPane()).setOpaque(false);
        panel.setLayout(null);
        add(ok);
        add(yonghu);
        add(mima);
        add(name);
        add(address);
        add(phone);
        add(email);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        ok.addActionListener(this::actionPerformed);
        yonghu.setBounds(95, 50, 200, 25);
        mima.setBounds(95, 90, 200, 25);
        name.setBounds(95, 130, 200, 25);
        phone.setBounds(95, 170, 200, 25);
        email.setBounds(95, 210, 200, 25);
        address.setBounds(95, 250, 200, 25);
        ok.setBounds(170, 300, 90, 25);
        b1.setBounds(55, 50, 60, 25);
        b2.setBounds(55, 90, 60, 25);
        b3.setBounds(55, 130, 60, 25);
        b4.setBounds(55, 170, 60, 25);
        b5.setBounds(55, 210, 60, 25);
        b6.setBounds(55, 250, 60, 25);
        yonghu.setOpaque(false);
        mima.setOpaque(false);
        panel.setOpaque(false);
        getContentPane().add(panel);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("确定注册")) {
            this.zhuce();
        }
    }

    public void zhuce() {
        SQLsever ss = new SQLsever();
        ss.ConnectSQL();
        User user = new User();
        String zh = yonghu.getText();
        String mm = mima.getText();
        String mz = name.getText();
        String tel = phone.getText();
        String yj = email.getText();
        String dz = address.getText();
        if (zh.equals("")) {
            JOptionPane.showMessageDialog(null, "账号不能为空！", "提示信息", JOptionPane.WARNING_MESSAGE);
        } else if (mm.equals("") || mm.length() < 3) {
            JOptionPane.showMessageDialog(null, "密码不能为空且不能少于3位", "提示信息", JOptionPane.WARNING_MESSAGE);
        } else if (mz.equals("")) {
            JOptionPane.showMessageDialog(null, "姓名不能为空！", "提示信息", JOptionPane.WARNING_MESSAGE);
        }else{
            user.setUzhanghao(zh);
            user.setUpassword(mm);
            user.setUname(mz);
            user.setUphone(tel);
            user.setUemail(yj);
            user.setUaddress(dz);
            ss.zhuceVerify(user);
            System.out.println(yonghu.getText() + " " + mima.getText());
        }

    }

}


