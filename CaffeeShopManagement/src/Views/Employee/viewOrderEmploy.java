package Views.Employee;

import Controller.OrderEmploy;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class viewOrderEmploy extends JFrame {
    private DefaultTableModel menuModel, billModel;
    private JTable menuTable, billTable;
    private JTextField txtID, txtQuantity;
    private JLabel lblTotal;
    private JButton btnOk, btnDone, btnExit;  // Đổi thành biến thành viên

    private mainEmployeeView homeView;


    private int userID;

    public int getUserId() {
        return userID;
    }

    public viewOrderEmploy(int userID) {
        this.userID= userID;
        setTitle("Order");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(new Color(240, 240, 240));
        setContentPane(contentPane);

        // Menu label
        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblMenu.setBounds(20, 10, 100, 25);
        contentPane.add(lblMenu);

        // Menu table
        String[] menuCols = {"ID", "Name", "Price", "Unit"};
        menuModel = new DefaultTableModel(menuCols, 0);
        menuTable = new JTable(menuModel);
        JScrollPane spMenu = new JScrollPane(menuTable);
        spMenu.setBounds(20, 40, 420, 200);
        contentPane.add(spMenu);

        // Bill label
        JLabel lblBill = new JLabel("Bill");
        lblBill.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblBill.setBounds(20, 270, 100, 25);
        contentPane.add(lblBill);

        // Bill table
        String[] billCols = {"ID", "Name", "Price", "Unit", "Quantity", "Delete"};
        billModel = new DefaultTableModel(billCols, 0) {
            public boolean isCellEditable(int row, int col) {
                return col == 5; // chỉ cột "Delete" được sửa (nút)
            }
        };


        billTable = new JTable(billModel);
        JScrollPane spBill = new JScrollPane(billTable);
        spBill.setBounds(20, 300, 420, 200);
        contentPane.add(spBill);

        // Tổng tiền label
        lblTotal = new JLabel("Total: 0");
        lblTotal.setBounds(20, 510, 400, 30);
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(lblTotal);

        // Tiêu đề Order
        JLabel lblTitle = new JLabel("Order");
        lblTitle.setFont(new Font("Brush Script MT", Font.BOLD, 60));
        lblTitle.setBounds(600, 50, 200, 60);
        contentPane.add(lblTitle);

        // Selected Item label
        JLabel lblSelected = new JLabel("Selected Item");
        lblSelected.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblSelected.setBounds(590, 208, 150, 30);
        contentPane.add(lblSelected);

        // ID input
        JLabel lblID = new JLabel("ID");
        lblID.setBounds(513, 270, 60, 25);
        contentPane.add(lblID);

        txtID = new JTextField();
        txtID.setBounds(570, 270, 250, 25);
        contentPane.add(txtID);

        // Quantity input
        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(500, 310, 60, 25);
        contentPane.add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(570, 310, 250, 25);
        contentPane.add(txtQuantity);

        // OK button - gán giá trị cho biến thành viên
        btnOk = new JButton("Ok");
        btnOk.setBounds(610, 360, 150, 40);
        btnOk.setBackground(Color.GREEN);
        btnOk.setForeground(Color.BLACK);
        contentPane.add(btnOk);

        // Exit button - biến thành viên
        btnExit = new JButton("Exit");
        btnExit.setBounds(540, 500, 100, 40);
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.BLACK);
        contentPane.add(btnExit);

        // Done button - biến thành viên
        btnDone = new JButton("Done");
        btnDone.setBounds(670, 500, 100, 40);
        btnDone.setBackground(Color.GREEN);
        btnDone.setForeground(Color.BLACK);
        contentPane.add(btnDone);
        homeView = new mainEmployeeView(getUserId());
        btnExit.addActionListener(e -> {
            mainEmployeeView viewHome = new mainEmployeeView(userID);
            this.dispose();  // đóng cửa sổ order hiện tại
            viewHome.setVisible(true);  // hiện lại Home
        });

    }

    // Getters
    public JTable getMenuTable() { return menuTable; }
    public JTable getBillTable() { return billTable; }
    public DefaultTableModel getMenuModel() { return menuModel; }
    public DefaultTableModel getBillModel() { return billModel; }
    public JTextField getTxtID() { return txtID; }
    public JTextField getTxtQuantity() { return txtQuantity; }
    public JButton getBtnOk() { return btnOk; }
    public JButton getBtnDone() { return btnDone; }
    public JButton getBtnExit() { return btnExit; }
    public JLabel getLblTotal() { return lblTotal; }

    public void setBillModel(DefaultTableModel model) {
        this.billModel = model;
        this.billTable.setModel(model);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            viewOrderEmploy view = new viewOrderEmploy(1);
            OrderEmploy service = new OrderEmploy(view, view.getUserId());  // TẠO ĐỐI TƯỢNG orderEmploy để khởi tạo logic, load dữ liệu
            view.setVisible(true);
        });
    }
}
