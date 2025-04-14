package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConn {
    private static final String URL = "jdbc:mysql://localhost:3306/micaydbproject";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection getConnection() {
    	 Connection con = null;
         try {
             con = DriverManager.getConnection(URL, USER, PASSWORD);
             System.out.println("Kết nối thành công!");
         } catch (SQLException e) {
             System.err.println("Kết nối thất bại!"); 
             e.printStackTrace();
         }
         return con;
    }
    
    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT * FROM menu";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                menuItems.add(new MenuItem(id, name, price, description));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    }
