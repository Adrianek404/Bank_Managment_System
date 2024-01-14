package pl.adrianek.banksystem;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumericKeyListener extends KeyAdapter {

    private JTextField tf;

    public NumericKeyListener(JTextField tf){
        this.tf = tf;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String value = tf.getText();
        char typedChar = e.getKeyChar();
        if (!(Character.isDigit(typedChar) ||
                (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) ||
                ((e.getModifiers() & InputEvent.CTRL_MASK) != 0 && (typedChar == 'v' || typedChar == 'V')))) {
            e.consume();
            tf.setEditable(false);
        } else {
            tf.setEditable(true);
        }
    }
}
