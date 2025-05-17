package Views.Employee;

import Controller.OrderEmploy;
import Db.BillDAO;
import Db.OrderItemDAO;
import Model.Bill;
import Model.OrderItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;

public class OrderSummaryView extends JFrame {
    String[] summaryColumns = {"ID", "Name", "Price", "Unit", "Quantity"};
    private DefaultTableModel summaryModel;
    private DefaultTableModel billModel;  // 🟢 Thêm dòng này vào


    private viewOrderEmploy orderView;
    private OrderEmploy orderController;

    private JTable billTable;
    private JLabel lblSubtotal, lblTax, lblTotal, lblDateTime;
    private JButton btnCancel, btnDone, btnUpdate;
    private JLabel lblDiscount;

    private int userID;


    private final double TAX_PERCENT = 50;

    public OrderSummaryView(DefaultTableModel billModel, viewOrderEmploy orderView, OrderEmploy orderController,int id) {
        this.summaryModel = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Unit", "Quantity"}, 0);

        this.billModel =billModel;
        this.orderView = orderView;
        this.orderController = orderController;

        this.userID = id;

        JPanel bottomPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        lblSubtotal = new JLabel("Subtotal: 0");
        lblDiscount = new JLabel("Discount: 0");
        lblTotal = new JLabel("Total: 0");
        bottomPanel.add(lblSubtotal);
        bottomPanel.add(lblDiscount);
        bottomPanel.add(lblTotal);

        initUI();
        transferDataFromBill(billModel);
        calculateTotals();
        initEvents();
    }

    public int getUserId() {
        return userID;
    }

    private Vector<String> getSummaryColumnIdentifiers() {
        Vector<String> cols = new Vector<>();
        cols.add("ID");
        cols.add("Name");
        cols.add("Price");
        cols.add("Unit");
        cols.add("Quantity");
        return cols;
    }


    private void initUI() {
        setTitle("Order Summary");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header thời gian
        lblDateTime = new JLabel(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
        add(lblDateTime, BorderLayout.NORTH);

        // Bảng order summary
        billTable = new JTable(summaryModel);
        JScrollPane scrollPane = new JScrollPane(billTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel dưới cho subtotal, tax, total
        JPanel panelBottom = new JPanel(new GridLayout(2, 3, 10, 10));

        lblSubtotal = new JLabel();
        lblTax = new JLabel();
        lblTotal = new JLabel();

        panelBottom.add(new JLabel("Subtotal"));
        panelBottom.add(new JLabel("Tax(%)"));
        panelBottom.add(new JLabel("Totals"));
        panelBottom.add(lblSubtotal);
        panelBottom.add(lblTax);
        panelBottom.add(lblTotal);

        add(panelBottom, BorderLayout.SOUTH);

        // Panel nút điều khiển
        JPanel panelButtons = new JPanel();

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(Color.RED);
        btnCancel.setForeground(Color.WHITE);

        btnDone = new JButton("Done");
        btnDone.setBackground(Color.GREEN);
        btnDone.setForeground(Color.BLACK);

        btnUpdate = new JButton("Update");
        btnUpdate.setBackground(Color.GREEN);
        btnUpdate.setForeground(Color.BLACK);

        panelButtons.add(btnCancel);
        panelButtons.add(btnDone);
        panelButtons.add(btnUpdate);

        add(panelButtons, BorderLayout.PAGE_END);
    }

    public double calculateSubtotal() {
        double total = 0;
        for (int i = 0; i < summaryModel.getRowCount(); i++) {
            double price = Double.parseDouble(summaryModel.getValueAt(i, 2).toString());
            int qty = Integer.parseInt(summaryModel.getValueAt(i, 4).toString());
            total += price * qty;
        }
        return total;
    }

    public double calculateDiscount(double subtotal) {
        return subtotal > 100 ? subtotal * 0.10 : 0;
    }

    public void updateTotals() {
        double subtotal = calculateSubtotal();
        double discount = calculateDiscount(subtotal);
        double finalTotal = subtotal - discount;

        lblSubtotal.setText(String.format("Subtotal: %.2f", subtotal));
        lblDiscount.setText(String.format("Discount: %.2f", discount));
        lblTotal.setText(String.format("Total: %.2f", finalTotal));
    }

    public void transferDataFromBill(DefaultTableModel billModel) {
        summaryModel.setRowCount(0);
        for (int i = 0; i < billModel.getRowCount(); i++) {
            Object[] rowData = new Object[billModel.getColumnCount() - 1];
            for (int j = 0; j < rowData.length; j++) {
                rowData[j] = billModel.getValueAt(i, j);
            }
            summaryModel.addRow(rowData);
        }
        updateTotals();
    }




    private void calculateTotals() {
        double subtotal = 0;
        for (int i = 0; i < summaryModel.getRowCount(); i++) {
            Object priceObj = summaryModel.getValueAt(i, 2);
            Object qtyObj = summaryModel.getValueAt(i, 4);

            double price = priceObj instanceof Number ? ((Number) priceObj).doubleValue() : Double.parseDouble(priceObj.toString());
            int qty = qtyObj instanceof Number ? ((Number) qtyObj).intValue() : Integer.parseInt(qtyObj.toString());

            subtotal += price * qty;
        }
        double taxAmount = subtotal * TAX_PERCENT / 100;
        double total = subtotal + taxAmount;

        lblSubtotal.setText(String.format("%,.0f vnd", subtotal));
        lblTax.setText(String.format("%.0f", TAX_PERCENT));
        lblTotal.setText(String.format("%,.0f vnd", total));
    }

    private void initEvents() {
        btnCancel.addActionListener(e -> {
            // Về Home (giả sử bạn có class HomeView)
            this.dispose();
            new mainEmployeeView(getUserId()).setVisible(true);
        });

        btnDone.addActionListener(e -> {
            try {
                // 🔹 1. Nhập số điện thoại khách hàng
                String phone = JOptionPane.showInputDialog(this, "Nhập số điện thoại khách hàng:");
                if (phone == null || phone.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại!");
                    return;
                }

                // 🔹 2. Tính tổng tiền từ summaryModel
                double total = 0;
                for (int i = 0; i < summaryModel.getRowCount(); i++) {
                    double price = Double.parseDouble(summaryModel.getValueAt(i, 2).toString());
                    int qty = Integer.parseInt(summaryModel.getValueAt(i, 4).toString());
                    total += price * qty;
                }

                // 🔹 3. Tạo bill mới
                Bill newBill = new Bill(1,phone,total,LocalDateTime.now(),orderView.getUserId());

                BillDAO billDAO = new BillDAO();
                int newBillId = billDAO.createBill(newBill);

                if (newBillId == -1) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi tạo hóa đơn!");
                    return;
                }

                // 🔹 4. Thêm các sản phẩm vào bảng orderitem
                OrderItemDAO itemDAO = new OrderItemDAO();

                for (int i = 0; i < summaryModel.getRowCount(); i++) {
                    int productId = Integer.parseInt(summaryModel.getValueAt(i, 0).toString());
                    int quantity = Integer.parseInt(summaryModel.getValueAt(i, 4).toString());
                    double price = Double.parseDouble(summaryModel.getValueAt(i, 2).toString());

                    OrderItem item = new OrderItem(1,newBillId,productId,quantity,price);
                    itemDAO.createOrderItem(item);
                }

                // 🔹 5. Thông báo & quay về trang chủ
                JOptionPane.showMessageDialog(this, "Đặt hàng thành công!");
                this.dispose();
                new mainEmployeeView(orderView.getUserId()).setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        });


        btnUpdate.addActionListener(e -> {
            // Tạo lại model đầy đủ 6 cột (bao gồm cột "Delete")
            String[] billCols = {"ID", "Name", "Price", "Unit", "Quantity", "Delete"};
            DefaultTableModel fullBillModel = billModel;
            billModel.setRowCount(0);
            // Chuyển dữ liệu từ summaryModel sang fullBillModel
            for (int i = 0; i < summaryModel.getRowCount(); i++) {
                Object[] rowData = new Object[6];
                for (int j = 0; j < 5; j++) {
                    rowData[j] = summaryModel.getValueAt(i, j);
                }
                rowData[5] = "Xóa";  // Thêm cột Delete
                billModel.addRow(rowData);
            }

            // Cập nhật lại model + renderer + editor
            orderView.setBillModel(billModel);  // Gán model mới
            orderController.setBillModel(billModel);  // Đồng bộ trong Controller

            JTable billTable = orderView.getBillTable();
            billTable.setModel(billModel);  // Gán lại bảng (bắt buộc)
            billTable.getColumn("Delete").setCellRenderer(new OrderEmploy.ButtonRenderer());
            billTable.getColumn("Delete").setCellEditor(new OrderEmploy.ButtonEditor(new JCheckBox(), billModel));

            orderView.setVisible(true);
            this.dispose();
        });





    }
}

