package Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.UserController;
import Model.User;

public class staffManagerView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;
	private UserController userController = new UserController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffManagerView frame = new staffManagerView();
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
	public staffManagerView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Staff Management");
		lblNewLabel.setBounds(24, 11, 170, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);

		// Thêm JTable với 4 cột
		String[] columnNames = { "ID", "Username", "Fullname", "Salary" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); // 0 dòng ban đầu
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(24, 50, 552, 210); // đúng, dùng số 50 thay vì 'fifty'
		contentPane.add(scrollPane);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addAndEditEmployee("").setVisible(true);
				dispose();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(24, 288, 90, 27);
		contentPane.add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow(); // lấy chỉ số dòng đang chọn
				if (selectedRow == -1) {
					// Nếu chưa chọn dòng nào
					javax.swing.JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để chỉnh sửa");
					return;
				}
				// Lấy giá trị ở cột đầu tiên (ID) tại dòng được chọn
				int id = (int) tableModel.getValueAt(selectedRow, 0);

				new addAndEditEmployee(String.valueOf(id)).setVisible(true);
				dispose();
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEdit.setBounds(134, 288, 90, 27);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 nhân viên để xóa");
					return;
				}

				int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc sẽ xóa nhân viên này không", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (confirm != JOptionPane.YES_OPTION)
					return;

				Integer userId = (Integer) table.getValueAt(selectedRow, 0);
				boolean success = userController.deleteUser(userId);
				if (success) {
					JOptionPane.showMessageDialog(null, "Xóa thành công");
					loadAllStaff();
				} else {
					JOptionPane.showMessageDialog(null, "Xóa thất bại");
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(245, 288, 90, 27);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để xóa");
					return;
				}

				int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa nhân viên này chứ?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (confirm != JOptionPane.YES_OPTION)
					return;

				Integer userId = (Integer) table.getValueAt(selectedRow, 0);
				boolean success = userController.deleteUser(userId);
				if (success) {
					JOptionPane.showMessageDialog(null, "Xóa thành công");
					loadAllStaff();
				} else {
					JOptionPane.showMessageDialog(null, "Xóa thất bại ");
				}
			}
		});

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText().trim();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				List<User> users = userController.searchUserByName(keyword);
				for (User user : users) {
					model.addRow(new Object[] { user.getId(), user.getUsername(), user.getName(), user.getSalary() });
				}
			}
		});

		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(359, 288, 90, 27);
		contentPane.add(btnSearch);

		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		txtSearch.setBounds(461, 290, 115, 27);
		contentPane.add(txtSearch);

		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainAdminView().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(236, 327, 99, 36);
		contentPane.add(btnNewButton_1);
		loadAllStaff();
	}

	private void loadAllStaff() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Clear dữ liệu cũ

		List<User> users = userController.getUsers();
		for (User user : users) {
			model.addRow(new Object[] { user.getId(), user.getUsername(), user.getName(), user.getSalary() });
		}
	}

}
