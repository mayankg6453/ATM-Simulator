/*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */

package ASimulatorSystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Mayank
 */

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
    Login(){
        
        setTitle("Automated Teller Machine");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.jpg"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel lab = new JLabel(i3);
		lab.setBounds(100, 10, 100, 100);
		add(lab);
		
        l1=new JLabel("Welcome to ATM");
        l1.setFont(new Font("Osward",Font.BOLD,38));
        
        
        l2=new JLabel("Card No");
        l2.setFont(new Font("Raleway",Font.BOLD,28));
        

        l3=new JLabel("PIN");
        l3.setFont(new Font("Raleway",Font.BOLD,28));
        
        tf1= new JTextField(15);
        pf2= new JPasswordField(15);
        
        b1= new JButton("Sign In");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2= new JButton("Clear");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3= new JButton("Sign UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        setLayout(null);
        
        l1.setBounds(250,40,450,40);
        add(l1);
        
        l2.setBounds(110,150,375,30);
        add(l2);
        
        tf1.setFont(new Font("Arial",Font.BOLD,14));
        tf1.setBounds(270, 150, 375, 30);
        add(tf1);
        
        l3.setBounds(110,220,375,30);
        add(l3);
        
        pf2.setFont(new Font("Arial",Font.BOLD,14));
        pf2.setBounds(270, 220, 375, 30);
        add(pf2);
        
        
        b1.setFont(new Font("Arial",Font.BOLD,14));
        b1.setBounds(270,300,100,30);
        add(b1);
        
        b2.setFont(new Font("Arial",Font.BOLD,14));
        b2.setBounds(400,300,100,30);
        add(b2);
        
        b3.setFont(new Font("Arial",Font.BOLD,14));
        b3.setBounds(270,350,230,30);
        add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.white);
        
        setSize(750,500);
        setLocation(400,180);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            
            if(ae.getSource()==b1){
            	Conn c1=new Conn();
                String a=tf1.getText();
                String b=pf2.getText();
                String q = "select * from login where cardno = '"+a+"' and pin = '"+b+"'";
                ResultSet rs=c1.s.executeQuery(q);
                
                if(rs.next()){
                    new Transactions(b).setVisible(true);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect credentials");
                }
            }else if(ae.getSource()==b2){
                tf1.setText("");
                pf2.setText("");
            }else{
                new Signup().setVisible(true);
                setVisible(false);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("errors: "+e);
        }
    
    }
    public static void main(String[] args){
        new Login().setVisible(true);
    }
}
