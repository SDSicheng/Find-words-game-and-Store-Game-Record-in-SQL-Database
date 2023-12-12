import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import java.awt.Checkbox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameRecord {

	private JFrame frame;
	private JTextField txtplayername;
	private JTextField txtscore;
	private JTextField txtdate;
	private JTable table;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameRecord window = new GameRecord();
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
	public GameRecord() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Java", "root","");
	            System.out.println("connected.");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }
	
	  public void table_load()
	    {
	     try
	     {
	    pst = con.prepareStatement("select * from GameRecord");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	    }
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Game Record");
		lblNewLabel.setBounds(166, 24, 88, 33);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(19, 55, 249, 152);
		panel.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(22, 31, 61, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Score");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(22, 68, 61, 25);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(22, 109, 61, 25);
		panel.add(lblNewLabel_3);
		
		txtplayername = new JTextField();
		txtplayername.setBounds(110, 31, 130, 22);
		panel.add(txtplayername);
		txtplayername.setColumns(10);
		
		txtscore = new JTextField();
		txtscore.setColumns(10);
		txtscore.setBounds(110, 65, 130, 33);
		panel.add(txtscore);
		
		txtdate = new JTextField();
		txtdate.setColumns(10);
		txtdate.setBounds(110, 110, 130, 26);
		panel.add(txtdate);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(19, 211, 75, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name,Score,Date;
				Name = txtplayername.getText();
				Score = txtscore.getText();
				Date = txtdate.getText();
				
				try {
					pst = con.prepareStatement("insert into GameRecord(Name,Score,Date)values(?,?,?)");
					pst.setString(1, Name);
					pst.setString(2, Score);
					pst.setString(3, Date);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					
					table_load();
					txtplayername.setText("");
					txtscore.setText("");
					txtdate.setText("");
					txtplayername.requestFocus();
				}
				catch(SQLException el){
					el.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(106, 211, 75, 29);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtplayername.setText("");
				txtscore.setText("");
				txtdate.setText("");
				txtplayername.requestFocus();
				
			}
		});
		btnClear.setBounds(193, 211, 75, 29);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 31, 324, 209);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(19, 252, 255, 60);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID");
		lblNewLabel_2_1.setBounds(34, 24, 37, 21);
		lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_2_1);
		
		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String ID = txtID.getText();
		 
		                pst = con.prepareStatement("select Name,Score,Date from GameRecord where ID = ?");
		                pst.setString(1, ID);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String Name = rs.getString(1);
		                String Score = rs.getString(2);
		                String Date = rs.getString(3);
		                
		                txtplayername.setText(Name);
		                txtscore.setText(Score);
		                txtdate.setText(Date);
		                
		                
		            }  
		            else
		            {
		             txtplayername.setText("");
		             txtscore.setText("");
		                txtdate.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
				
				
				
				
				
				
				
			}
		});
		txtID.setBounds(83, 23, 130, 26);
		txtID.setColumns(10);
		panel_1.add(txtID);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                String ID;
                ID  = txtID.getText();
                try {	
                	pst = con.prepareStatement("delete from GameRecord where ID =?");
                	pst.setString(1, ID);
                	pst.executeUpdate();
                	JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
                	table_load();
  
                	txtplayername.setText("");
                	txtscore.setText("");
                	txtdate.setText("");
                	txtplayername.requestFocus();
                }

                catch (SQLException e1) {
                	e1.printStackTrace();
                }
				
				
			}
		});
		btnNewButton_1.setBounds(371, 257, 117, 55);
		frame.getContentPane().add(btnNewButton_1);
	}
}
