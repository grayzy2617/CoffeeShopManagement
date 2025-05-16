package Views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class profileView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					profileView frame = new profileView();
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
	public profileView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Họ và tên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(74, 71, 112, 25);
		contentPane.add(lblNewLabel);

		JLabel lblTnngNhp = new JLabel("Tên đăng nhập");
		lblTnngNhp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTnngNhp.setBounds(36, 107, 117, 25);
		contentPane.add(lblTnngNhp);

		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMtKhu.setBounds(74, 143, 112, 25);
		contentPane.add(lblMtKhu);

		JLabel lblVaiTr = new JLabel("Vai trò");
		lblVaiTr.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVaiTr.setBounds(159, 197, 112, 25);
		contentPane.add(lblVaiTr);

		JLabel lblLng = new JLabel("Lương");
		lblLng.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLng.setBounds(159, 233, 112, 25);
		contentPane.add(lblLng);

		textField = new JTextField();
		textField.setBounds(159, 71, 208, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(159, 107, 174, 25);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(159, 143, 137, 25);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(220, 197, 186, 25);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(220, 233, 186, 25);
		contentPane.add(textField_4);

		JButton btnNewButton = new JButton("Đổi mật khẩu");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(408, 21, 156, 40);
		contentPane.add(btnNewButton);
	}

}
