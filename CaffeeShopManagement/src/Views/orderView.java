package Views;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class orderView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable menuTable;
	private JTable orderTable;
	private DefaultTableModel menuTableModel;
	private DefaultTableModel orderTableModel;
	private JScrollPane orderScrollPane;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				orderView frame = new orderView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public orderView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// ComboBox + search
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(20, 10, 89, 22);
		contentPane.add(comboBox);

		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setBounds(120, 10, 100, 22);
		contentPane.add(btnSearch);

		// Table món ăn (phía trên)
		String[] columnNames = { "Tên món", "Giá", "Số lượng" };
		menuTableModel = new DefaultTableModel(null, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 2; // chỉ cột "Số lượng" cho chỉnh
			}
		};
		menuTable = new JTable(menuTableModel);
		menuTable.setRowHeight(30);

		// Custom editor cho cột số lượng
		TableColumn qtyColumn = menuTable.getColumnModel().getColumn(2);
		qtyColumn.setCellEditor(new SpinnerEditor());

		JScrollPane menuScrollPane = new JScrollPane(menuTable);
		menuScrollPane.setBounds(20, 50, 640, 150);
		contentPane.add(menuScrollPane);

		// Button "Các món đã đặt"
		JButton btnShowOrders = new JButton("Các món đã đặt");
		btnShowOrders.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShowOrders.setBounds(20, 210, 150, 30);
		contentPane.add(btnShowOrders);

		// Table đơn hàng (phía dưới)
		orderTableModel = new DefaultTableModel(new Object[] { "Tên món", "Số lượng", "Giá tiền" }, 0);
		orderTable = new JTable(orderTableModel);
		orderTable.setRowHeight(25);

		orderScrollPane = new JScrollPane(orderTable);
		orderScrollPane.setBounds(20, 250, 640, 120);
		orderScrollPane.setVisible(false); // ẩn mặc định
		contentPane.add(orderScrollPane);

		// Button Thanh toán, Reset
		JButton btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBounds(180, 380, 110, 30);
		contentPane.add(btnThanhToan);

		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(300, 380, 90, 30);
		contentPane.add(btnReset);

		JLabel lblPhone = new JLabel("Số điện thoại");
		lblPhone.setBounds(400, 380, 100, 30);
		contentPane.add(lblPhone);

		textField = new JTextField();
		textField.setBounds(500, 380, 150, 30);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setBounds(240, 10, 140, 22);
		contentPane.add(textField_1);

		// Sự kiện khi nhấn "Các món đã đặt"
		btnShowOrders.addActionListener(e -> {
			orderTableModel.setRowCount(0); // clear bảng

			for (int i = 0; i < menuTableModel.getRowCount(); i++) {
				Object name = menuTableModel.getValueAt(i, 0);
				Object price = menuTableModel.getValueAt(i, 1);
				Object qty = menuTableModel.getValueAt(i, 2);
				int q = (qty instanceof Integer) ? (Integer) qty : 0;
				if (q > 0) {
					try {
						double p = Double.parseDouble(price.toString());
						double total = p * q;
						orderTableModel.addRow(new Object[] { name, q, String.format("%,.0f đ", total) });
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
					}
				}
			}
			orderScrollPane.setVisible(true);
		});
	}

	// Cell editor dùng JSpinner
	class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
		final JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			spinner.setValue(value != null ? value : 0);
			return spinner;
		}

		public Object getCellEditorValue() {
			return spinner.getValue();
		}
	}
}
