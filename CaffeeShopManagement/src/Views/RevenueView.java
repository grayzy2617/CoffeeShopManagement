package Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.BillController;
import Controller.UserController;
import Model.Bill;
import Model.User;

public class RevenueView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	private JTable table;
	private BillController billController = new BillController();
	private UserController userController = new UserController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RevenueView frame = new RevenueView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RevenueView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Revenue Management");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 0, 207, 42);
		contentPane.add(lblNewLabel);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(227, 11, 89, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(e -> {
			try {
				int day = Integer.parseInt(txtDay.getText().trim());
				int month = Integer.parseInt(txtMonth.getText().trim());
				int year = Integer.parseInt(txtYear.getText().trim());

				// Tạo khoảng thời gian: bắt đầu và kết thúc trong ngày
				LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0, 0);
				LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59, 59);

				List<Bill> bills = billController.searchBillsByDateRange(start, end);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0); // clear table

				for (Bill bill : bills) {
					String createdAt = bill.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
					String staffName = "Unknown";
					User staff = userController.getUserById(bill.getCreatedByEmployID());
					if (staff != null) {
						staffName = staff.getName();
					}
					model.addRow(new Object[] { createdAt, staffName, bill.getTotalPrice() });
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày/tháng/năm hợp lệ.", "Lỗi",
						javax.swing.JOptionPane.ERROR_MESSAGE);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(332, 10, 34, 25);
		contentPane.add(lblNewLabel_1);

		txtDay = new JTextField();
		txtDay.setBounds(364, 14, 27, 20);
		contentPane.add(txtDay);
		txtDay.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Month");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(412, 10, 46, 25);
		contentPane.add(lblNewLabel_1_1);

		txtMonth = new JTextField();
		txtMonth.setColumns(10);
		txtMonth.setBounds(454, 14, 27, 20);
		contentPane.add(txtMonth);

		JLabel lblNewLabel_1_1_1 = new JLabel("Year");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(500, 9, 34, 25);
		contentPane.add(lblNewLabel_1_1_1);

		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(532, 14, 27, 20);
		contentPane.add(txtYear);

		// Thêm bảng JTable
		String[] columnNames = { "CreateAt", "StaffName", "Total" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); // 0 dòng ban đầu
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 60, 500, 261);
		contentPane.add(scrollPane);
	}
}
