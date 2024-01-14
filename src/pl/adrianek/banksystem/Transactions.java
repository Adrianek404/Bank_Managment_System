package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit, withdraw1, fastcash, ministatemnt, pinchange, balance, exit;
    String pinnumber, numbercard;
    Transactions(String pinnumber, String numbercard){
        this.pinnumber = pinnumber;
        this.numbercard = numbercard;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Proszę wybrać typ operacji");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        deposit = new JButton("Wpłać");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdraw1 = new JButton("Wypłata gotówki");
        withdraw1.setBounds(355, 415, 150, 30);
        withdraw1.addActionListener(this);
        image.add(withdraw1);

        fastcash = new JButton("Szybka wypłata");
        fastcash.setBounds(170, 450, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatemnt = new JButton("Rachunek konta");
        ministatemnt.setBounds(355, 450, 150, 30);
        ministatemnt.addActionListener(this);
        image.add(ministatemnt);

        pinchange = new JButton("Zmiana PINu");
        pinchange.setBounds(170, 485, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balance = new JButton("Saldo konta");
        balance.setBounds(355, 485, 150, 30);
        balance.addActionListener(this);
        image.add(balance);

        exit = new JButton("Wyjście");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            System.exit(0);
        } else if (e.getSource().equals(deposit)){
            setVisible(false);
            new Deposit(pinnumber, numbercard).setVisible(true);
        } else if (e.getSource().equals(withdraw1)){
            setVisible(false);
            new Withdraw1(pinnumber, numbercard).setVisible(true);
        } else if (e.getSource().equals(fastcash)){
            setVisible(false);
            new FastCash(pinnumber, numbercard).setVisible(true);
        } else if (e.getSource().equals(pinchange)){
            setVisible(false);
            new PinChange(pinnumber, numbercard).setVisible(true);
        } else if (e.getSource().equals(balance)){{
            setVisible(false);
            new BalanceEnquiry(pinnumber, numbercard).setVisible(true);
        }}
    }
}
