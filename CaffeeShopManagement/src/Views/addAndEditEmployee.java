package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import Controller.UserController;
import Model.User;

public class addAndEditEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtFullName;
	private JTextField txtID;
	private JComboBox<String> cbbRole;
	private String idEmployee;
	private JTextField txtSalary;
	private UserController userController;

	public addAndEditEmployee(String idEmploy) {
		this.idEmployee = idEmploy;
		userController = new UserController();
		setTitle("Thêm / Sửa nhân viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 212);
		setLocationRelativeTo(null); // canh giữa màn hình

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		JLabel lblUsername = new JLabel("Tên đăng nhập:");
		JLabel lblPassword = new JLabel("Mật khẩu:");
		JLabel lblFullName = new JLabel("Họ và tên:");
		JLabel lblSalary = new JLabel("ID");
		JLabel lblRole = new JLabel("Vai trò:");

		txtUsername = new JTextField(20);
		txtPassword = new JPasswordField(20);
		txtFullName = new JTextField(20);
		txtID = new JTextField(20);
		cbbRole = new JComboBox<>(new String[] { "Quản lý", "Nhân viên" });

		JButton btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSave();
			}
		});
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new staffManagerView().setVisible(true);
				dispose();
			}
		});

		JLabel lblId = new JLabel("Lương");

		txtSalary = new JTextField(20);

		GroupLayout layout = new GroupLayout(contentPane);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER).addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(lblUsername)
						.addComponent(lblPassword).addComponent(lblFullName))
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(txtUsername, 167, 167, 167)
						.addComponent(txtPassword, 167, 167, 167).addComponent(txtFullName, 167, 167, 167))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(lblSalary)
								.addComponent(lblRole))
						.addGroup(Alignment.TRAILING,
								layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtSalary, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtID, 167, 167, 167).addComponent(cbbRole, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(38))
				.addGroup(layout.createSequentialGroup()
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE).addGap(20)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblUsername)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSalary).addComponent(txtID, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRole).addComponent(cbbRole, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblFullName)
								.addComponent(txtFullName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId).addComponent(txtSalary, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(20).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnSave)
								.addComponent(btnExit))));
		contentPane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		loadRole();
		loadUserData();

	}

	public void loadRole() {
		List<String> Roles = List.of("Quản lý", "Nhân viên");
		for (String role : Roles) {
			cbbRole.addItem(role);
		}
	}

	private void loadUserData() {
		if (!idEmployee.isEmpty()) {
			User user = userController.getUserById(Integer.parseInt(idEmployee));
			if (user != null) {
				txtID.setText(idEmployee);
				txtID.setEditable(false);
				txtUsername.setText(user.getUsername());
				txtPassword.setText(user.getPassword());
				txtFullName.setText(user.getName());
				txtSalary.setText(String.valueOf(user.getSalary()));

				// Chọn vai trò tương ứng trong comboBox
				String roleName = (user.getRoleId() == 1) ? "Quản lý" : "Nhân viên";
				cbbRole.setSelectedItem(roleName);
			}
		}
	}

	private void handleSave() {
		int id = Integer.parseInt(txtID.getText());
		String username = txtUsername.getText().trim();
		String password = new String(txtPassword.getPassword());
		String fullName = txtFullName.getText().trim();
		Double salaryStr = Double.valueOf(txtSalary.getText().trim());

		if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		int roleId = cbbRole.getSelectedItem().toString().equals("Quản lý") ? 1 : 2;

		User user = new User(id, username, password, roleId, fullName, salaryStr);
		boolean result;
		if (idEmployee == null || idEmployee.trim().isEmpty()) {

			result = userController.addUser(user);
			if (result) {
				JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
				new staffManagerView().setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else {

			result = userController.updateUser(user);
			if (result) {
				JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!");
				new staffManagerView().setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
