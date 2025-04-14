package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class userDAO {
	Connection con = DBConn.getConnection();
	PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    public void insert(String username, String email, String password) {
        String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Người dùng đã đăng ký thành công!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: Không thể đăng ký người dùng. Vui lòng thử lại sau.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
