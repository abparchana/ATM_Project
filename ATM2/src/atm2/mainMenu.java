package atm2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainMenu {

	private JFrame frmAtmManagmentSystem;
	static int accnum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu window = new mainMenu(accnum);
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
	public mainMenu(int accnum) {
		this.accnum=accnum;
		//System.out.println("Hiiiiii"+ accnum);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtmManagmentSystem = new JFrame();
		frmAtmManagmentSystem.setTitle(" ATM Managment System");
		frmAtmManagmentSystem.setVisible(true);
		frmAtmManagmentSystem.setBounds(100, 100, 571, 397);
		frmAtmManagmentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtmManagmentSystem.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(40, 23, 474, 309);
		frmAtmManagmentSystem.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("                   ATM Managment System");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 513, 33);
		panel.add(lblNewLabel);
		
		
		JLabel lblSignupForm = new JLabel("Main Menu Form");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(195, 47, 117, 22);
		panel.add(lblSignupForm);
		
		JButton btnNewButton = new JButton("Deposit");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new deposits(accnum);
			}
		});
		btnNewButton.setBounds(34, 86, 144, 33);
		panel.add(btnNewButton);
		
		JButton btnFastcash = new JButton("FastCash");
		btnFastcash.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFastcash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new fastcash(accnum);
			}
		});
		btnFastcash.setBounds(34, 147, 144, 33);
		panel.add(btnFastcash);
		
		JButton btnChangePin = new JButton("Change Pin");
		btnChangePin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChangePin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new changePIN(accnum);
			}
		});
		btnChangePin.setBounds(34, 199, 144, 33);
		panel.add(btnChangePin);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new withdraw(accnum);
			}
		});
		btnWithdraw.setBounds(299, 86, 144, 33);
		panel.add(btnWithdraw);
		
		JButton button_3 = new JButton("Mini Statement");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MINIsTATEMENT(accnum);
			}
		});
		button_3.setBounds(299, 147, 144, 33);
		panel.add(button_3);
		
		JButton btnBalance = new JButton("Balance");
		btnBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new balance(accnum);
				//System.out.println("In Balance "+accnum);
			}
		});
		btnBalance.setBounds(299, 199, 144, 33);
		panel.add(btnBalance);
		
		JButton btnSignUp = new JButton("Log Out");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignUp.setBounds(127, 260, 117, 38);
		panel.add(btnSignUp);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
