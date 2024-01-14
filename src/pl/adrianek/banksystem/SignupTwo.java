package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupTwo extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    Random random = new Random();
    String cardnumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);
    String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);
    String formno;

    public SignupTwo(String formno) {
        this.formno = formno;

        setLayout(null);

        JLabel l1 = new JLabel("Strona 2: Detale");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Typ konta:");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 140, 200, 30);
        add(type);

        r1 = new JRadioButton("Konto Oszczędniościowe");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100, 180, 250, 20);
        add(r1);

        r2 = new JRadioButton("Konto osobiste");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 180, 250, 20);
        add(r2);

        r3 = new JRadioButton("Konto firmowe");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 220, 250, 20);
        add(r3);

        r4 = new JRadioButton("Konto walutowe");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setBackground(Color.WHITE);
        r4.setBounds(350, 220, 250, 20);
        add(r4);

        ButtonGroup groupAccount = new ButtonGroup();
        groupAccount.add(r1);
        groupAccount.add(r2);
        groupAccount.add(r3);
        groupAccount.add(r4);

        JLabel card = new JLabel("Numer karty:");
        card.setFont(new Font("Raleway", Font.BOLD, 22));
        card.setBounds(100, 300, 200, 30);
        add(card);

        JLabel numer = new JLabel(cardnumber.substring(0,4) +"-"+ cardnumber.substring(4,8) +"-"+ cardnumber.substring(8,12) +"-"+ cardnumber.substring(12));
        numer.setFont(new Font("Raleway", Font.BOLD, 22));
        numer.setBounds(330, 300, 300, 30);
        add(numer);

        JLabel cardDetails = new JLabel("Twój 16-cyfrowy nr karty");
        cardDetails.setFont(new Font("Raleway", Font.BOLD, 12));
        cardDetails.setBounds(100, 330, 300, 20);
        add(cardDetails);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 22));
        pin.setBounds(100, 370, 200, 30);
        add(pin);

        JLabel pnumer = new JLabel(pinnumber);
        pnumer.setFont(new Font("Raleway", Font.BOLD, 22));
        pnumer.setBounds(330, 370, 300, 30);
        add(pnumer);

        JLabel pinDetails = new JLabel("Twój 4-cyfrowy kod karty");
        pinDetails.setFont(new Font("Raleway", Font.BOLD, 12));
        pinDetails.setBounds(100, 400, 300, 20);
        add(pinDetails);

        JLabel services = new JLabel("Wymagane Usługi:");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 450, 250, 30);
        add(services);

        c1 = new JCheckBox("Karta rzeczywista");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100, 500, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internetowa pomoc");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(350, 500, 200, 30);
        add(c2);

        c3 = new JCheckBox("Zwrot Cashback'u");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 550, 200, 30);
        add(c3);

        c4 = new JCheckBox("Alerty SMS & E-mail");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350, 550, 200, 30);
        add(c4);

        c5 = new JCheckBox("Blik");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 600, 200, 30);
        add(c5);

        c6 = new JCheckBox("Tylko na terenie kraju");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setBounds(350, 600, 250, 30);
        add(c6);

        c7 = new JCheckBox("Potwierdzam że wprowadzone dane są poprawne i znam ich znaczenie");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBounds(100, 680, 600, 30);
        add(c7);

        submit = new JButton("Potwierdź");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 720, 120, 30);
        submit.setBorderPainted(false);
        submit.setOpaque(true);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Anuluj");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420, 720, 100, 30);
        cancel.setBorderPainted(false);
        cancel.setOpaque(true);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 820);
        setLocation(350, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String accountType = null;
            if (r1.isSelected()) {
                accountType = "Konto Oszczędniościowe";
            } else if (r2.isSelected()) {
                accountType = "Konto Osobiste";
            } else if (r3.isSelected()) {
                accountType = "Konto Firmowe";
            } else if (r4.isSelected()) {
                accountType = "Konto Walutowe";
            }

            String facility = "";
            if (c1.isSelected()){
                facility += " Karta rzeczywista";
            }
            if (c2.isSelected()){
                facility += " Internetowa pomoc";
            }
            if (c3.isSelected()){
                facility += " Zwrot Cashbacku";
            }
            if (c4.isSelected()){
                facility += " Alerty SMS & E-mail";
            }
            if (c5.isSelected()){
                facility += " Blik";
            }
            if (c6.isSelected()){
                facility += " Tylko na terenie kraju";
            }

            try{
                if (accountType == null){
                    JOptionPane.showMessageDialog(null,"Typ konta jest wymagany, proszę wybrać");
                } else {
                    Conn conn = new Conn();
                    String q1 = "insert into signupthree values('"+formno+"','"+accountType+"','"+cardnumber+"','"+pinnumber+"','"+facility+"')";
                    String q2 = "insert into login values('"+formno+"','"+cardnumber+"','"+pinnumber+"')";
                    conn.s.executeUpdate(q1);
                    conn.s.executeUpdate(q2);
                    setVisible(false);
                    new Transactions(pinnumber, cardnumber).setVisible(true);
                }
            } catch (Exception ex){
                System.out.println(ex);
            }

        } else if (e.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }
}
