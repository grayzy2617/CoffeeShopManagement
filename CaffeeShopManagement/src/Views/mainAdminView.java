package Views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class mainAdminView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainAdminView frame = new mainAdminView();
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
	public mainAdminView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Admin Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(78, 11, 168, 28);
		contentPane.add(lblNewLabel);

		JButton btnMenuManagement = new JButton("Menu Management");
		btnMenuManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new foodMenuView().setVisible(true);
				dispose();
			}
		});
		btnMenuManagement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMenuManagement.setBounds(54, 74, 224, 40);
		contentPane.add(btnMenuManagement);

		JButton btnStaffManagement = new JButton("Staff Management");
		btnStaffManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new staffManagerView().setVisible(true);
				dispose();
			}
		});
		btnStaffManagement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStaffManagement.setBounds(54, 149, 224, 40);
		contentPane.add(btnStaffManagement);

		JButton btnRevenueManagement = new JButton("Revenue Management");
		btnRevenueManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RevenueView().setVisible(true);
				dispose();
			}
		});
		btnRevenueManagement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRevenueManagement.setBounds(54, 228, 224, 40);
		contentPane.add(btnRevenueManagement);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginView().setVisible(true);
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(119, 319, 112, 40);
		contentPane.add(btnExit);
	}

}
