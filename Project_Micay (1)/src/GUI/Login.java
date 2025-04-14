package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DBConn;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfemail;
	private JPasswordField tfpassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 64, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Đăng Nhập");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel_5.setBounds(29, 21, 327, 57);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Email:");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_6.setBounds(44, 110, 62, 14);
		contentPane.add(lblNewLabel_6);
		
		tfemail = new JTextField();
		tfemail.setText("");
		tfemail.setColumns(10);
		tfemail.setBounds(44, 135, 300, 30);
		contentPane.add(tfemail);
		
		JLabel lblNewLabel_7 = new JLabel("Mật khẩu:");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_7.setBounds(44, 190, 90, 20);
		contentPane.add(lblNewLabel_7);
		
		tfpassword = new JPasswordField();
		tfpassword.setBounds(44, 215, 300, 30);
		contentPane.add(tfpassword);
		
		JButton btnNewButton = new JButton("Đăng Nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String email = tfemail.getText();
		         String password = String.valueOf(tfpassword.getPassword());
		         try {
	                    Connection con = DBConn.getConnection();
	                    PreparedStatement ps;
	                    ps = con.prepareStatement("SELECT * FROM user WHERE email =? and password = ?");
	                    ps.setString(1, email);
	                    ps.setString(2, password);
	                    ResultSet rs = ps.executeQuery();
	                    if (rs.next()) {
	                    	new SpicyGUI().setVisible(true);
	                        
	                        dispose();
	                    } else {        
	                    	JOptionPane.showMessageDialog(null,"Địa chỉ email hoặc mật khẩu không đúng",  "Dăng nhập thất bại", JOptionPane.WARNING_MESSAGE);
	                    }
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnNewButton.setBackground(new Color(128, 128, 192));
		btnNewButton.setBounds(44, 284, 300, 38);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_10 = new JLabel("Đăng ký");
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Sign_up().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(237, 351, 62, 23);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_9 = new JLabel("Không có tài khoản?");
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(75, 351, 152, 23);
		contentPane.add(lblNewLabel_9);
	}
}
