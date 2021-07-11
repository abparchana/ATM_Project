package atm2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class balance {

	private JFrame frmAtmManagmentSystem;
	private JTextField accNum;
	private JTextField urBalance;
	
	String url = "jdbc:mysql://localhost:3306/atm_reg";
	String user = "root";
	String password = "admin";
	 static int preaccnum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					balance window = new balance(preaccnum);
					window.frmAtmManagmentSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public balance(int accnum) {
		preaccnum=accnum;
		initialize();
		getconnect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtmManagmentSystem = new JFrame();
		frmAtmManagmentSystem.setTitle(" ATM Managment System");
		frmAtmManagmentSystem.setBounds(100, 100, 602, 428);
		frmAtmManagmentSystem.setVisible(true);
		frmAtmManagmentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtmManagmentSystem.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 11, 501, 345);
		frmAtmManagmentSystem.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Number");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(52, 102, 131, 33);
		panel.add(lblNewLabel);
		
		JLabel lblYourBalance = new JLabel("Your Balance");
		lblYourBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYourBalance.setBounds(52, 160, 146, 33);
		panel.add(lblYourBalance);
		
	
		
		JLabel lblSignupForm = new JLabel("Available Balance");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(195, 47, 117, 22);
		panel.add(lblSignupForm);
		
		
		JLabel lblAtmManagementSystem = new JLabel("                   ATM Managment System");
		lblAtmManagementSystem.setBackground(Color.PINK);
		lblAtmManagementSystem.setForeground(new Color(160, 82, 45));
		lblAtmManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAtmManagementSystem.setBounds(10, 11, 513, 33);
		panel.add(lblAtmManagementSystem);
		
		accNum = new JTextField();
		accNum.setEditable(false);
		accNum.setBounds(246, 102, 151, 34);
		panel.add(accNum);
		accNum.setColumns(10);
		
		urBalance = new JTextField();
		urBalance.setEditable(false);
		urBalance.setBounds(246, 161, 151, 33);
		panel.add(urBalance);
		urBalance.setColumns(10);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				new mainMenu(preaccnum);
			}
		});
		btnBack.setBounds(156, 224, 89, 44);
		panel.add(btnBack);
	
			}
	
	public Connection getconnect(){
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(url, user, password);
			 st = con.createStatement();
			 String sql="select Balance from atm_reg.atm_table where AccNum='"+ preaccnum+"' ";
			 rs=st.executeQuery(sql);
			 rs.next();
			 accNum.setText(Integer.toString(preaccnum));
			 urBalance.setText(Integer.toString(rs.getInt("Balance")));
			 System.out.println(rs.getInt("Balance"));
			 
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;}
	
	
}
