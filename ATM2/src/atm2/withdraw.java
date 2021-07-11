package atm2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class withdraw {

	private JFrame frmAtmManagmentSystem;
	private JTextField widraw_amount;
	static int preaccnum;

	String url = "jdbc:mysql://localhost:3306/atm_reg";
	String user = "root";
	String password = "admin";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					withdraw window = new withdraw(preaccnum);
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
	public withdraw(int preaccnum) {
		this.preaccnum = preaccnum;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtmManagmentSystem = new JFrame();
		frmAtmManagmentSystem.setTitle(" ATM Managment System");
		frmAtmManagmentSystem.setBounds(100, 100, 560, 410);
		frmAtmManagmentSystem.setVisible(true);
		frmAtmManagmentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtmManagmentSystem.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(21, 11, 513, 332);
		frmAtmManagmentSystem.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("                   ATM Managment System");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 513, 33);
		panel.add(lblNewLabel);

		JLabel lblSignupForm = new JLabel("WITHDRAW FORM");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(156, 47, 172, 52);
		panel.add(lblSignupForm);

		JLabel lblAmount = new JLabel("AMOUNT");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAmount.setBounds(111, 125, 63, 33);
		panel.add(lblAmount);

		widraw_amount = new JTextField();
		widraw_amount.setBounds(220, 126, 160, 33);
		panel.add(widraw_amount);
		widraw_amount.setColumns(10);

		JButton btnWithdraw = new JButton("WITHDRAW");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				if(widraw_amount.getText().isEmpty()){
					JOptionPane.showMessageDialog(btnWithdraw, "Please Enter Ammount to be Withdraw");
					System.out.println("Empty" +widraw_amount.getText().isEmpty());
				}else{

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, password);
					Statement st = con.createStatement();
					String sql2 = "select Balance from atm_table where AccNum='" + preaccnum + "' ";
					ResultSet rs = st.executeQuery(sql2);
					rs.next();
					
					int wid_amount = Integer.parseInt(widraw_amount.getText());
					int bal=rs.getInt("Balance");
					if(bal>0 && wid_amount<bal){
					String sql = "update atm_table set Balance='"+(bal-wid_amount)+"' where AccNum='" + preaccnum + "' ";
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(btnWithdraw, "Transaction Done Successfully");
					widraw_amount.setText("");
					}
					else {
						JOptionPane.showMessageDialog(btnWithdraw, "Insufficient Ammount");
					}

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}

			}
		});
		btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnWithdraw.setBounds(220, 185, 119, 33);
		panel.add(btnWithdraw);

		JButton btnBack = new JButton("LOGOUT");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(127, 259, 108, 33);
		panel.add(btnBack);
		
		JButton btnBack_1 = new JButton("BACK");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainMenu(preaccnum);
			}
		});
		btnBack_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack_1.setBounds(272, 259, 108, 33);
		panel.add(btnBack_1);

	}
}
