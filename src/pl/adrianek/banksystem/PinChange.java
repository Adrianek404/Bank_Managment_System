package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {

    String pinnumber, numbercard;

    JButton change, back;
    JTextField pin, repin;
    PinChange(String pinnumber, String numbercard){
        this.pinnumber = pinnumber;
        this.numbercard = numbercard;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Zmień swój PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        JLabel pintext = new JLabel("Nowy PIN:");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 320, 180, 35);
        image.add(pintext);

        pin = new JTextField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330, 320, 180, 25);
        image.add(pin);

        JLabel repintext = new JLabel("Powtórz nowy PIN:");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 360, 180, 35);
        image.add(repintext);

        repin = new JTextField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(330, 360, 180, 25);
        image.add(repin);

        change = new JButton("Zmień");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Powrót");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(change)){
            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)){
                    JOptionPane.showMessageDialog(null, "Wprowadzone PINy nie są takie same");
                    return;
                }
                if (npin.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Proszę wpisać nowy PIN");
                    return;
                }
                if (rpin.equals("")){
                    JOptionPane.showMessageDialog(null, "Proszę wpisać nowy PIN");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "update login set pin ='"+rpin+"' where pin='"+pinnumber+"' and cardnumber ='"+numbercard+"'";
                String query2 = "update signupthree set pin ='"+rpin+"' where pin='"+pinnumber+"' and cardnumber ='"+numbercard+"'";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Kod PIN został pomyślnie zmieniony");
                setVisible(false);
                new Transactions(rpin, numbercard).setVisible(true);

            } catch (Exception ex){
                System.out.println(ex);
            }
        } else {
            setVisible(false);
            new Transactions(pinnumber, numbercard).setVisible(true);
        }
    }
}
