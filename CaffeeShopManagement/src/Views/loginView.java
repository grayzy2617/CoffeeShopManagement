package Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.LoginController;
import Db.UserDAO;
import Model.User;
import Views.Employee.mainEmployeeView;

public class loginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private LoginController loginController = new LoginController(); // thêm dòng này
	private UserDAO userDAO = new UserDAO();
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginView frame = new loginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public loginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(135, 32, 71, 46);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(59, 120, 87, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(59, 205, 87, 25);
		contentPane.add(lblNewLabel_1_1);

		txtUsername = new JTextField();
		txtUsername.setBounds(59, 150, 207, 34);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(59, 231, 207, 34);
		contentPane.add(txtPassword);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();

				boolean isValid = loginController.login(username, password);
				if (isValid) {
					try {
						user = userDAO.getUserByUsername(username);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (user.getRoleId() == 1)
						new mainAdminView().setVisible(true);
					else if (user.getRoleId() == 2) {
						int userId=user.getId();
						new mainEmployeeView(userId).setVisible(true);

					}
					dispose(); // đóng cửa sổ login
				} else {
					JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOk.setBackground(new Color(255, 255, 255));
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOk.setBounds(117, 313, 107, 34);
		contentPane.add(btnOk);
	}

}
