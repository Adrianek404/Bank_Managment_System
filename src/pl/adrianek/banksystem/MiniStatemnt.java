package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class MiniStatemnt extends JFrame {

    String pinnumber, numbercard;

    MiniStatemnt(String pinnumber, String numbercard) {
        setTitle("Wyciąg bankowy");

        setLayout(null);

        JLabel mini = new JLabel();
        add(mini);

        JLabel bank = new JLabel("Twój bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);

        JLabel card = new JLabel("Numer karty: "+ numbercard.substring(0,4) +"-"+ numbercard.substring(4,8) +"-"+ numbercard.substring(8,12) +"-"+ numbercard.substring(12));
        card.setBounds(20, 80, 300, 20);
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(20, 450, 300, 20);
        add(balance);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"' and cardnumber = '"+numbercard+"'");
            while (rs.next()){
                mini.setText(mini.getText() + "<html> " + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><html>");

            }
            ResultSet rs2 = conn.s.executeQuery("select balance from login where pin = '"+pinnumber+"' and cardnumber='"+numbercard+"'");
            while (rs2.next()) {
                balance.setText("Aktualny stan konta: "+ rs2.getString("balance"));
            }
        } catch (Exception e){
            System.out.println(e);
        }

        mini.setBounds(20, 100, 400, 400);

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
}
