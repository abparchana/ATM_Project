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
import javax.swing.JPasswordField;

public class changePIN {

	private JFrame frame;
	static int accnum;

	String url = "jdbc:mysql://localhost:3306/atm_reg";
	String user = "root";
	String password = "admin";
	private JPasswordField old_Pin;
	private JPasswordField new_Pin;
	private JPasswordField confirm_Pin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changePIN window = new changePIN(accnum);
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
	public changePIN(int accnum) {
		this.accnum = accnum;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 608, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(40, 11, 461, 318);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("                   ATM Managment System");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 513, 33);
		panel.add(lblNewLabel);

		JLabel lblSignupForm = new JLabel("CHANGE PIN FORM");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(157, 47, 155, 22);
		panel.add(lblSignupForm);

		JLabel lblNewPin = new JLabel("OLD PIN");
		lblNewPin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewPin.setBounds(24, 83, 108, 27);
		panel.add(lblNewPin);

		JLabel lblConfirmPin = new JLabel("NEW PIN");
		lblConfirmPin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirmPin.setBounds(24, 142, 108, 22);
		panel.add(lblConfirmPin);

		JButton btnChange = new JButton("CHANGE PIN");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// old_pin ,confirm_Pin, new_Pin

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, password);
					Statement st = con.createStatement();
					String sql = "select pin from atm_reg.atm_table where AccNum='" + accnum + "' ";
					ResultSet rs = st.executeQuery(sql);
					// rs.next();

					if (old_Pin.getText().isEmpty() && new_Pin.getText().isEmpty() && confirm_Pin.getText().isEmpty()) {
						JOptionPane.showMessageDialog(btnChange, "Please Enter Pin in all Fields ");
					}

					if (rs.next()) {
						int old_pin1 = Integer.parseInt(old_Pin.getText());
						int new_pin1 = Integer.parseInt(new_Pin.getText());
						int confirm_pin1 = Integer.parseInt(confirm_Pin.getText());

						int dbpin = rs.getInt("pin");
						if (old_pin1 == dbpin) {
							if (new_pin1 == confirm_pin1) {
								String sql2 = "update atm_reg.atm_table set pin='" + confirm_pin1 + "' where AccNum='"
										+ accnum + "' ";
								st.executeUpdate(sql2);
								old_Pin.setText("");
								new_Pin.setText("");
								confirm_Pin.setText("");
								JOptionPane.showMessageDialog(btnChange, "Your ATM PIN has been Changed Successfully ");

							} else {
								JOptionPane.showMessageDialog(btnChange, "New Pin and Confirm Pin Must Be Same ");
								new_Pin.setText("");
								confirm_Pin.setText("");
							}

						} else {
							JOptionPane.showMessageDialog(btnChange, "Old Pin is Incorrect, enter correct old pin ");
							old_Pin.setText("");

						}
					} else {
						JOptionPane.showMessageDialog(btnChange, "Incorrect Information ");
					}

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChange.setBounds(24, 263, 117, 33);
		panel.add(btnChange);

		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainMenu(accnum);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(167, 263, 117, 33);
		panel.add(btnBack);

		JLabel label = new JLabel("CONFIRM PIN");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(24, 194, 108, 22);
		panel.add(label);

		old_Pin = new JPasswordField();
		old_Pin.setBounds(167, 80, 145, 30);
		panel.add(old_Pin);

		new_Pin = new JPasswordField();
		new_Pin.setBounds(167, 134, 145, 30);
		panel.add(new_Pin);

		confirm_Pin = new JPasswordField();
		confirm_Pin.setBounds(167, 196, 145, 30);
		panel.add(confirm_Pin);
	}
}
