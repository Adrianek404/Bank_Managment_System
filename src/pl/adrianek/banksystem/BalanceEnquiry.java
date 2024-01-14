package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pinnumber, numbercard;

    JButton back;
    BalanceEnquiry(String pinnumber, String numbercard){
        setLayout(null);

        this.pinnumber = pinnumber;
        this.numbercard = numbercard;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        back = new JButton("Powrót");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        String balance = "0";
        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select balance from login where pin = '"+pinnumber+"' and cardnumber='"+numbercard+"'");
            while (rs.next()) {
                balance = rs.getString("balance");
            }
        }catch(Exception ignored){}

        JLabel text = new JLabel("Twój aktalny stan konta: " + balance + " zł");
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 400, 30);
        image.add(text);

        setSize(900, 900);
        setLocation(300 ,0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transactions(pinnumber, numbercard).setVisible(true);
    }
}
