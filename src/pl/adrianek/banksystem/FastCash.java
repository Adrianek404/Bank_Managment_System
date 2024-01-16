package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton pln50, pln100, pln200, pln500, exit;
    String pinnumber, numbercard;

    FastCash(String pinnumber, String numbercard) {
        this.pinnumber = pinnumber;
        this.numbercard = numbercard;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Proszę wybrać kwotę do wypłaty");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        pln50 = new JButton("50 zł");
        pln50.setBounds(170, 415, 150, 30);
        pln50.addActionListener(this);
        image.add(pln50);

        pln100 = new JButton("100 zł");
        pln100.setBounds(355, 415, 150, 30);
        pln100.addActionListener(this);
        image.add(pln100);

        pln200 = new JButton("200 zł");
        pln200.setBounds(170, 450, 150, 30);
        pln200.addActionListener(this);
        image.add(pln200);

        pln500 = new JButton("500 zł");
        pln500.setBounds(355, 450, 150, 30);
        pln500.addActionListener(this);
        image.add(pln500);

        exit = new JButton("Powrót");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            setVisible(false);
            new Transactions(pinnumber, numbercard).setVisible(true);
        }
        if (e.getSource().equals(pln50)) {
            performWithdrawal(50);
        } else if (e.getSource().equals(pln100)) {
            performWithdrawal(100);
        } else if (e.getSource().equals(pln200)) {
            performWithdrawal(200);
        } else if (e.getSource().equals(pln500)) {
            performWithdrawal(500);
        }
    }

    public void performWithdrawal(int amount) {
        Date date = new Date();
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select balance from login where pin = '" + pinnumber + "' and cardnumber='" + numbercard + "'");
            int balance = 0;
            while (rs.next()) {
                balance = Integer.parseInt(rs.getString("balance"));
            }
            if (amount > balance) {
                JOptionPane.showMessageDialog(null, "Nie można wypłacić kwoty, brak środków na koncie");
                return;
            }
            balance -= amount;
            String query1 = "UPDATE login SET balance='" + balance + "' WHERE cardnumber = '" + numbercard + "' and pin = '" + pinnumber + "'";
            conn.s.executeUpdate(query1);
            String query = "insert into bank values('" + pinnumber + "','" + numbercard + "', '" + date + "', 'Withdraw1', '" + amount + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Wypłacono kwotę " + amount + "zł z konta pomyślnie");
            setVisible(false);
            new Transactions(pinnumber, numbercard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
