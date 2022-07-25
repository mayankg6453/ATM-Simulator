package ASimulatorSystem;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.util.*;

public class Deposite extends JFrame implements ActionListener {

	JTextField t1,t2;
	JButton b1,b2,b3;
	JLabel l1,l2,l3;
	String pin;
	Deposite(String pin){
		this.pin=pin;
		
		setTitle("Deposit");

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel lab = new JLabel(i3);
		lab.setBounds(0, 0, 960, 1080);
		add(lab);
		
		l1=new JLabel("Enter amount you want to deopist");
		l1.setForeground(Color.white);
		l1.setFont(new Font("System",Font.BOLD,16));

		t1=new JTextField();
		t1.setFont(new Font("System",Font.BOLD,22));

		b1=new JButton ("Depoit");

		b2=new JButton ("Go back");

		b3=new JButton ("Exit");

		setLayout(null);


		t1.setBounds(200,450,300,35);
		lab.add(t1);
		

		l1.setBounds(225,400,700,35);
		lab.add(l1);

		b1.setBounds(170, 588, 150, 35);
		lab.add(b1);

		b2.setBounds(390, 588, 150, 35);
		lab.add(b2);
		b3.setBounds(390, 633, 150, 35);
		lab.add(b3);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		getContentPane().setBackground(Color.white);
		setSize(960,1080);
		setLocation(250,0);
		setUndecorated(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			String amount=t1.getText();
			Date date = new Date();
			if(ae.getSource()==b1) {
				
				if(amount.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Valid Anount");

				}else {
					Conn c1=new Conn();
					String q="insert into bank values('"+pin+"','"+date+"','Deposit','"+amount+"')";

					c1.s.executeUpdate(q);
					JOptionPane.showMessageDialog(null, "Rs. "+amount+" deposited successfully");				
					setVisible(false);
					new Transactions(pin).setVisible(true);
				}
			}
			else if(ae.getSource()==b2) {
				new Transactions(pin).setVisible(true);
				setVisible(false);
			}else if (ae.getSource()==b3) {
				System.exit(0);
			}

		}catch(Exception e) {
			e.printStackTrace();

		}

	}
	public static void main(String[] args) {
		new Deposite("").setVisible(true);
	}

}
