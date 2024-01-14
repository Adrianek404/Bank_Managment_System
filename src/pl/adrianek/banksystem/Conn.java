package pl.adrianek.banksystem;

import java.sql.*;
import java.util.Stack;

public class Conn {

    Connection c;
    Statement s;
    public Conn(){
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "");
            s = c.createStatement();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
