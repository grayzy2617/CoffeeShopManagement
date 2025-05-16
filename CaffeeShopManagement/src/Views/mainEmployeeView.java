package Views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class mainEmployeeView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainEmployeeView frame = new mainEmployeeView();
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
	public mainEmployeeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHome.setBounds(62, 11, 89, 38);
		contentPane.add(btnHome);

		JButton btnMenu = new JButton("Menu");
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMenu.setBounds(181, 11, 89, 38);
		contentPane.add(btnMenu);

		JButton btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProfile.setBounds(303, 11, 89, 38);
		contentPane.add(btnProfile);

		JButton btnBill = new JButton("Bills");
		btnBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBill.setBounds(422, 11, 89, 38);
		contentPane.add(btnBill);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(247, 314, 89, 38);
		contentPane.add(btnExit);
	}

}
