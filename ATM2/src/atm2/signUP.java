package atm2;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.SystemColor;
import javax.swing.JTextPane;

public class signUP {

	private JFrame frame;
	private JTextField accNum;
	private JTextField AccName;
	private JTextField name;
	private JTextArea addressnew;
	private JTextField pin;
	private JTextField occupation;
	private JTextField Phone;
	private JComboBox comboBoxEdu;

	 String url = "jdbc:mysql://localhost:3306/atm_reg";
	 String user = "root";
	 String password = "admin";
	private JTextField dob;
	private Container getContentPane;
	private JTextArea txtrDfdsfsfs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signUP window = new signUP();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public signUP() {
		initialize();
	}
	
	public void clear(){
		accNum.setText("");
		AccName.setText("");
		name.setText("");
		dob.setText("");
		Phone.setText("");
		addressnew.setText("");
		occupation.setText("");
		pin.setText("");
		comboBoxEdu.setSelectedItem(-1);
		
		
		//comboBoxEdu
		
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ATM Managment System");
		frame.setBounds(100, 100, 554, 448);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("                   ATM Managment System");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 513, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Account Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 83, 127, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Account Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 149, 97, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		accNum = new JTextField();
		accNum.setBounds(138, 81, 117, 20);
		frame.getContentPane().add(accNum);
		accNum.setColumns(10);
		
		AccName = new JTextField();
		AccName.setBounds(138, 147, 117, 20);
		frame.getContentPane().add(AccName);
		AccName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 209, 86, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		name = new JTextField();
		name.setBounds(138, 207, 117, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblPin = new JLabel("Pin");
		lblPin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPin.setBounds(275, 83, 53, 14);
		frame.getContentPane().add(lblPin);
		
		pin = new JTextField();
		pin.setBounds(372, 81, 108, 20);
		frame.getContentPane().add(pin);
		pin.setColumns(10);
		
		JLabel lblEducation = new JLabel("Education");
		lblEducation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEducation.setBounds(275, 149, 70, 14);
		frame.getContentPane().add(lblEducation);
		
		JComboBox comboBoxEdu = new JComboBox();
		comboBoxEdu.setModel(new DefaultComboBoxModel(new String[] {"Select", "UnEducated", "UG", "PG", "Diploma", "PHD"}));
		comboBoxEdu.setBounds(372, 147, 108, 20);
		frame.getContentPane().add(comboBoxEdu);
		
		JLabel lbloccupation = new JLabel("occupation");
		lbloccupation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbloccupation.setBounds(275, 209, 94, 14);
		frame.getContentPane().add(lbloccupation);
		
		occupation = new JTextField();
		occupation.setBounds(372, 207, 108, 20);
		frame.getContentPane().add(occupation);
		occupation.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhone.setBounds(275, 259, 53, 14);
		frame.getContentPane().add(lblPhone);
		
		Phone = new JTextField();
		Phone.setBounds(372, 257, 108, 20);
		frame.getContentPane().add(Phone);
		Phone.setColumns(10);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDob.setBounds(275, 293, 53, 20);
		frame.getContentPane().add(lblDob);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 263, 86, 22);
		frame.getContentPane().add(lblAddress);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSubmit.addActionListener(new ActionListener() {
			

	

			public void actionPerformed(ActionEvent e) {
				
				
				
			if(accNum.getText().isEmpty() || pin.getText().isEmpty() || AccName.getText().isEmpty() || occupation.getText().isEmpty() || name.getText().isEmpty()|| 
				   Phone.getText().isEmpty() || dob.getText().isEmpty() ||comboBoxEdu.getSelectedItem().equals(null) || addressnew.getText().isEmpty()  ){
					JOptionPane.showMessageDialog(btnSubmit, "Missing Information");
					
				}else{
					try {
						System.out.println(accNum.getText() + AccName.getText() + name.getText() + dob.getText() + Phone.getText() + 
								addressnew.getText() + comboBoxEdu.getSelectedItem().toString() + occupation.getText() + Integer.parseInt(pin.getText()));
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection(url, user, password);
						String sql="insert into atm_reg.atm_table values(?,?,?,?,?,?,?,?,?,?)";
						PreparedStatement pst=con.prepareStatement(sql);
						pst.setInt(1, Integer.parseInt(accNum.getText()));
						
						pst.setString(2, AccName.getText());
						pst.setString(3, name.getText());
						pst.setString(4, dob.getText());
						pst.setString(5, Phone.getText());
						pst.setString(6, addressnew.getText());
						pst.setString(7, comboBoxEdu.getSelectedItem().toString());
						pst.setString(8, occupation.getText());
						pst.setInt(9, 0);
						pst.setInt(10, Integer.parseInt(pin.getText()));
						
						
						
						
						pst.executeUpdate();
						clear();
						new login();
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
				}			
			}
		});
		btnSubmit.setBounds(140, 352, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton buttonSignUp = new JButton("SignUp");
		buttonSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new login();
				
			}
		});
		buttonSignUp.setBounds(256, 352, 89, 23);
		frame.getContentPane().add(buttonSignUp);
		
		JLabel lblSignupForm = new JLabel("SignUp Form");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(195, 47, 117, 22);
		frame.getContentPane().add(lblSignupForm);
		
		dob = new JTextField();
		dob.setBounds(372, 294, 108, 20);
		frame.getContentPane().add(dob);
		dob.setColumns(10);
		
	    addressnew = new JTextArea();
	    addressnew.setBackground(new Color(255, 255, 255));
	    addressnew.setForeground(new Color(0, 0, 0));
		addressnew.setBounds(138, 251, 117, 62);
		frame.getContentPane().add(addressnew);
	}

	
}
