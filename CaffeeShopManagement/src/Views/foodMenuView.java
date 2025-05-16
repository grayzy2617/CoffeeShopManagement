package Views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.CategoryController;
import Controller.ProductController;
import Model.Category;
import Model.Product;

public class foodMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable tableFood;
	private DefaultTableModel tableModel;
	private ProductController productCtrl;
	private CategoryController categoryCtrl;

	JComboBox cbbCate;

	public foodMenuView() {
		productCtrl = new ProductController();
		categoryCtrl = new CategoryController();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cbbCate = new JComboBox();
		cbbCate.setBounds(77, 11, 89, 22);
		contentPane.add(cbbCate);

		JLabel lblNewLabel = new JLabel("Loại");
		lblNewLabel.setBounds(21, 15, 64, 14);
		contentPane.add(lblNewLabel);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadProducts();
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(342, 5, 100, 30);
		contentPane.add(btnTimKiem);

		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(452, 9, 124, 26);
		contentPane.add(txtSearch);

		JButton btnThemMoi = new JButton("Thêm mới");
		btnThemMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addAndEditProduct("").setVisible(true);
				dispose();
			}
		});
		btnThemMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemMoi.setBounds(21, 296, 111, 38);
		contentPane.add(btnThemMoi);

		JButton btnChinhSua = new JButton("Chỉnh sửa");
		btnChinhSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableFood.getSelectedRow(); // lấy chỉ số dòng đang chọn
				if (selectedRow == -1) {
					// Nếu chưa chọn dòng nào
					javax.swing.JOptionPane.showMessageDialog(null, "Vui lòng chọn một món để chỉnh sửa!");
					return;
				}
				// Lấy giá trị ở cột đầu tiên (ID) tại dòng được chọn
				int id = (int) tableModel.getValueAt(selectedRow, 0);

				// Mở form chỉnh sửa, truyền ID vào
				new addAndEditProduct(String.valueOf(id)).setVisible(true);
				dispose();
			}
		});
		btnChinhSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChinhSua.setBounds(142, 296, 111, 38);
		contentPane.add(btnChinhSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableFood.getSelectedRow();

				if (selectedRow == -1) {
					javax.swing.JOptionPane.showMessageDialog(null, "Vui lòng chọn một món để xóa!");
					return;
				}

				int confirm = javax.swing.JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa món này?",
						"Xác nhận xóa", javax.swing.JOptionPane.YES_NO_OPTION);

				if (confirm == javax.swing.JOptionPane.YES_OPTION) {
					int id = (int) tableModel.getValueAt(selectedRow, 0);

					boolean result = productCtrl.deleteProduct(id);
					if (result) {
						javax.swing.JOptionPane.showMessageDialog(null, "Xóa món thành công!");
						loadProducts(); // cập nhật lại bảng sau khi xóa
					} else {
						javax.swing.JOptionPane.showMessageDialog(null, "Xóa thất bại!");
					}
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoa.setBounds(274, 296, 111, 38);
		contentPane.add(btnXoa);

		// Tạo bảng với DefaultTableModel
		String[] columnNames = { "ID", "Tên món", "Giá tiền" };
		tableModel = new DefaultTableModel(columnNames, 0); // 0 là số dòng ban đầu
		tableFood = new JTable(tableModel);
		tableFood.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(tableFood);
		scrollPane.setBounds(42, 50, 500, 220);
		contentPane.add(scrollPane);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainEmployeeView().setVisible(true);
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(418, 296, 111, 38);
		contentPane.add(btnExit);

		// Gọi hàm để load dữ liệu món ăn vào bảng
		loadProducts();
		loadCate();
	}

	private void loadProducts() {
		List<Product> productList = productCtrl.searchProduct(txtSearch.getText());
		tableModel.setRowCount(0); // Xóa các dòng cũ (nếu có)

		for (Product p : productList) {
			Object[] row = { p.getId(), p.getName(), p.getPrice() };
			tableModel.addRow(row);
		}
	}

	private void loadCate() {
		try {
			List<Category> categories = categoryCtrl.getAllCategories();
			cbbCate.removeAllItems(); // Xóa các mục cũ nếu có
			cbbCate.addItem("Tất cả"); // Mục mặc định để hiển thị tất cả món

			for (Category cate : categories) {
				cbbCate.addItem(cate.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null, "Lỗi khi tải danh mục!");
		}
	}
}
