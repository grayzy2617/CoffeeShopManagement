package Views.Employee;

import Controller.BillViewController;
import Db.BillDAO;
import Model.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class BillEmployView extends JFrame {
    private JTable billTable; // bảng danh sách hóa đơn
    private DefaultTableModel billTableModel;

    public BillEmployView() {
        // Khởi tạo UI, bảng...

        String[] columns = {"ID", "Phone", "Total Price", "Created At"};
        billTableModel = new DefaultTableModel(columns, 0);
        billTable = new JTable(billTableModel);

        // Bọc bảng vào JScrollPane để có thanh cuộn
        JScrollPane scrollPane = new JScrollPane(billTable);

        // Thêm JScrollPane vào JFrame, set layout BorderLayout để bảng chiếm toàn bộ diện tích
        this.setLayout(new java.awt.BorderLayout());
        this.add(scrollPane, java.awt.BorderLayout.CENTER);

        // Thiết lập kích thước, vị trí, đóng ứng dụng khi tắt cửa sổ
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    // Hàm hiển thị danh sách hóa đơn lên bảng
    public void displayBills(List<Bill> bills) {
        billTableModel.setRowCount(0);
        for (Bill bill : bills) {
            billTableModel.addRow(new Object[]{
                    bill.getId(),
                    bill.getPhoneNumberCus(),
                    bill.getTotalPrice(),
                    bill.getCreatedAt()
            });
        }
    }

    // Main test hiển thị
    public static void main(String[] args) throws SQLException {
        int testUserId = 2; // giả lập user ID để test

        // Tạo view hóa đơn
        BillEmployView billView = new BillEmployView();

        // Tạo controller, truyền view + userID test
        BillViewController billController = new BillViewController(billView, testUserId);

        // Hiển thị view
        billView.setVisible(true);
    }
}
