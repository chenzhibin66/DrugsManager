package zhujiemian;

import com.bean.Drug;
import com.bean.Sell;
import com.bean.Stock;
import com.bean.Vip;
import jiemian.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class HomeScreen extends JFrame {
    private JLabel searchofyouxiaoqi,keyword, totalinprice,totaloutprice,profit,kwofsell,kwofstock,kwofvip, drugnameofyouxiaoqi, L1, L2, L3, L4, L5, L6, Medid, Medname, Medtype, Medcreate, contactKF, VipID, VipName, VipAge, VipPhone, VipMessage, Vipadress, stocknum, shengyuquantity, stockID, drugname, quantity, unitprice, totalprice, Vip, count;
    private JButton add1, add2, addofstock, resetofstock, searchofstock, query, alterdrug, altervip, delete1, delete2, destory, resetting, submitorder, resetorder, searchofalter, searchofvip;
    private static JTextField begintimeText,endtimeText,totalinpriceText,totaloutpriceText,profitText,searchmedofsellText,nameTextFieldofAddMed, typeTextFieldofAddMed, createTextFieldofAddMed, alterMedid, alterMedname, alterMedtype, alterMedcreate, searchjTextField;
    private JFrame myFrame;
    private static JTextField searchofyouxiaoqiText,searchdrugnameText, drugidofstockText, drugnameofstockText, drugquantityText, dateofstockText, priceofstockText, sellpriceofstockText, alterMedidjTextField, alterMednamejTextField, alterMedtypejTextField, alterMedcreatejTextField, AddnameTextField, AddtypeTextField, AddcreateTextField, vipsearch, vipidjTextField, vipnamejTextField, vipagejTextField, vipphonejTextField, vipemailjTextField, vipaddressjTextField, stockidofsellText, mednameofsellText, quantityofsellText, unitpriceofsellText, vipidofsellText, countofsellText, vipofsellText, totalpriceofsellText, stockidofyouxiaoqiText, mednameofyouxiaoqiText, shengyuquantityText, searchofvipText;
    JTable table;
    DefaultTableModel tableModel;
    JPanel jPanelUP;
    Connection conn;
    String c1, d1;
    float per;

    public HomeScreen() {
        searchofyouxiaoqi=new JLabel("请输入药品名称：");
        kwofstock = new JLabel("请输入关键字：");
        kwofsell=new JLabel("请输入药品名称：");
        kwofvip=new JLabel("请输入手机号：");
        totalinprice=new JLabel("总进价：");
        totaloutprice=new JLabel("总售价:");
        profit=new JLabel("总利润:");
        L1 = new JLabel("药品编号：");
        L2 = new JLabel("药品名称");
        L3 = new JLabel("药品数量");
        L4 = new JLabel("过期日期");
        L5 = new JLabel("进价");
        L6 = new JLabel("售价");
        drugnameofyouxiaoqi = new JLabel("药品名称：");
        keyword = new JLabel("请输入关键字:");
        Medid = new JLabel("药品编号:");
        Medname = new JLabel("药品名称:");
        drugname = new JLabel("药品名称:");
        Medtype = new JLabel("药品类型:");
        Medcreate = new JLabel("生产商:");
        stocknum = new JLabel("库存编号:");
        shengyuquantity = new JLabel("剩余数量:");
        VipID = new JLabel("会员ID:");
        Vipadress = new JLabel("地址:");
        VipAge = new JLabel("年龄:");
        VipMessage = new JLabel("邮箱:");
        VipPhone = new JLabel("电话：");
        VipName = new JLabel("会员姓名：");
        Vip = new JLabel("会员：");
        stockID = new JLabel("库存ID：");
        quantity = new JLabel("数量：");
        unitprice = new JLabel("单价：");
        count = new JLabel("购买数量：");
        totalprice = new JLabel("总价：");
        add1 = new JButton("添加");
        add2 = new JButton("添加");
        addofstock = new JButton("添加");
        resetofstock = new JButton("重置");
        resetting = new JButton("重置");
        destory = new JButton("销毁：");
        query = new JButton("查询");
        alterdrug = new JButton("修改");
        altervip = new JButton("修改");
        delete1 = new JButton("删除");
        delete2 = new JButton("删除");
        resetorder = new JButton("重置订单");
        submitorder = new JButton("提交订单");

        //提交订单按钮事件
        submitorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = stockidofsellText.getText();
                String s4 = unitpriceofsellText.getText();
                String s5 = vipidofsellText.getText();
                String s6 = countofsellText.getText();
                String s7= totalpriceofsellText.getText();
                Sell sell=new Sell();
                String slno = "";
                SQLsever sqLsever = new SQLsever();
                int l = sqLsever.getRowCountofSell();
                slno = "S" + String.valueOf(l);
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ldate1 = dateFormat.format(date);
                sell.setSellnum(slno);
                sell.setStocknum(s1);
                sell.setVipnum(s5);
                sell.setSellprice(Float.parseFloat(s4));
                sell.setSellquantity(Integer.parseInt(s6));
                sell.setSellmoney(Float.parseFloat(s7));
                sell.setLdate(ldate1);
                sqLsever.AddSellVerity(sell);
                sqLsever.updateSell();
            }
        });
        //修改按钮事件
        alterdrug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLsever s = new SQLsever();
                s.ConnectSQL();
                String name = alterMednamejTextField.getText();
                String type = alterMedtypejTextField.getText();
                String create = alterMedcreatejTextField.getText();
                String id = alterMedidjTextField.getText();
                Drug drug = new Drug();
                drug.setDrugname(name);
                drug.setDrugtype(type);
                drug.setSuppliername(create);
                s.updateDrug(drug, id);


            }
        });
        //会员修改按钮事件
        altervip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLsever s = new SQLsever();
                s.ConnectSQL();
                String vipid = vipidjTextField.getText();
                String vipname = vipnamejTextField.getText();
                String vipage = vipagejTextField.getText();
                String vipphone = vipphonejTextField.getText();
                String vipemail = vipemailjTextField.getText();
                String vipaddress = vipaddressjTextField.getText();
                com.bean.Vip vip = new Vip();
                vip.setVipname(vipname);
                vip.setVipnum(vipid);
                vip.setVipage(vipage);
                vip.setVipphone(vipphone);
                vip.setVipemail(vipemail);
                vip.setVipaddress(vipaddress);
                s.updateVip(vip, vipid);
            }
        });

        //添加药品事件
        add1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLsever s = new SQLsever();
                s.ConnectSQL();
                String name = nameTextFieldofAddMed.getText();
                String type = typeTextFieldofAddMed.getText();
                String create = createTextFieldofAddMed.getText();
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                String sDate = simpleDateFormat.format(date);
                Drug drug = new Drug();
                //自动形成有规律的编号
                String id = sDate.replace("-", "");
                drug.setDrugnum(id);
                drug.setDrugname(name);
                drug.setDrugtype(type);
                drug.setSuppliername(create);
                s.AddMedVerity(drug);
            }
        });
        //重置订单
        resetorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vipidofsellText.setText("");
                countofsellText.setText("");
                totalpriceofsellText.setText("");
            }
        });
        //入库添加
        addofstock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLsever s = new SQLsever();
                s.ConnectSQL();
                String id = drugidofstockText.getText();
                String n = drugnameofstockText.getText();
                String quantity = drugquantityText.getText();
                String outdate = dateofstockText.getText();
                String inputprice = priceofstockText.getText();
                String sellprice = sellpriceofstockText.getText();
                Stock stock = new Stock();
                String sid = "";
                SQLsever sqLsever = new SQLsever();
                int m = sqLsever.getRowCountofStock();
                sid = "00" + String.valueOf(m);
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ldate = dateFormat.format(date);
                float totalprice=Float.parseFloat(quantity)*Float.parseFloat(inputprice);
                stock.setStocknum(sid);
                stock.setDrugnum(id);
                stock.setDrugname(n);
                stock.setDrugtype(c1);
                stock.setSname(d1);
                stock.setDbidd(Float.parseFloat(inputprice));
                stock.setDprice(Float.parseFloat(sellprice));
                stock.setStockquantity(Integer.parseInt(quantity));
                stock.setOutdate(outdate);
                stock.setLdate(ldate);
                stock.setTotalPrice(totalprice);
                s.AddStockVerity(stock);
            }
        });
        resetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTextFieldofAddMed.setText("");
                typeTextFieldofAddMed.setText("");
                createTextFieldofAddMed.setText("");
            }
        });
        resetofstock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drugquantityText.setText("");
                dateofstockText.setText("");
                priceofstockText.setText("");
                sellpriceofstockText.setText("");
            }
        });
        searchdrugnameText = new JTextField();
        drugidofstockText = new JTextField();
        drugnameofstockText = new JTextField();
        drugquantityText = new JTextField();
        dateofstockText = new JTextField();
        priceofstockText = new JTextField();
        sellpriceofstockText = new JTextField();
        alterMedcreatejTextField = new JTextField();
        alterMedidjTextField = new JTextField();
        alterMednamejTextField = new JTextField();
        alterMedtypejTextField = new JTextField();
        nameTextFieldofAddMed = new JTextField();
        typeTextFieldofAddMed = new JTextField();
        createTextFieldofAddMed = new JTextField();
        vipidjTextField = new JTextField();
        vipnamejTextField = new JTextField();
        vipphonejTextField = new JTextField();
        vipemailjTextField = new JTextField();
        vipaddressjTextField = new JTextField();
        vipagejTextField = new JTextField();
        searchjTextField = new JTextField();
        alterMedid = new JTextField();
        alterMedname = new JTextField();
        alterMedtype = new JTextField();
        alterMedcreate = new JTextField();
        stockidofsellText = new JTextField();
        mednameofsellText = new JTextField();
        quantityofsellText = new JTextField();
        unitpriceofsellText = new JTextField();
        vipidofsellText = new JTextField();
        quantityofsellText = new JTextField();
        vipofsellText = new JTextField();
        totalpriceofsellText = new JTextField();
        countofsellText = new JTextField();
        stockidofyouxiaoqiText = new JTextField();
        mednameofyouxiaoqiText = new JTextField();
        shengyuquantityText = new JTextField();
        searchofvipText = new JTextField();
        searchmedofsellText=new JTextField();
        totalinpriceText=new JTextField();
        totaloutpriceText=new JTextField();
        profitText=new JTextField();
        begintimeText=new JTextField();
        endtimeText=new JTextField();
        searchofyouxiaoqiText=new JTextField();
        myFrame = this;
        JDesktopPane desktopPane = new JDesktopPane();
        myFrame.add(desktopPane);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("库存管理");
        JMenu menu2 = new JMenu("销售管理");
        JMenu menu3 = new JMenu("查询管理");
        JMenu menu4 = new JMenu("关于我们");
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);
        JMenuItem menuItem1 = new JMenuItem("添加药品");
        JMenuItem menuItem2 = new JMenuItem("修改药品");
        JMenuItem menuItem3 = new JMenuItem("入库管理");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);
        JMenuItem m1 = new JMenuItem("会员");
        JMenuItem m2 = new JMenuItem("出售");
        menu2.add(m1);
        menu2.add(m2);
        JMenuItem n1 = new JMenuItem("有效期");
        JMenuItem n2 = new JMenuItem("盈利");
        menu3.add(n1);
        menu3.add(n2);
        JMenuItem a1 = new JMenuItem("联系客服");
        menu4.add(a1);
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame internalFrame = new JInternalFrame("添加药品", true, true, true, true);
                internalFrame.setSize(new Dimension(500, 700));
                internalFrame.setLayout(null);
                internalFrame.setVisible(true);
                Container icontentpane = internalFrame.getContentPane();
                add(internalFrame);
                nameTextFieldofAddMed.setBounds(115, 155, 300, 25);
                typeTextFieldofAddMed.setBounds(115, 180, 300, 25);
                createTextFieldofAddMed.setBounds(115, 205, 300, 25);
                Medname.setBounds(60, 155, 60, 25);
                Medtype.setBounds(60, 180, 60, 25);
                Medcreate.setBounds(60, 205, 60, 25);
                add1.setBounds(70, 240, 90, 20);
                resetting.setBounds(250, 240, 90, 20);
                icontentpane.add(Medname);
                icontentpane.add(Medtype);
                icontentpane.add(Medcreate);
                icontentpane.add(add1);
                icontentpane.add(resetting);
                icontentpane.add(nameTextFieldofAddMed);
                icontentpane.add(typeTextFieldofAddMed);
                icontentpane.add(createTextFieldofAddMed);
                ImageIcon icon=new ImageIcon("C:\\Users\\Administrator\\Desktop\\3.jpg");
                JLabel label1=new JLabel(icon);
                label1.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
                icontentpane.add(label1,new Integer(Integer.MIN_VALUE));
                ((JPanel) icontentpane).setOpaque(false);
            }
        });
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame internalFrame = new JInternalFrame("修改药品", true, true, true, true);
                internalFrame.setSize(new Dimension(500, 700));
                internalFrame.setLayout(null);
                internalFrame.setVisible(true);
                add(internalFrame);
                Container icontentpane = internalFrame.getContentPane();
                Vector rowData = PutinStorage.getRows();//取得药品表中的各行数据
                Vector columnNames = PutinStorage.getHead();//取得药品表的表头数据
                tableModel = new DefaultTableModel(rowData, columnNames);
                table = new JTable(tableModel)//表格不允许被编辑
                {
                    public boolean isCellEditable(int rowDate, int columnNames) {
                        return false;
                    }
                };
                table.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                        String a = table.getValueAt(table.getSelectedRow(), 0).toString();
                        String b = table.getValueAt(table.getSelectedRow(), 1).toString();
                        String c = table.getValueAt(table.getSelectedRow(), 2).toString();
                        String d = table.getValueAt(table.getSelectedRow(), 3).toString();
                        alterMedidjTextField.setText(a);
                        alterMedidjTextField.setEditable(false);
                        alterMednamejTextField.setText(b);
                        alterMedtypejTextField.setText(c);
                        alterMedcreatejTextField.setText(d);

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                table.setSize(500, 100);
                JScrollPane s = new JScrollPane(table);
                s.setSize(500, 100);
                s.setLocation(150, 120);
                icontentpane.add(s);
                searchjTextField.setBounds(300, 80, 200, 25);
                alterMedidjTextField.setBounds(150, 250, 150, 25);
                alterMednamejTextField.setBounds(500, 250, 150, 25);
                alterMedtypejTextField.setBounds(150, 300, 150, 25);
                alterMedcreatejTextField.setBounds(500, 300, 150, 25);
                keyword.setBounds(180, 80, 100, 25);
                Medid.setBounds(80, 250, 60, 25);
                Medname.setBounds(430, 250, 60, 25);
                Medtype.setBounds(80, 300, 60, 25);
                Medcreate.setBounds(430, 300, 60, 25);
                alterdrug.setBounds(150, 350, 60, 25);
                delete1.setBounds(500, 350, 60, 25);
                icontentpane.add(Medname);
                icontentpane.add(Medtype);
                icontentpane.add(Medcreate);
                icontentpane.add(Medid);
                icontentpane.add(delete1);
                icontentpane.add(alterdrug);
                icontentpane.add(keyword);
                icontentpane.add(searchjTextField);
                icontentpane.add(alterMedidjTextField);
                icontentpane.add(alterMednamejTextField);
                icontentpane.add(alterMedtypejTextField);
                icontentpane.add(alterMedcreatejTextField);
                ImageIcon icon=new ImageIcon("C:\\Users\\Administrator\\Desktop\\3.jpg");
                JLabel label1=new JLabel(icon);
                label1.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
                icontentpane.add(label1,new Integer(Integer.MIN_VALUE));
                ((JPanel) icontentpane).setOpaque(false);
                //实现模糊动态查询
                searchjTextField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updatadrug();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updatadrug();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updatadrug();
                    }
                });


            }
        });

        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame internalFrame = new JInternalFrame("入库管理", true, true, true, true);
                internalFrame.setSize(new Dimension(500, 700));
                internalFrame.setLayout(null);
                internalFrame.setVisible(true);
                add(internalFrame);
                Container icontentpane = internalFrame.getContentPane();
                Vector rowData = PutinStorage.getRows();//取得药品表中的各行数据
                Vector columnNames = PutinStorage.getHead();//取得药品表的表头数据
                tableModel = new DefaultTableModel(rowData, columnNames);
                table = new JTable(tableModel)//表格不允许被编辑
                {
                    public boolean isCellEditable(int rowDate, int columnNames) {
                        return false;
                    }
                };
                table.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                        String a1 = table.getValueAt(table.getSelectedRow(), 0).toString();
                        String b1 = table.getValueAt(table.getSelectedRow(), 1).toString();
                        c1 = table.getValueAt(table.getSelectedRow(), 2).toString();
                        d1 = table.getValueAt(table.getSelectedRow(), 3).toString();
                        drugidofstockText.setText(a1);
                        drugidofstockText.setEditable(false);
                        drugnameofstockText.setText(b1);
                        drugnameofstockText.setEditable(false);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                table.setSize(500, 100);
                JScrollPane s = new JScrollPane(table);
                s.setSize(500, 100);
                s.setLocation(150, 120);
                icontentpane.add(s);
                searchdrugnameText.setBounds(320, 60, 150, 25);
                drugidofstockText.setBounds(80, 250, 150, 25);
                drugnameofstockText.setBounds(350, 250, 150, 25);
                drugquantityText.setBounds(620, 250, 100, 25);
                dateofstockText.setBounds(80, 300, 150, 25);
                priceofstockText.setBounds(350, 300, 150, 25);
                sellpriceofstockText.setBounds(620, 300, 100, 25);
                kwofstock.setBounds(200, 60, 200, 25);
                addofstock.setBounds(80, 350, 60, 25);
                resetofstock.setBounds(620, 350, 60, 25);
                L1.setBounds(20, 250, 70, 25);
                L2.setBounds(280, 250, 80, 25);
                L3.setBounds(550, 250, 60, 25);
                L4.setBounds(20, 300, 60, 25);
                L5.setBounds(280, 300, 60, 25);
                L6.setBounds(550, 300, 60, 25);
                icontentpane.add(kwofstock);
                icontentpane.add(addofstock);
                icontentpane.add(resetofstock);
                icontentpane.add(L1);
                icontentpane.add(L2);
                icontentpane.add(L3);
                icontentpane.add(L4);
                icontentpane.add(L5);
                icontentpane.add(L6);
                icontentpane.add(searchdrugnameText);
                icontentpane.add(drugidofstockText);
                icontentpane.add(drugnameofstockText);
                icontentpane.add(drugquantityText);
                icontentpane.add(dateofstockText);
                icontentpane.add(priceofstockText);
                icontentpane.add(sellpriceofstockText);
                ImageIcon icon=new ImageIcon("C:\\Users\\Administrator\\Desktop\\3.jpg");
                JLabel label1=new JLabel(icon);
                label1.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
                icontentpane.add(label1,new Integer(Integer.MIN_VALUE));
                ((JPanel) icontentpane).setOpaque(false);
            }
        });
        searchdrugnameText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatadrugofstock();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatadrugofstock();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatadrugofstock();
            }
        });
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame internalFrame = new JInternalFrame("会员查询", true, true, true, true);
                internalFrame.setSize(new Dimension(500, 700));
                internalFrame.setLayout(null);
                internalFrame.setVisible(true);
                Container icontentpane = internalFrame.getContentPane();
                add(internalFrame);
                Vector rowData = PutinVip.getRows();//取得药品表中的各行数据
                Vector columnNames = PutinVip.getHead();//取得药品表的表头数据
                tableModel = new DefaultTableModel(rowData, columnNames);
                table = new JTable(tableModel)//表格不允许被编辑
                {
                    public boolean isCellEditable(int rowDate, int columnNames) {
                        return false;
                    }
                };
                table.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                        String a = table.getValueAt(table.getSelectedRow(), 0).toString();
                        String b = table.getValueAt(table.getSelectedRow(), 1).toString();
                        String c = table.getValueAt(table.getSelectedRow(), 2).toString();
                        String d = table.getValueAt(table.getSelectedRow(), 3).toString();
                        String n = table.getValueAt(table.getSelectedRow(), 4).toString();
                        String f = table.getValueAt(table.getSelectedRow(), 5).toString();
                        vipidjTextField.setText(a);
                        vipidjTextField.setEditable(false);
                        vipnamejTextField.setText(b);
                        vipagejTextField.setText(c);
                        vipphonejTextField.setText(d);
                        vipemailjTextField.setText(n);
                        vipaddressjTextField.setText(f);

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

                table.setSize(500, 100);
                JScrollPane s = new JScrollPane(table);
                s.setSize(500, 100);
                s.setLocation(150, 120);
                icontentpane.add(s);

                vipidjTextField.setBounds(80, 250, 150, 25);
                vipnamejTextField.setBounds(350, 250, 150, 25);
                vipagejTextField.setBounds(620, 250, 150, 25);
                vipphonejTextField.setBounds(80, 300, 150, 25);
                vipemailjTextField.setBounds(350, 300, 150, 25);
                vipaddressjTextField.setBounds(620, 300, 150, 25);
                searchofvipText.setBounds(300, 60, 200, 25);
                kwofvip.setBounds(200, 60, 200, 25);
                VipID.setBounds(30, 250, 60, 25);
                VipName.setBounds(280, 250, 80, 25);
                VipAge.setBounds(580, 250, 60, 25);
                VipPhone.setBounds(30, 300, 60, 25);
                VipMessage.setBounds(280, 300, 60, 25);
                Vipadress.setBounds(580, 300, 60, 25);
                add2.setBounds(80, 350, 60, 25);
                altervip.setBounds(350, 350, 60, 25);
                delete2.setBounds(620, 350, 60, 25);
                icontentpane.add(searchofvipText);
                icontentpane.add(kwofvip);
                icontentpane.add(altervip);
                icontentpane.add(add2);
                icontentpane.add(delete2);
                icontentpane.add(VipID);
                icontentpane.add(VipName);
                icontentpane.add(VipAge);
                icontentpane.add(VipPhone);
                icontentpane.add(VipMessage);
                icontentpane.add(Vipadress);
                icontentpane.add(vipidjTextField);
                icontentpane.add(vipnamejTextField);
                icontentpane.add(vipagejTextField);
                icontentpane.add(vipphonejTextField);
                icontentpane.add(vipemailjTextField);
                icontentpane.add(vipaddressjTextField);
                ImageIcon icon=new ImageIcon("C:\\Users\\Administrator\\Desktop\\3.jpg");
                JLabel label1=new JLabel(icon);
                label1.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
                icontentpane.add(label1,new Integer(Integer.MIN_VALUE));
                ((JPanel) icontentpane).setOpaque(false);

            }
        });
        searchofvipText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updataVip();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updataVip();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updataVip();
            }
        });
        //会员添加按钮事件
        add2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLsever s = new SQLsever();
                s.ConnectSQL();
                String vipname = vipnamejTextField.getText();
                String vipage = vipagejTextField.getText();
                String vipphone = vipphonejTextField.getText();
                String vipemail = vipemailjTextField.getText();
                String vipaddress = vipaddressjTextField.getText();
                com.bean.Vip vip = new Vip();
                //自动形成有规律的VipID
                String vipid = "";
                int ID = 1;
                for (int i = 0; i < table.getRowCount(); i++) {
                    vipid = vip.getVipnum();
                    if (table.getRowCount() == 0) {
                        vipid = "0" + ID;
                        ++ID;
                    } else {
                        ++ID;
                        vipid = "0" + ID;
                    }
                }

                vip.setVipnum(vipid);
                vip.setVipname(vipname);
                vip.setVipphone(vipphone);
                vip.setVipemail(vipemail);
                vip.setVipaddress(vipaddress);
                vip.setVipage(vipage);
                s.AddVipVerity(vip);
            }
        });


        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame internalFrame = new JInternalFrame("出售药品", true, true, true, true);
                internalFrame.setSize(new Dimension(500, 700));
                internalFrame.setLayout(null);
                internalFrame.setVisible(true);
                Container icontentpane = internalFrame.getContentPane();
                Vector rowData = PutinStock.getRows();//取得药品表中的各行数据
                Vector columnNames = PutinStock.getHead();//取得药品表的表头数据
                tableModel = new DefaultTableModel(rowData, columnNames);
                table = new JTable(tableModel)//表格不允许被编辑
                {
                    public boolean isCellEditable(int rowDate, int columnNames) {
                        return false;
                    }
                };
                table.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                        String q = table.getValueAt(table.getSelectedRow(), 0).toString();
                        String q1 = table.getValueAt(table.getSelectedRow(), 2).toString();
                        String q2 = table.getValueAt(table.getSelectedRow(), 3).toString();
                        String q3 = table.getValueAt(table.getSelectedRow(), 5).toString();
                        stockidofsellText.setText(q);
                        stockidofsellText.setEditable(false);
                        mednameofsellText.setText(q1);
                        mednameofsellText.setEditable(false);
                        quantityofsellText.setText(q3);
                        quantityofsellText.setEditable(false);
                        unitpriceofsellText.setText(q2);
                        unitpriceofsellText.setEditable(false);
                        vipofsellText.setEditable(false);
                        totalpriceofsellText.setEditable(false);

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                table.setSize(500, 100);
                JScrollPane s = new JScrollPane(table);
                s.setSize(500, 100);
                s.setLocation(150, 120);
                icontentpane.add(s);
                add(internalFrame);
                kwofsell.setBounds(250,80,150,25);
                searchmedofsellText.setBounds(370,80,200,25);
                stockID.setBounds(20, 250, 50, 25);
                drugname.setBounds(230, 250, 60, 25);
                quantity.setBounds(450, 250, 50, 25);
                unitprice.setBounds(600, 250, 50, 25);
                VipID.setBounds(20, 290, 50, 25);
                count.setBounds(230, 290, 70, 25);
                Vip.setBounds(450, 290, 50, 25);
                totalprice.setBounds(600, 290, 50, 25);
                stockidofsellText.setBounds(80, 250, 100, 25);
                mednameofsellText.setBounds(300, 250, 100, 25);
                quantityofsellText.setBounds(500, 250, 60, 25);
                unitpriceofsellText.setBounds(640, 250, 60, 25);
                vipidofsellText.setBounds(80, 290, 100, 25);
                searchmedofsellText.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                             updataSell();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updataSell();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updataSell();
                    }
                });
                countofsellText.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        try
                        {
                            drugActionMoney();
                        }
                        catch (Exception e1)
                        {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
                vipidofsellText.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        if (!vipidofsellText.getText().equals("")) {
                            try {
                                isNotVip();
                            } catch (IllegalStateException e1) {
                                vipofsellText.setText("否");
                            }
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        if (vipidofsellText.getText().equals("")) {
                            vipofsellText.setText("否");
                        } else {

                            try {
                                isNotVip();
                            } catch (IllegalStateException e1) {
                                vipofsellText.setText("否");
                            }

                        }
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
                vipofsellText.setText("否");
                countofsellText.setBounds(300, 290, 100, 25);
                vipofsellText.setBounds(500, 290, 60, 25);
                totalpriceofsellText.setBounds(640, 290, 60, 25);
                resetorder.setBounds(200, 350, 100, 25);
                submitorder.setBounds(450, 350, 100, 25);
                icontentpane.add(kwofsell);
                icontentpane.add(searchmedofsellText);
                icontentpane.add(stockID);
                icontentpane.add(drugname);
                icontentpane.add(quantity);
                icontentpane.add(unitprice);
                icontentpane.add(VipID);
                icontentpane.add(count);
                icontentpane.add(Vip);
                icontentpane.add(totalprice);
                icontentpane.add(resetorder);
                icontentpane.add(submitorder);
                icontentpane.add(stockidofsellText);
                icontentpane.add(mednameofsellText);
                icontentpane.add(quantityofsellText);
                icontentpane.add(unitpriceofsellText);
                icontentpane.add(vipidofsellText);
                icontentpane.add(countofsellText);
                icontentpane.add(vipofsellText);
                icontentpane.add(totalpriceofsellText);
                ImageIcon icon=new ImageIcon("C:\\Users\\Administrator\\Desktop\\3.jpg");
                JLabel label1=new JLabel(icon);
                label1.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
                icontentpane.add(label1,new Integer(Integer.MIN_VALUE));
                ((JPanel) icontentpane).setOpaque(false);


            }
        });
        //有效期botton
        n1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame internalFrame = new JInternalFrame("有效期查询", true, true, true, true);
                internalFrame.setSize(new Dimension(500, 700));
                internalFrame.setLayout(null);
                internalFrame.setVisible(true);
                Container icontentpane = internalFrame.getContentPane();

                Vector rowData = Putinyouxiaoqi.getRows();//取得药品表中的各行数据
                Vector columnNames = Putinyouxiaoqi.getHead();//取得药品表的表头数据
                tableModel = new DefaultTableModel(rowData, columnNames);
                table = new JTable(tableModel)//表格不允许被编辑
                {
                    public boolean isCellEditable(int rowDate, int columnNames) {
                        return false;
                    }
                };
                table.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                        String m = table.getValueAt(table.getSelectedRow(), 0).toString();
                        String m1 = table.getValueAt(table.getSelectedRow(), 1).toString();
                        String m2 = table.getValueAt(table.getSelectedRow(), 3).toString();
                        stockidofyouxiaoqiText.setText(m);
                        mednameofyouxiaoqiText.setText(m1);
                        shengyuquantityText.setText(m2);
                        stockidofyouxiaoqiText.setEditable(false);
                        mednameofyouxiaoqiText.setEditable(false);
                        shengyuquantityText.setEditable(false);

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                table.setSize(500, 200);
                JScrollPane s = new JScrollPane(table);
                s.setSize(500, 120);
                s.setLocation(130, 100);
                icontentpane.add(s);
                add(internalFrame);
                searchofyouxiaoqi.setBounds(150,60,150,25);
                stocknum.setBounds(40, 250, 60, 25);
                drugnameofyouxiaoqi.setBounds(280, 250, 80, 25);
                shengyuquantity.setBounds(500, 250, 60, 25);
                stockidofyouxiaoqiText.setBounds(120, 250, 100, 25);
                mednameofyouxiaoqiText.setBounds(360, 250, 100, 25);
                shengyuquantityText.setBounds(570, 250, 100, 25);
                searchofyouxiaoqiText.setBounds(300,60,150,25);
                icontentpane.add(stockidofyouxiaoqiText);
                icontentpane.add(searchofyouxiaoqiText);
                icontentpane.add(mednameofyouxiaoqiText);
                icontentpane.add(shengyuquantityText);
                icontentpane.add(stocknum);
                icontentpane.add(drugnameofyouxiaoqi);
                icontentpane.add(shengyuquantity);
                icontentpane.add(searchofyouxiaoqi);
                searchofyouxiaoqiText.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updataYouxiaoqi();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updataYouxiaoqi();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updataYouxiaoqi();
                    }
                });
                ImageIcon icon=new ImageIcon("C:\\Users\\Administrator\\Desktop\\3.jpg");
                JLabel label1=new JLabel(icon);
                label1.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
                icontentpane.add(label1,new Integer(Integer.MIN_VALUE));
                ((JPanel) icontentpane).setOpaque(false);

            }
        });
        //盈利
        n2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JInternalFrame internalFrame = new JInternalFrame("利润查询", true, true, true, true);
                internalFrame.setSize(new Dimension(500, 700));
                internalFrame.setLayout(null);
                internalFrame.setVisible(true);
                Container icontentpane = internalFrame.getContentPane();
                Vector rowData = PutinProfit.getRows();//取得药品表中的各行数据
                Vector columnNames = PutinProfit.getHead();//取得药品表的表头数据
                tableModel = new DefaultTableModel(rowData, columnNames);
                table = new JTable(tableModel)//表格不允许被编辑
                {
                    public boolean isCellEditable(int rowDate, int columnNames) {
                        return false;
                    }
                };
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                SQLsever sss=new SQLsever();
                sss.ConnectSQL();
                String k1 = sss.selectPrfit();
                String k2 = sss.selectSell();
                Float k3=Float.parseFloat(k2)-Float.parseFloat(k1);
                totalinpriceText.setText(k1);
                totaloutpriceText.setText(k2);
                totalinpriceText.setEditable(false);
                totaloutpriceText.setEditable(false);
                profitText.setEditable(false);
                profitText.setText(String.valueOf(k3));
                totalinpriceText.setBounds(120, 300, 100, 25);
                totaloutpriceText.setBounds(350, 300, 100, 25);
                profitText.setBounds(570, 300, 100, 25);
                totalinprice.setBounds(40, 300, 60, 25);
                totaloutprice.setBounds(280, 300, 80, 25);
                profit.setBounds(500, 300, 60, 25);
                icontentpane.add(totalinprice);
                icontentpane.add(totaloutprice);
                icontentpane.add(profit);
                icontentpane.add(totalinpriceText);
                icontentpane.add(totaloutpriceText);
                icontentpane.add(profitText);
                table.setSize(500, 100);
                JScrollPane ss= new JScrollPane(table);
                ss.setSize(500, 100);
                ss.setLocation(130, 130);
                icontentpane.add(ss);
                add(internalFrame);
                ImageIcon icon=new ImageIcon("C:\\Users\\Administrator\\Desktop\\3.jpg");
                JLabel label1=new JLabel(icon);
                label1.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
                icontentpane.add(label1,new Integer(Integer.MIN_VALUE));
                ((JPanel) icontentpane).setOpaque(false);
            }
        });
        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contactKF = new JLabel("客服QQ：200928243", JLabel.CENTER);
                contactKF.setFont(new Font("宋体", Font.BOLD, 40));
                JInternalFrame internalFrame = new JInternalFrame("联系客服", true, true, true, true);
                internalFrame.setSize(new Dimension(500, 700));
                internalFrame.setLayout(new BorderLayout());
                internalFrame.setVisible(true);
                Container icontentpane = internalFrame.getContentPane();
                add(internalFrame);
                icontentpane.add(contactKF);
                ((JPanel) icontentpane).setOpaque(false);

            }
        });
        delete1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                int[] ids = table.getSelectedRows(); //返回选定行的索引
                BigInteger values[] = new BigInteger[ids.length];  //遍历选定行的数组
                for (int i = 0; i < ids.length; i++) {
                    //获取用户选择某单元格的内容
                    values[i] = new BigInteger(table.getValueAt(ids[i], 0).toString());
                }
                tableModel.removeRow(table.getSelectedRow());
                DeleteMed.deleteMed(values);
                table.setModel(tableModel);
                JOptionPane.showMessageDialog(null, "数据删除成功！", "提示信息", JOptionPane.WARNING_MESSAGE);
            }
        });
        delete2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                int[] ids = table.getSelectedRows(); //返回选定行的索引
                BigInteger values[] = new BigInteger[ids.length];  //遍历选定行的数组
                for (int i = 0; i < ids.length; i++) {
                    //获取用户选择某单元格的内容
                    values[i] = new BigInteger(table.getValueAt(ids[i], 0).toString());
                }
                tableModel.removeRow(table.getSelectedRow());
                DeleteVip.deletevip(values);
                table.setModel(tableModel);
                JOptionPane.showMessageDialog(null, "数据删除成功！", "提示信息", JOptionPane.WARNING_MESSAGE);
            }
        });
        ImageIcon icon=new ImageIcon("C:\\Users\\Administrator\\Desktop\\3.jpg");
        JLabel label1=new JLabel(icon);
        label1.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
         this.getLayeredPane().add(label1,new Integer(Integer.MIN_VALUE));
        ((JPanel) this.getContentPane()).setOpaque(false);
        setTitle("药房管理系统主界面");
        setSize(new Dimension(800, 500));
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());//设置窗口布局为流式布局

    }
//会员药品价格优惠
    private float isNotVip() {

        SQLsever sqLsever2 = new SQLsever();
        sqLsever2.ConnectSQL();
        if (sqLsever2.getVipnum(vipidofsellText.getText())) {
            vipofsellText.setText("是");
            per = (float) 0.9;

        } else {
            per = 1;
            vipofsellText.setText("否");
        }
        return per;
    }

    private void drugActionMoney() {
        String totalmount = quantityofsellText.getText();
        String buymount = countofsellText.getText();
        String unitprice = unitpriceofsellText.getText();
        float p1=Float.parseFloat(unitprice);
        float m=Float.parseFloat(buymount);
        float t=Float.parseFloat(totalmount);
        if (m <= t)
        {
            totalpriceofsellText.setText(String.valueOf(isNotVip()*m*p1));
            System.out.println(isNotVip()*m*p1);
        } else {
            JOptionPane.showMessageDialog(null, "购买数量不能大于库存量！");
        }
    }

    private void drugsearch(String sql,int i)
    {
        SQLsever sqLsever5=new SQLsever();
        conn = sqLsever5.Connect();
        ResultSet rs2;
        try
        {
            int rowcount=tableModel.getRowCount()-1;
            if(rowcount !=-1)
            {
                for (int i1=rowcount;i1>=0;i1--)
                {
                    tableModel.removeRow(i1);
                }
                tableModel.setRowCount(0);
            }
            Statement statement=conn.createStatement();
            rs2=statement.executeQuery(sql);
            String [] data=new String[4];
            while(rs2.next())
            {
                for (int j=1;j<=4;j++)
                {
                    data[j-1]= rs2.getString(j);
                }
                tableModel.addRow(data);
            }
            conn.close();
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
    }

    private void vipsearch(String sql,int i)
    {
        SQLsever sqLsever5=new SQLsever();
        conn = sqLsever5.Connect();
        ResultSet rs2;
        try
        {
            int rowcount=tableModel.getRowCount()-1;
            if(rowcount !=-1)
            {
                for (int i1=rowcount;i1>=0;i1--)
                {
                    tableModel.removeRow(i1);
                }
                tableModel.setRowCount(0);
            }
            Statement statement=conn.createStatement();
            rs2=statement.executeQuery(sql);
            String [] data=new String[6];
            while(rs2.next())
            {
                for (int j=1;j<=6;j++)
                {
                    data[j-1]= rs2.getString(j);
                }
                tableModel.addRow(data);
            }
            conn.close();
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
    }

    private void sellsearch(String sql,int i)
    {
        SQLsever sqLsever5=new SQLsever();
        conn = sqLsever5.Connect();
        ResultSet rs3;
        try
        {
            int rowcount=tableModel.getRowCount()-1;
            if(rowcount !=-1)
            {
                for (int i1=rowcount;i1>=0;i1--)
                {
                    tableModel.removeRow(i1);
                }
                tableModel.setRowCount(0);
            }
            Statement statement=conn.createStatement();
            rs3=statement.executeQuery(sql);
            String [] data=new String[6];
            while(rs3.next())
            {
                for (int j=1;j<=6;j++)
                {
                    data[j-1]= rs3.getString(j);
                }
                tableModel.addRow(data);
            }
            conn.close();
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
    }
    private void youxiaoqisearch(String sql,int i)
    {
        SQLsever sqLsever6=new SQLsever();
        conn = sqLsever6.Connect();
        ResultSet rs3;
        try
        {
            int rowcount=tableModel.getRowCount()-1;
            if(rowcount !=-1)
            {
                for (int i1=rowcount;i1>=0;i1--)
                {
                    tableModel.removeRow(i1);
                }
                tableModel.setRowCount(0);
            }
            Statement statement=conn.createStatement();
            rs3=statement.executeQuery(sql);
            String [] data=new String[5];
            while(rs3.next())
            {
                for (int j=1;j<=5;j++)
                {
                    data[j-1]= rs3.getString(j);
                }
                tableModel.addRow(data);
            }
            conn.close();
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
    }

    private void updatadrug()
    {
        String s1 = null;
        s1 = searchjTextField.getText();
        String sql="select * from 药品 where Dname like '%"+s1+"%'";
        drugsearch(sql,5);
    }
    private void updatadrugofstock()
    {
        String s1 = null;
        s1 = searchdrugnameText.getText();
        String sql1="select * from 药品 where Dname like '%"+s1+"%'";
        drugsearch(sql1,5);
    }
    private void updataVip()
    {
        String s2=null;
        s2=searchofvipText.getText();
        String sql2="select * from 会员 where Cphone like '%"+s2+"%'";
        vipsearch(sql2,7);
    }
    private void updataSell()
    {
        String s3=null;
        s3=searchmedofsellText.getText();
        String sql3="select * from v_库存表 where Dname like '%"+s3+"%'";
        sellsearch(sql3,7);
    }
    private void updataYouxiaoqi()
    {
        String s4=null;
        s4=searchmedofsellText.getText();
        String sql3="select * from v_youxiaoqi where Dname like '%"+s4+"%'";
        youxiaoqisearch(sql3,6);
    }
    public static void main(String[] args) {
        new HomeScreen().setVisible(true);
    }
}





