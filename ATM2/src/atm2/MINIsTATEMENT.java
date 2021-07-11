package atm2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class MINIsTATEMENT {

	private JFrame frame;
	private JLabel lblMiniStatement;

	static int accnum;
	private JTable mini_Statement_table;

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
					MINIsTATEMENT window = new MINIsTATEMENT(accnum);
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
	public MINIsTATEMENT(int accnum) {
		this.accnum = accnum;
		initialize();
		DisplayTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	

	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 648, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(25, 11, 583, 85);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 325, 583, 65);
		frame.getContentPane().add(panel_2);

		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainMenu(accnum);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnBack);

		JLabel lblNewLabel = new JLabel("                   ATM Managment System");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(new Color(160, 82, 45));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 513, 33);
		panel_1.add(lblNewLabel);

		JLabel lblSignupForm = new JLabel("MINI STATEMNET FORM");
		lblSignupForm.setForeground(SystemColor.textHighlight);
		lblSignupForm.setBackground(Color.MAGENTA);
		lblSignupForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSignupForm.setBounds(163, 47, 197, 22);
		panel_1.add(lblSignupForm);

		
		
		mini_Statement_table = new JTable();
		mini_Statement_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		mini_Statement_table.setFillsViewportHeight(true);
	
		
	
		mini_Statement_table.setBounds(35, 107, 573, 207);
		frame.getContentPane().add(mini_Statement_table);

	}
	
	public void DisplayTable() {

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		String sql = "select * from atm_reg.transaction_table where AccNum='" + accnum + "' ";
		ResultSet rs = st.executeQuery(sql);
		Object cl_Arr[] = { "Transaction_ID", "Account Number", "Type", "Amount", "Date" };
		
		
		
		
		DefaultTableModel tableModel = (DefaultTableModel) mini_Statement_table.getModel();
		//tableModel.addColumn(cl_Arr);
		//tableModel.addColumn(cl_Arr,  "Transaction_ID", "Account Number", "Type", "Amount", "Date");
		tableModel.addColumn(cl_Arr);
		
		while (rs.next()) {
			String id = String.valueOf(rs.getInt("T_id"));
			String accNum = String.valueOf(rs.getInt("AccNum"));
			String Type = rs.getString("Type");
			String amount = rs.getString("Amount");
			String date = rs.getString("Date");
			

			// Create String array to store data into JTable
			
			String row_arr[] = { id, accNum, Type, amount, date };

			

			// add arry rows into Jtable
			
			tableModel.addRow(row_arr);
			

		}

	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
