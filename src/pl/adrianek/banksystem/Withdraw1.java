package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Withdraw1  extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;
    String pinnumber, numbercard;
    Withdraw1(String pinnumber, String numbercard) {
        this.pinnumber = pinnumber;
        this.numbercard = numbercard;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Wpisz kwotę jaką chcesz wypłacić");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System" ,Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        withdraw = new JButton("Wypłać");
        withdraw.setBounds(355, 485, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);

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
        if (e.getSource().equals(withdraw)){
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")){
                JOptionPane.showMessageDialog(null,"Proszę wpisać kwotę jaką chcesz wypłacić");
            } else {
                try {
                    Conn conn = new Conn();
                    String query = "insert into bank values('" + pinnumber + "', '" + date + "', 'Withdraw1', '" + amount + "')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Wypłacono kwotę " + amount + "zł z konta pomyślnie");
                    setVisible(false);
                    new Transactions(pinnumber, numbercard);
                } catch (Exception ex){
                    System.out.println(ex);
                }
            }
        } else if (e.getSource().equals(back)){
            setVisible(false);
            new Transactions(pinnumber, numbercard).setVisible(true);
        }
    }
}
