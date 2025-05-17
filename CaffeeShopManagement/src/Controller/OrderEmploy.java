package Controller;

import Model.Product;
import Db.ProductDAO;
import Views.Employee.OrderSummaryView;
import Views.Employee.viewOrderEmploy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.DefaultCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class OrderEmploy {
    private viewOrderEmploy view;
    private DefaultTableModel billModel;
    private List<Product> productList;
    private ProductDAO productDAO;
    private  int userID;

    private OrderSummaryView summaryView;

    public OrderEmploy(viewOrderEmploy view, int id) {
        this.view = view;
        this.billModel = view.getBillModel();
        productDAO = new ProductDAO();

        this.userID =id;
        loadMenuData();
        initEvents();
    }

    private void loadMenuData() {
        try {
            productList = productDAO.getAllProducts();
            DefaultTableModel menuModel = view.getMenuModel();
            menuModel.setRowCount(0);
            for (Product p : productList) {
                menuModel.addRow(new Object[]{
                        p.getId(), p.getName(), p.getPrice(), p.getUnit()
                });
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi load menu: " + e.getMessage());
        }
    }

    public void setBillModel(DefaultTableModel model) {
        this.billModel = model;
        view.setBillModel(model);  // cập nhật luôn bảng hiển thị
    }

    public DefaultTableModel getBillModel() {
        return billModel;
    }


    public int getUserId() {
        return userID;
    }

    private void initEvents() {
        view.getBtnOk().addActionListener(e -> addItemToBill());
        // Gọi để mở giao diện OrderSummaryView
        view.getBtnDone().addActionListener(e -> {
            OrderSummaryView summaryView = new OrderSummaryView(
                    billModel, // model gốc, không phải summaryModel
                    view,
                    this,getUserId()
            );
            summaryView.setVisible(true);
            view.setVisible(false);
        });

        view.getBtnExit().addActionListener(e -> System.exit(0));

        // Renderer & Editor cho nút Delete ở bảng bill
        view.getBillTable().getColumn("Delete").setCellRenderer(new ButtonRenderer());
        view.getBillTable().getColumn("Delete")
                .setCellEditor(new ButtonEditor(new JCheckBox(), billModel));

    }

    private void showOrderSummary() {
        if (summaryView == null) {
            summaryView = new OrderSummaryView(billModel, view, this,getUserId());
        }
        summaryView.setVisible(true);
        view.setVisible(false);
    }

    public void updateBillModel(DefaultTableModel updatedModel) {
        this.billModel = updatedModel;
        view.setBillModel(billModel);
        view.getBillTable().setModel(billModel);
        updateTotal();
    }


    private void addItemToBill() {
        String idText = view.getTxtID().getText().trim();
        String qtyText = view.getTxtQuantity().getText().trim();

        if (idText.isEmpty() || qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập ID và Số lượng!");
            return;
        }

        int id, qty;
        try {
            id = Integer.parseInt(idText);
            qty = Integer.parseInt(qtyText);
            if (qty <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "ID và Số lượng phải là số nguyên dương!");
            return;
        }

        Product selectedProduct = productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy món với ID: " + id);
            return;
        }

        boolean found = false;
        for (int i = 0; i < billModel.getRowCount(); i++) {
            if ((Integer) billModel.getValueAt(i, 0) == id) {
                int currentQty = (Integer) billModel.getValueAt(i, 4);
                billModel.setValueAt(currentQty + qty, i, 4);
                found = true;
                break;
            }
        }

        if (!found) {
            billModel.addRow(new Object[]{
                    selectedProduct.getId(),
                    selectedProduct.getName(),
                    selectedProduct.getPrice(),
                    selectedProduct.getUnit(),
                    qty,
                    "Xóa"
            });
        }

        updateTotal();
        view.getTxtID().setText("");
        view.getTxtQuantity().setText("");
    }

    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < billModel.getRowCount(); i++) {
            Object priceObj = billModel.getValueAt(i, 2);
            Object qtyObj = billModel.getValueAt(i, 4);
            double price = 0;
            int qty = 0;
            if (priceObj instanceof Number) price = ((Number) priceObj).doubleValue();
            if (qtyObj instanceof Number) qty = ((Number) qtyObj).intValue();
            total += price * qty;
        }
        view.getLblTotal().setText(String.format("Total: %, .0f", total));
    }


    public void doneOrder() {
        DefaultTableModel billModel = view.getBillModel();  // Lấy model hiện tại đang dùng

        OrderSummaryView summaryView = new OrderSummaryView(billModel, view, this,getUserId()); // TRUYỀN CHUẨN
        summaryView.transferDataFromBill(billModel);  // Bỏ cột Delete, chép đúng dữ liệu
        summaryView.setVisible(true);

        view.dispose();
    }



    // Renderer nút Xóa
    public static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText(value == null ? "Xóa" : value.toString());
            return this;
        }
    }

    // Editor nút Xóa
    public static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private DefaultTableModel model;
        private int currentRow;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
            super(checkBox);
            this.model = model;

            button = new JButton("Xóa");
            button.setOpaque(true);
            button.setBackground(Color.RED);
            button.setForeground(Color.WHITE);

            button.addActionListener((ActionEvent e) -> {
                if (currentRow >= 0 && currentRow < model.getRowCount()) {
                    model.removeRow(currentRow);
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return "Xóa";
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            isPushed = true;
            currentRow=row;
            return button;
        }
    }




}
