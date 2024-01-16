package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, back;
    String pinnumber, numbercard;
    Deposit(String pinnumber, String numbercard) {
        this.pinnumber = pinnumber;
        this.numbercard = numbercard;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Wpisz kwotę jaką chcesz do wpłacenia");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System" ,Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        amount.addKeyListener(new NumericKeyListener(amount));
        image.add(amount);

        deposit = new JButton("Wpłać");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Powrót");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setUndecorated(true);
        setLocation(300,0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(deposit)){
            deposit();
        } else if (e.getSource().equals(back)){
            setVisible(false);
            new Transactions(pinnumber, numbercard).setVisible(true);
        }
    }

    private void deposit() {
        String number = amount.getText();
        Date date = new Date();
        int[] nominaly = {500, 200, 100, 50, 20, 10};
        if (number.equals("")) {
            JOptionPane.showMessageDialog(null, "Proszę wpisać kwotę jaką chcesz wpłacić");
        } else {
            int realNumber = Integer.parseInt(number);
            if (realNumber % nominaly[nominaly.length - 1] == 0) {
                int sprawdzKwote = realNumber;
                StringBuilder uzyteNominaly = new StringBuilder();

                for (int nominal : nominaly) {
                    int iloscNominalow = sprawdzKwote / nominal;
                    sprawdzKwote -= iloscNominalow * nominal;

                    if (iloscNominalow > 0) {
                        if (uzyteNominaly.length() > 0) {
                            uzyteNominaly.append(", ");
                        }
                        uzyteNominaly.append(iloscNominalow).append("x ").append(nominal).append(" zł");
                    }
                }

                if (sprawdzKwote == 0) {
                    try {
                        Conn conn = new Conn();
                        ResultSet rs = conn.s.executeQuery("select balance from login where pin = '" + pinnumber + "' and cardnumber='" + numbercard + "'");
                        String balance = null;
                        while (rs.next()) {
                            balance = String.valueOf(realNumber + Integer.parseInt(rs.getString("balance")));
                        }
                        String query1 = "UPDATE login SET balance='" + balance + "' WHERE cardnumber = '" + numbercard + "' and pin = '" + pinnumber + "'";
                        conn.s.executeUpdate(query1);
                        String query = "insert into bank values('" + pinnumber + "','" + numbercard + "', '" + date + "', 'Deposit', '" + number + "')";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Wpłacono kwotę " + number + " zł na konto pomyślnie\n"+uzyteNominaly);
                        setVisible(false);
                        new Transactions(pinnumber, numbercard);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Proszę wpisać poprawną kwotę jaką chcesz wpłacić");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Proszę wpisać poprawną kwotę jaką chcesz wpłacić");
            }
        }
    }
}
