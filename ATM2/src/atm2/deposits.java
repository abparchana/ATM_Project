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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class deposits {

	private JFrame frmAtmManagmentSystem;
	private JTextField depositAmount;
	static int deposit_accnum;
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
					deposits window = new deposits(deposit_accnum);
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
	public deposits(int accnum) {
		deposit_accnum = accnum;
		initialize();

	}

	String myDate;

	public void getDate() {
		Date d = new Date(0, 0, 0);
		SimpleDateFormat sd = new SimpleDateFormat("dd-yy-yyyy");
		myDate = sd.format(d);

	}

	public void transactionDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			String sql = "insert into atm_reg.transaction_table values(?,?,?,?,?)";
			PreparedStatement add = con.prepareStatement(sql);

			add.setInt(1, 0);
			add.setInt(2, deposit_accnum);
			add.setString(3, "Deposit");
			add.setString(4, depositAmount.getText());
			add.setString(5, myDate);

			int row=add.executeUpdate();
			
			con.close();

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtmManagmentSystem = new JFrame();
		frmAtmManagmentSystem.setVisible(true);
		frmAtmManagmentSystem.setTitle(" ATM Managment System");
		frmAtmManagmentSystem.setBounds(100, 100, 538, 342);
		frmAtmManagmentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtmManagmentSystem.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(31, 11, 469, 282);
		frmAtmManagmentSystem.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("                   ATM Managment System");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 513, 33);
		panel.add(lblNewLabel);

		JLabel lblSignupForm = new JLabel("Deposit Form");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(195, 47, 117, 22);
		panel.add(lblSignupForm);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAmount.setBounds(84, 111, 117, 45);
		panel.add(lblAmount);

		depositAmount = new JTextField();
		depositAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		depositAmount.setBounds(255, 123, 86, 20);
		panel.add(depositAmount);
		depositAmount.setColumns(10);

		JButton btnDeosit = new JButton("DEPOSIT");
		btnDeosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, password);

					Statement st = con.createStatement();
					int balance = Integer.parseInt(depositAmount.getText());
					if ((balance == 0) && (depositAmount.getText().isEmpty())) {
						JOptionPane.showMessageDialog(btnDeosit, "Please enter Amount to be deposit");
					} else {

						String sql2 = "select Balance from atm_reg.atm_table where AccNum='" + deposit_accnum + "' ";
						ResultSet rs = st.executeQuery(sql2);
						rs.next();
						int prevBalance = rs.getInt("Balance");

						String sql = "update atm_reg.atm_table set Balance='" + (prevBalance + balance)
								+ "' where AccNum='" + deposit_accnum + "' ";
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(btnDeosit, "Amount Deposited Successfully");
						
						transactionDB();
						depositAmount.setText("");
					}

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDeosit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDeosit.setBounds(94, 178, 89, 33);
		panel.add(btnDeosit);

		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainMenu(deposit_accnum);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(223, 178, 89, 33);
		panel.add(btnBack);
	}

}
