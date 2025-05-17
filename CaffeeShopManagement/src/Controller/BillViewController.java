package Controller;

import Db.BillDAO;
import Model.Bill;
import Views.Employee.BillEmployView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class BillViewController {
    private BillDAO billDAO;
    private BillEmployView billView;
    private int userId;

    public BillViewController(BillEmployView billView, int userId) throws SQLException {
        this.billView = billView;
        this.userId = userId;
        this.billDAO = new BillDAO();
        loadBills();
    }

    private void loadBills() throws SQLException {
        try {
            List<Bill> bills = billDAO.getBillsByEmployeeId(userId);
            billView.displayBills(bills);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(billView, "Lỗi tải hóa đơn: " + e.getMessage());
        }
    }
}

