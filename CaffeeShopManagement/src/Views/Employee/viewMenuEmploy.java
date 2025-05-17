package Views.Employee;

import Db.ProductDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import java.util.List;


public class viewMenuEmploy extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public viewMenuEmploy(int userId) {
        this.userId= userId;
        mainEmployeeView viewOrder= new mainEmployeeView(userId);
        setTitle("Menu View");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Giao diện chính
        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(new Color(240, 240, 240));
        setContentPane(contentPane);

        JLabel lblGreeting = new JLabel("Menu", SwingConstants.CENTER);
        lblGreeting.setFont(new Font("Brush Script MT", Font.PLAIN, 55));
        lblGreeting.setBounds(0, 15, 800, 80);
        contentPane.add(lblGreeting);

        // Exit button - biến thành viên
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(354, 500, 100, 40);
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.BLACK);
        contentPane.add(btnExit);

        // Bảng sản phẩm
        String[] columns = {"ID", "Name", "Price", "Unit", "Category", "Sold"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 90, 750, 370);
        contentPane.add(scrollPane);

        // Load dữ liệu sản phẩm + số lượng đã bán
        ProductDAO productDAO = new ProductDAO();
        List<Object[]> productList = productDAO.getAllWithSoldCount();
        model.setRowCount(0);
        for (Object[] row : productList) {
            model.addRow(row);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        btnExit.addActionListener(e -> {
            this.dispose();  // đóng cửa sổ order hiện tại
            viewOrder.setVisible(true);
        }); // hiện lại Home
    }
    public static void main (String[]args){
        SwingUtilities.invokeLater(() -> new viewMenuEmploy(1).setVisible(true));
    }
}