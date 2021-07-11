package atm2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

public class fastcash {

	private JFrame frame;
	String url = "jdbc:mysql://localhost:3306/atm_reg";
	String user = "root";
	String password = "admin";
	String sql1;
	String sql2;
	Statement st;
	ResultSet rs;
	static int preaccnum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fastcash window = new fastcash(preaccnum);
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
	public fastcash(int preaccnum) {
		this.preaccnum = preaccnum;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 625, 395);
		frame.setVisible(true);
		frame.setTitle("ATM Managment System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(22, 11, 432, 335);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("               ATM Managment System");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 412, 33);
		panel.add(lblNewLabel);

		JLabel lblSignupForm = new JLabel("FASTCASH FORM");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(153, 55, 161, 33);
		panel.add(lblSignupForm);

		JButton btnRs = new JButton("RS 100");
		btnRs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbconnect();

				try {
					sql1 = "select Balance from atm_reg.atm_table where AccNum='" + preaccnum + "' ";
					rs = st.executeQuery(sql1);
					rs.next();
					System.out.println("HIIII" + rs.getInt("Balance"));
					int avalBal = rs.getInt("Balance");
					
					if(avalBal>0 && 100<=avalBal){
						sql2 = "update atm_table set Balance='" + (avalBal - 100) + "' where AccNum='" + preaccnum
								+ "' ";
						st.executeUpdate(sql2);
						new balance(preaccnum);
						}else {
							JOptionPane.showMessageDialog(btnRs, "Insufficient Ammount");
						}
					

				} catch (SQLException e2) {
					e2.printStackTrace();
				}

			}
		});
		btnRs.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRs.setBounds(33, 100, 120, 33);
		panel.add(btnRs);

		JButton btnRs_1 = new JButton("RS. 1000");
		btnRs_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbconnect();

				try {
					sql1 = "select Balance from atm_reg.atm_table where AccNum='" + preaccnum + "' ";
					rs = st.executeQuery(sql1);
					rs.next();
					System.out.println("HIIII" + rs.getInt("Balance"));
					int avalBal = rs.getInt("Balance");
					if(avalBal>0 && 100<=avalBal){
						sql2 = "update atm_table set Balance='" + (avalBal - 100) + "' where AccNum='" + preaccnum
								+ "' ";
						st.executeUpdate(sql2);
						new balance(preaccnum);
						}else {
							JOptionPane.showMessageDialog(btnRs_1, "Insufficient Ammount");
						}

				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnRs_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRs_1.setBounds(33, 155, 120, 33);
		panel.add(btnRs_1);

		JButton btnRs_2 = new JButton("RS. 5000");
		btnRs_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbconnect();

				try {
					sql1 = "select Balance from atm_reg.atm_table where AccNum='" + preaccnum + "' ";
					rs = st.executeQuery(sql1);
					rs.next();
					System.out.println("HIIII" + rs.getInt("Balance"));
					int avalBal = rs.getInt("Balance");
					if(avalBal>0 && 5000<=avalBal){
						sql2 = "update atm_table set Balance='" + (avalBal - 5000) + "' where AccNum='" + preaccnum
								+ "' ";
						st.executeUpdate(sql2);
						new balance(preaccnum);
						}else {
							JOptionPane.showMessageDialog(btnRs_2, "Insufficient Ammount");
						}

				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnRs_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRs_2.setBounds(33, 221, 120, 33);
		panel.add(btnRs_2);

		JButton btnRs_3 = new JButton("RS. 500");
		btnRs_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbconnect();

				try {
					sql1 = "select Balance from atm_reg.atm_table where AccNum='" + preaccnum + "' ";
					rs = st.executeQuery(sql1);
					rs.next();
					System.out.println("HIIII" + rs.getInt("Balance"));
					int avalBal = rs.getInt("Balance");
					if(avalBal>0 && 500<=avalBal){
						sql2 = "update atm_table set Balance='" + (avalBal - 500) + "' where AccNum='" + preaccnum
								+ "' ";
						st.executeUpdate(sql2);
						new balance(preaccnum);
						}else {
							JOptionPane.showMessageDialog(btnRs_3, "Insufficient Ammount");
						}

				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnRs_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRs_3.setBounds(264, 99, 120, 34);
		panel.add(btnRs_3);

		JButton btnRs_4 = new JButton("RS. 2000");
		btnRs_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbconnect();

				try {
					sql1 = "select Balance from atm_reg.atm_table where AccNum='" + preaccnum + "' ";
					rs = st.executeQuery(sql1);
					rs.next();
					System.out.println("HIIII" + rs.getInt("Balance"));
					int avalBal = rs.getInt("Balance");
					if(avalBal>0 && 2000<=avalBal){
						sql2 = "update atm_table set Balance='" + (avalBal - 2000) + "' where AccNum='" + preaccnum
								+ "' ";
						st.executeUpdate(sql2);
						new balance(preaccnum);
						}else {
							JOptionPane.showMessageDialog(btnRs_4, "Insufficient Ammount");
						}

				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnRs_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRs_4.setBounds(264, 155, 120, 33);
		panel.add(btnRs_4);

		JButton btnRs_5 = new JButton("RS. 10000");
		btnRs_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbconnect();

				try {
					sql1 = "select Balance from atm_reg.atm_table where AccNum='" + preaccnum + "' ";
					rs = st.executeQuery(sql1);
					rs.next();
					System.out.println("HIIII" + rs.getInt("Balance"));
					int avalBal = rs.getInt("Balance");
					
					if(avalBal>0 && 10000<=avalBal){
					sql2 = "update atm_table set Balance='" + (avalBal - 10000) + "' where AccNum='" + preaccnum
							+ "' ";
					st.executeUpdate(sql2);
					new balance(preaccnum);
					}else {
						JOptionPane.showMessageDialog(btnRs_5, "Insufficient Ammount");
					}
					

				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnRs_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRs_5.setBounds(264, 221, 120, 33);
		panel.add(btnRs_5);

		JLabel lblBalance = new JLabel("BALANCE");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBalance.setBounds(177, 165, 65, 23);
		panel.add(lblBalance);

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogout.setBounds(131, 286, 89, 38);
		panel.add(btnLogout);

		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainMenu(preaccnum);
			}
		});
		btnNewButton.setBounds(239, 286, 89, 38);
		panel.add(btnNewButton);
	}

	public void dbconnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
