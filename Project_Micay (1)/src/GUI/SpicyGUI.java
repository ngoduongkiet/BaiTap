package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SpicyGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SpicyGUI frame = new SpicyGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    class LoginPanel extends JPanel {
        private JPanel contentPane;

        public LoginPanel(JPanel contentPane) {
            this.contentPane = contentPane;
            setLayout(new BorderLayout());
            setBackground(new Color(255, 220, 185));

            // Tiêu đề
            JLabel titleLabel = new JLabel("Đăng nhập Admin", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
            titleLabel.setForeground(new Color(102, 51, 0));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 
            add(titleLabel, BorderLayout.NORTH);

            // nhập dữ liệu
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(2, 2, 15, 20));
            inputPanel.setBackground(new Color(255, 220, 185));

            JLabel usernameLabel = new JLabel("Tên đăng nhập:");
            JTextField usernameField = new JTextField();
            usernameField.setFont(new Font("Arial", Font.PLAIN, 16));

            JLabel passwordLabel = new JLabel("Mật khẩu:");
            JPasswordField passwordField = new JPasswordField();
            passwordField.setFont(new Font("Arial", Font.PLAIN, 16));

            // Add input
            inputPanel.add(usernameLabel);
            inputPanel.add(usernameField);
            inputPanel.add(passwordLabel);
            inputPanel.add(passwordField);
            
            // Add bảng nhập chữ
            usernameField.setPreferredSize(new Dimension(250, 30));
            passwordField.setPreferredSize(new Dimension(250, 30));

            add(inputPanel, BorderLayout.CENTER);

            // nút login và back
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(255, 220, 185));
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15)); // Centered buttons with spacing

            // nút login, phông chữ 
            JButton loginButton = new JButton("Đăng nhập");
            loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
            loginButton.setBackground(new Color(255, 140, 0));
            loginButton.setForeground(Color.WHITE);
            loginButton.setFocusPainted(false);
            loginButton.setPreferredSize(new Dimension(150, 40));
            loginButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Adding button border effect
            loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    loginButton.setBackground(new Color(255, 120, 0)); // Change color on hover
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    loginButton.setBackground(new Color(255, 140, 0)); // Revert color when not hovered
                }
            });

            // nút back
            JButton backButton = new JButton("Trở về trang chủ");
            backButton.setFont(new Font("Arial", Font.PLAIN, 18));
            backButton.setBackground(new Color(255, 140, 0));
            backButton.setForeground(Color.WHITE);
            backButton.setFocusPainted(false);
            backButton.setPreferredSize(new Dimension(150, 40));
            backButton.setBorder(BorderFactory.createRaisedBevelBorder());
            backButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    backButton.setBackground(new Color(255, 120, 0)); // Change color on hover
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    backButton.setBackground(new Color(255, 140, 0)); // Revert color when not hovered
                }
            });

            buttonPanel.add(loginButton);
            buttonPanel.add(backButton);
            
            add(buttonPanel, BorderLayout.SOUTH);

            loginButton.addActionListener(e -> handleLogin(usernameField, passwordField));
            backButton.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "Home");
            });
        }

        private void handleLogin(JTextField usernameField, JPasswordField passwordField) {
            String enteredUsername = usernameField.getText();
            String enteredPassword = new String(passwordField.getPassword());

            String dbUrl = "jdbc:mysql://localhost:3306/micaydbproject";
            String dbUsername = "root";
            String dbPassword = "";

            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

            try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, enteredUsername);
                    statement.setString(2, enteredPassword);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                            cardLayout.show(contentPane, "Admin");
                        } else {
                            JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

        private void handleLogin(JTextField usernameField, JPasswordField passwordField) {
            String enteredUsername = usernameField.getText();
            String enteredPassword = new String(passwordField.getPassword());

            String dbUrl = "jdbc:mysql://localhost:3306/micaydbproject";
            String dbUsername = "root";
            String dbPassword = "";

            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

            try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, enteredUsername);
                    statement.setString(2, enteredPassword);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            Container contentPane = null;
                            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                            cardLayout.show(contentPane, "Admin");
                        } else {
                            JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

    public SpicyGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JPanel contentPane = new JPanel(new CardLayout());
        setContentPane(contentPane);

        // home
        HomePanel homePanel = new HomePanel(contentPane);
        contentPane.add(homePanel, "Home");

        // login
        LoginPanel loginPanel = new LoginPanel(contentPane);
        contentPane.add(loginPanel, "Login");

        // admin và user
        AdminPanel adminPanel = new AdminPanel(contentPane);
        UserPanel userPanel = new UserPanel(contentPane);
        contentPane.add(adminPanel, "Admin");
        contentPane.add(userPanel, "User");
    }

    class HomePanel extends JPanel {
        public HomePanel(JPanel contentPane) {
            setLayout(new BorderLayout());
            setBackground(new Color(255, 220, 185));

            // tiêu đề
            JLabel titleLabel = new JLabel("Chào mừng bạn đến với nhà hàng Baratie.", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(new Color(102, 51, 0));
            add(titleLabel, BorderLayout.NORTH);

            // nút để chuyển đến admin
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(255, 220, 185));

            JButton adminButton = new JButton("Admin");
            JButton userButton = new JButton("Đặt hàng");

            adminButton.setFont(new Font("Arial", Font.PLAIN, 18));
            userButton.setFont(new Font("Arial", Font.PLAIN, 18));

            adminButton.setBackground(new Color(255, 140, 0));
            userButton.setBackground(new Color(255, 140, 0));

            buttonPanel.add(adminButton);
            buttonPanel.add(userButton);
            add(buttonPanel, BorderLayout.CENTER);


            adminButton.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "Login");
            });

            userButton.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "User");
            });
        }
    }


class AdminPanel extends JPanel {
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private JPanel contentPane;

    public AdminPanel(JPanel contentPane) {
        this.contentPane = contentPane;
        setLayout(new BorderLayout());
        setBackground(new Color(255, 240, 200));

        // Title
        JLabel titleLabel = new JLabel("Admin", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(102, 51, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Table to show menu items
        tableModel = new DefaultTableModel(new Object[]{"ID", "Tên món", "Giá"}, 0);
        menuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons for actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 240, 200));

        JButton addButton = new JButton("Thêm món ăn");
        JButton editButton = new JButton("Sửa món ăn");
        JButton deleteButton = new JButton("Xóa món ăn");
        JButton backButton = new JButton("Trở về trang chủ");

        addButton.setFont(new Font("Arial", Font.PLAIN, 16));
        editButton.setFont(new Font("Arial", Font.PLAIN, 16));
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));

        addButton.setBackground(new Color(255, 140, 0));
        editButton.setBackground(new Color(255, 140, 0));
        deleteButton.setBackground(new Color(255, 140, 0));
        backButton.setBackground(new Color(255, 140, 0));

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadMenuItems();

        addButton.addActionListener(e -> handleAddMenuItem());
        editButton.addActionListener(e -> handleEditMenuItem());
        deleteButton.addActionListener(e -> handleDeleteMenuItem());
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "Home");
        });
    }

    private void loadMenuItems() {
        tableModel.setRowCount(0);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micaydbproject", "root", "")) {
            String query = "SELECT id, name, price FROM menu";
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    tableModel.addRow(new Object[]{id, name, price});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleAddMenuItem() {
        String name = JOptionPane.showInputDialog(this, "Nhập tên món ăn:");
        if (name == null || name.trim().isEmpty()) return;

        String priceStr = JOptionPane.showInputDialog(this, "Nhập giá của món ăn:");
        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micaydbproject", "root", "")) {
            String query = "INSERT INTO menu (name, price) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setDouble(2, price);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadMenuItems();
    }

    private void handleEditMenuItem() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một món để sửa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String currentName = (String) tableModel.getValueAt(selectedRow, 1);
        double currentPrice = (double) tableModel.getValueAt(selectedRow, 2);

        String newName = JOptionPane.showInputDialog(this, "Nhập tên mới: ", currentName);
        if (newName == null || newName.trim().isEmpty()) return;

        String priceStr = JOptionPane.showInputDialog(this, "Nhập giá mới: ", currentPrice);
        double newPrice;
        try {
            newPrice = Double.parseDouble(priceStr);
            if (newPrice <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micaydbproject", "root", "")) {
            String query = "UPDATE menu SET name = ?, price = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, newName);
                statement.setDouble(2, newPrice);
                statement.setInt(3, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadMenuItems();
    }

    private void handleDeleteMenuItem() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một món để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa món này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micaydbproject", "root", "")) {
            String query = "DELETE FROM menu WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadMenuItems();
    }
}

class UserPanel extends JPanel {
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private JPanel contentPane;

    public UserPanel(JPanel contentPane) {
        this.contentPane = contentPane;
        setLayout(new BorderLayout());
        setBackground(new Color(255, 240, 200));

        // Title
        JLabel titleLabel = new JLabel("Đặt hàng", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(102, 51, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Table to show menu items
        tableModel = new DefaultTableModel(new Object[]{"ID", "Tên món", "Giá"}, 0);
        menuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(menuTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons for actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 240, 200));

        JButton orderButton = new JButton("Đặt hàng");
        JButton backButton = new JButton("Trở về trang chủ");

        orderButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));

        orderButton.setBackground(new Color(255, 140, 0));
        backButton.setBackground(new Color(255, 140, 0));

        buttonPanel.add(orderButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadMenuItems();

        orderButton.addActionListener(e -> handleOrderMenuItem());
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "Home");
        });
    }

    private void loadMenuItems() {
        tableModel.setRowCount(0);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micaydbproject", "root", "")) {
            String query = "SELECT id, name, price FROM menu";
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    tableModel.addRow(new Object[]{id, name, price});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleOrderMenuItem() {
        int selectedRow = menuTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một món để đặt hàng!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        double price = (double) tableModel.getValueAt(selectedRow, 2);

        String[] spiceLevels = {"1", "2", "3", "4", "5", "6", "7"};
        String selectedSpiceLevel = (String) JOptionPane.showInputDialog(
            this,
            "Chọn cấp độ cay (1-7):",
            "Cấp độ cay",
            JOptionPane.QUESTION_MESSAGE,
            null,
            spiceLevels,
            spiceLevels[0]
        );

        if (selectedSpiceLevel == null) return;

        String quantityStr = JOptionPane.showInputDialog(this, "Nhập số lượng muốn đặt:");
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double totalPrice = price * quantity;
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Xác nhận đặt hàng: \nMón: " + name +
            "\nCấp độ cay: " + selectedSpiceLevel +
            "\nSố lượng: " + quantity +
            "\nTổng giá: " + totalPrice + " VND",
            "Xác nhận",
            JOptionPane.YES_NO_OPTION
        );

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micaydbproject", "root", "")) {
            String query = "INSERT INTO orders (menu_id, quantity, total_price, spice_level) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.setInt(2, quantity);
                statement.setDouble(3, totalPrice);
                statement.setString(4, selectedSpiceLevel);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Đặt hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    	}
	}
}
