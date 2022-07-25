package ASimulatorSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Withdrawl  extends JFrame implements ActionListener{


	JTextField t1,t2;
	JButton b1,b2,b3;
	JLabel l1,l2,l3;
	String pin;
	Withdrawl(String pin){
		this.pin=pin;
		setFont(new Font("System",Font.BOLD,22));

		setTitle("Withdrawl");
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel lab = new JLabel(i3);
		lab.setBounds(0, 0, 960, 1080);
		add(lab);


		l1=new JLabel("Daily Limit Rs.10,000");
		l1.setForeground(Color.white);
		l1.setFont(new Font("System",Font.BOLD,16));

		l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("System", Font.BOLD, 16));


		t1=new JTextField();
		t1.setFont(new Font("System",Font.BOLD,14));

		b1=new JButton ("Withdraw");

		b2=new JButton ("Go back");

		b3=new JButton ("Exit");

		setLayout(null);

		l2.setBounds(225,400,700,35);
		lab.add(l2);
		
		t1.setBounds(200,450,300,35);
		lab.add(t1);

		l1.setBounds(270,350,700,35);
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

			if(ae.getSource()==b1) {
				String amount=t1.getText();
				Date date = new Date();
				if(amount.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Valid Anount");

				}else {
					Conn c1=new Conn();
					ResultSet rs=c1.s.executeQuery("Select * from bank where pin = '"+pin+"'");
					int bal=0;
					while(rs.next()) {
						if(rs.getString("mode").equals("Deposit")) {
							bal+=Integer.parseInt(rs.getString("amount"));
						}if(rs.getString("mode").equals("Withdrawl")) {
							bal-=Integer.parseInt(rs.getString("amount"));
						}
					}
					if(bal<Integer.parseInt(amount)) {
						JOptionPane.showMessageDialog(null, "Insufficient Balance");
						return;
					}
					c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
					
					setVisible(false);
					new Transactions(pin).setVisible(true);
				}
			}
			else if(ae.getSource()==b2) {
				setVisible(false);
				new Transactions(pin).setVisible(true); 
			}else if (ae.getSource()==b3) {
				System.exit(0);
			}

		}catch(Exception e) {
			e.printStackTrace();

		}

	}
	public static void main(String[] args) {
		new Withdrawl("").setVisible(true);
	}

}
