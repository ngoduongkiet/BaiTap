package bai2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptionApp extends JFrame {
    private JComboBox<String> algorithmComboBox;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton encryptButton;
    private JButton decryptButton;
    private JLabel keyInfoLabel;

    private Encryptable encryptor;

    public EncryptionApp() {
        setTitle("Ứng dụng mã hóa/giải mã");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        setupEventHandlers();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Algorithm selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Thuật toán:"));
        algorithmComboBox = new JComboBox<>(new String[]{"AES", "RSA"});
        topPanel.add(algorithmComboBox);

        keyInfoLabel = new JLabel(" ");
        topPanel.add(keyInfoLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Text areas
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputTextArea = new JTextArea();
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        centerPanel.add(new JScrollPane(inputTextArea));
        centerPanel.add(new JScrollPane(outputTextArea));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        encryptButton = new JButton("Mã hóa");
        decryptButton = new JButton("Giải mã");
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        updateEncryptor();
    }

    private void setupEventHandlers() {
        algorithmComboBox.addActionListener(e -> updateEncryptor());

        encryptButton.addActionListener(e -> {
            try {
                String input = inputTextArea.getText();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập văn bản cần mã hóa");
                    return;
                }
                String encrypted = encryptor.encrypt(input);
                outputTextArea.setText(encrypted);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi mã hóa: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        decryptButton.addActionListener(e -> {
            try {
                String input = inputTextArea.getText();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập văn bản cần giải mã");
                    return;
                }
                String decrypted = encryptor.decrypt(input);
                outputTextArea.setText(decrypted);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi giải mã: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void updateEncryptor() {
        try {
            String algorithm = (String) algorithmComboBox.getSelectedItem();
            if ("AES".equals(algorithm)) {
                encryptor = new AESEncryptor();
                keyInfoLabel.setText("AES Key: " + ((AESEncryptor) encryptor).getSecretKey());
            } else if ("RSA".equals(algorithm)) {
                encryptor = new RSAEncryptor();
                keyInfoLabel.setText("RSA Public Key: " + ((RSAEncryptor) encryptor).getPublicKey());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi khởi tạo encryptor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new EncryptionApp().setVisible(true);
        });
    }
}