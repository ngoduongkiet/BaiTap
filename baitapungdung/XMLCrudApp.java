package baitapungdung;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XMLCrudApp {
    private JFrame frame;
    private JTextArea textArea;
    private File xmlFile = new File("data.xml");
    private Document document;
    private Element rootElement;

    public XMLCrudApp() {
        frame = new JFrame("Ứng dụng XML CRUD");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        textArea = new JTextArea();
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Thêm phần tử");
        JButton updateButton = new JButton("Cập nhật phần tử");
        JButton deleteButton = new JButton("Xóa phần tử");
        JButton loadButton = new JButton("Tải XML");
        
        addButton.addActionListener(this::addElement);
        updateButton.addActionListener(this::updateElement);
        deleteButton.addActionListener(this::deleteElement);
        loadButton.addActionListener(this::loadXML);
        
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(loadButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(panel);
        
        initializeXML();
        frame.setVisible(true);
    }
    
    private void initializeXML() {
        if (!xmlFile.exists()) {
            document = new Document();
            rootElement = new Element("Elements");
            document.setRootElement(rootElement);
            saveXML();
        } else {
            loadXML(null);
        }
    }
    
    private void addElement(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Nhập tên phần tử:");
        if (name != null && !name.isEmpty()) {
            Element newElement = new Element("Item");
            newElement.setText(name);
            rootElement.addContent(newElement);
            saveXML();
        }
    }
    
    private void updateElement(ActionEvent e) {
        String oldName = JOptionPane.showInputDialog("Nhập tên phần tử cũ:");
        String newName = JOptionPane.showInputDialog("Nhập tên phần tử mới:");
        if (oldName != null && newName != null) {
            List<Element> elements = rootElement.getChildren("Item");
            for (Element el : elements) {
                if (el.getText().equals(oldName)) {
                    el.setText(newName);
                    saveXML();
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Không tìm thấy phần tử!");
        }
    }
    
    private void deleteElement(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Nhập tên phần tử cần xóa:");
        if (name != null) {
            List<Element> elements = rootElement.getChildren("Item");
            Element toRemove = null;

            for (Element el : elements) {
                if (el.getText().equals(name)) {
                    toRemove = el;
                    break;
                }
            }

            if (toRemove != null) {
                rootElement.removeContent(toRemove);
                saveXML();
            } else {
                JOptionPane.showMessageDialog(frame, "Không tìm thấy phần tử cần xóa!");
            }
        }
    }

        
    
    
    private void loadXML(ActionEvent e) {
        if (!xmlFile.exists()) {
            JOptionPane.showMessageDialog(frame, "Tập tin XML chưa tồn tại!");
            return;
        }

        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            document = saxBuilder.build(xmlFile);
            rootElement = document.getRootElement();
            
            textArea.setText("");
            for (Element el : rootElement.getChildren("Item")) {
                textArea.append(el.getText() + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Lỗi tải XML: " + ex.getMessage());
        }
    }

    private void saveXML() {
        try {
            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
            outputter.output(document, new FileWriter(xmlFile));
            loadXML(null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Lỗi lưu XML: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(XMLCrudApp::new);
    }
}
