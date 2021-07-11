package atm2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login {

	private JFrame frmAtmManagmentSystem;
	private JTextField accnum;

	String url = "jdbc:mysql://localhost:3306/atm_reg";
	String user = "root";
	String password = "admin";
	private JPasswordField pincode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtmManagmentSystem = new JFrame();
		frmAtmManagmentSystem.setVisible(true);
		frmAtmManagmentSystem.setTitle("ATM Managment System");
		frmAtmManagmentSystem.setBounds(100, 100, 491, 401);
		frmAtmManagmentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtmManagmentSystem.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 455, 316);
		frmAtmManagmentSystem.getContentPane().add(panel);
		panel.setLayout(null);

		accnum = new JTextField();
		accnum.setBounds(189, 88, 149, 33);
		panel.add(accnum);
		accnum.setColumns(10);

		JLabel lblNewLabel = new JLabel("Account Number");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(44, 84, 112, 29);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pin Code");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(44, 145, 112, 33);
		panel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, password);
					Statement st = con.createStatement();
					String sql = "select * from atm_reg.atm_table where AccNum='" + accnum.getText() + "' and pin='"
							+ pincode.getText() + "'";
					ResultSet rs = st.executeQuery(sql);

					if (accnum.getText().isEmpty() && pincode.getText().isEmpty()) {
						JOptionPane.showMessageDialog(btnNewButton,
								"Missing Information, Please Enter Account  and Pin Number");

					}
					else if (rs.next()) {
						int accnumber = rs.getInt("AccNum");
						int accpin = rs.getInt("pin");

						int userAccNum = Integer.parseInt(accnum.getText());
						int userPincode = Integer.parseInt(pincode.getText());

						if (userAccNum == accnumber && userPincode == accpin) {
							int accum = Integer.parseInt(accnum.getText());

							new mainMenu(accum);
							accnum.setText("");
							pincode.setText("");
						} /*else if(userAccNum != accnumber){
							JOptionPane.showMessageDialog(btnNewButton, "Incorrect Account Number");
						}else if(userPincode != accpin){
							JOptionPane.showMessageDialog(btnNewButton, "Incorrect Pin");
						}*/
					} else  {
						JOptionPane.showMessageDialog(btnNewButton, "Incorrect Information");
						accnum.setText("");
						pincode.setText("");
					}

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(67, 206, 89, 23);
		panel.add(btnNewButton);

		JButton btnSignup = new JButton("SIGNUP");
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new signUP();
			}
		});
		btnSignup.setBounds(196, 204, 89, 25);
		panel.add(btnSignup);

		JLabel lblNewLabel_2 = new JLabel("                   ATM Managment System");
		lblNewLabel_2.setBackground(Color.PINK);
		lblNewLabel_2.setForeground(new Color(160, 82, 45));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 11, 513, 33);
		panel.add(lblNewLabel_2);

		JLabel lblSignupForm = new JLabel("Login Form");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(195, 47, 117, 22);
		panel.add(lblSignupForm);

		pincode = new JPasswordField();
		pincode.setBounds(189, 145, 149, 33);
		panel.add(pincode);

		/*
		 * JLabel lblNewLabel_2 = new JLabel("ATM Managment System");
		 * lblNewLabel_2.setBounds(22, 11, 268, 39); panel.add(lblNewLabel_2);
		 */
	}
}
