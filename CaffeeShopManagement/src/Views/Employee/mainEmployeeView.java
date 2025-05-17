package Views.Employee;

import Controller.BillController;
import Controller.BillViewController;
import Controller.OrderEmploy;
import Controller.ProfileEmploy;
import Views.BillView;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class mainEmployeeView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnProfile;
	private static int userID;

//	public int getUserId() {
//		return userID;
//	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				mainEmployeeView frame = new mainEmployeeView(userID);
				ProfileEmploy profileController = new ProfileEmploy(frame,2);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	public JButton getProfileButton() {
		return btnProfile;
	}

	public mainEmployeeView(int id) {
		setTitle("Employee Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);

		userID= id;



		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 230));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// PANEL chứa các nút menu trên, dùng GridLayout để cách đều
		JPanel topMenuPanel = new JPanel(new GridLayout(1, 5, 20, 0));
		topMenuPanel.setBounds(80, 20, 640, 50);
		topMenuPanel.setBackground(new Color(230, 230, 230));
		contentPane.add(topMenuPanel);

		JButton btnHome = new JButton("HOME");
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHome.setBackground(Color.GREEN); // xanh lá cây
		topMenuPanel.add(btnHome);

		JButton btnMenu = new JButton("MENU");
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		topMenuPanel.add(btnMenu);

		JButton btnOrder = new JButton("Order");
		btnOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		topMenuPanel.add(btnOrder);

		btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		topMenuPanel.add(btnProfile);

		JButton btnBill = new JButton("Bills");
		btnBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		topMenuPanel.add(btnBill);

		// Chữ "Xin chào"
		JLabel lblGreeting = new JLabel("Xin chào", SwingConstants.CENTER);
		lblGreeting.setFont(new Font("Brush Script MT", Font.PLAIN, 48));
		lblGreeting.setBounds(0, 130, 800, 80);
		contentPane.add(lblGreeting);

		// Đường kẻ ngang
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 250, 720, 2);
		contentPane.add(separator);

		// Nút Exit
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(350, 320, 100, 40);
		btnExit.setBackground(Color.LIGHT_GRAY);
		contentPane.add(btnExit);

		ProfileEmploy profileController = new ProfileEmploy(this,userID);
		btnBill.addActionListener(e -> {
			BillEmployView billView = new BillEmployView();
            try {
                BillViewController billViewController = new BillViewController(billView, userID);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            billView.setVisible(true);
		});
		btnOrder.addActionListener(e -> {
			System.out.println(userID);
			viewOrderEmploy orderView = new viewOrderEmploy(userID);
			OrderEmploy orderController = new OrderEmploy(orderView,userID);
			orderView.setVisible(true);

			// Ẩn Home nếu muốn, hoặc giữ Home mở:
//			this.setVisible(false);
		});
		btnMenu.addActionListener(e -> {
			viewMenuEmploy menuView = new viewMenuEmploy(userID);
			menuView.setVisible(true);
			this.setVisible(false);
		});



	}


}
