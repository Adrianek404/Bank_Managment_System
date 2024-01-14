package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login(){
        setTitle("Bankomat");

        setLayout(null);

        ImageIcon iI = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = iI.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel WelcomeText = new JLabel("Witaj w bankomacie");
        WelcomeText.setFont(new Font("Osward", Font.BOLD, 38));
        WelcomeText.setBounds(200, 40, 400, 50);
        add(WelcomeText);

        JLabel CardNb = new JLabel("Numer karty: ");
        CardNb.setFont(new Font("Raleway", Font.BOLD, 28));
        CardNb.setBounds(120, 150, 400, 30);
        add(CardNb);

        cardTextField = new JTextField();
        cardTextField.setBounds(310, 150, 250, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        cardTextField.addKeyListener(new NumericKeyListener(cardTextField));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(310, 220, 250, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("ZALOGUJ");
        login.setBounds(300, 300, 100, 30);
        login.setBorderPainted(false);
        login.setOpaque(true);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        clear = new JButton("WYCZYŚĆ");
        clear.setBounds(420, 300, 110, 30);
        clear.setBorderPainted(false);
        clear.setOpaque(true);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("ZAREJESTRUJ");
        signup.setBounds(300, 350, 230, 30);
        signup.setBorderPainted(false);
        signup.setOpaque(true);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 500);
        setVisible(true);
        setLocation(350, 200);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (e.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '" +cardnumber+"' and pin = '"+pinnumber+"'";
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber, cardnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Nie poprawny numer karty lub pin");
                }
            } catch (Exception ex){
                System.out.println(ex);
            }
        } else if (e.getSource() == signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
