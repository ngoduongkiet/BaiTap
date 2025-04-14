package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.userDAO;



import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sign_up extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfname;
	private JTextField tfemail;
	private JPasswordField tfpass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sign_up frame = new Sign_up();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean isEmpty() {
		if (tfname.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Tên người dùng cần phải điền vào",  "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} if (tfemail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Email người dùng cần phải điền vào",  "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} if (String.valueOf(tfpass.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this,"Mật khẩu người dùng cần phải điền vào",  "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} if (!tfemail.getText().matches("^.+@.+\\..+$")) {
			JOptionPane.showMessageDialog(this,"Email không đúng",  "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		} return true;
		
	}
	
	public Sign_up() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 64, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Đăng ký");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel_2.setBounds(61, 35, 321, 52);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Tên:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(63, 112, 77, 25);
		contentPane.add(lblNewLabel_4);
		
		tfname = new JTextField();
		tfname.setColumns(10);
		tfname.setBounds(64, 138, 321, 31);
		contentPane.add(tfname);
		
		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(61, 182, 79, 14);
		contentPane.add(lblNewLabel_5);
		
		tfemail = new JTextField();
		tfemail.setColumns(10);
		tfemail.setBounds(64, 204, 321, 31);
		contentPane.add(tfemail);
		
		JLabel lblNewLabel_6 = new JLabel("Mật khẩu:");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(65, 244, 75, 25);
		contentPane.add(lblNewLabel_6);
		
		tfpass = new JPasswordField();
		tfpass.setBounds(64, 270, 321, 31);
		contentPane.add(tfpass);
		
		Button btnSave = new Button("Đăng ký");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (isEmpty()) {
						
						String username = tfname.getText(); 
						String email = tfemail.getText();
						String password = String.valueOf(tfpass.getPassword());
						
						
							userDAO user = new userDAO();
								user.insert( username, email, password);
								new Login().setVisible(true);
								dispose();
							
					}
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnSave.setBackground(Color.GREEN);
		btnSave.setBounds(64, 344, 154, 52);
		contentPane.add(btnSave);
		
		Button btnBack = new Button("Quay về");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(231, 344, 154, 52);
		contentPane.add(btnBack);
	}
}
