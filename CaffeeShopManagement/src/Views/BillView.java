package Views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BillView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDay;
	private JTextField Month;
	private JTextField Year;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillView frame = new BillView();
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
	public BillView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXemChiTiet.setBounds(10, 11, 125, 40);
		contentPane.add(btnXemChiTiet);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(219, 11, 89, 25);
		contentPane.add(btnSearch);

		JLabel lblDate = new JLabel("Day");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(318, 12, 34, 25);
		contentPane.add(lblDate);

		txtDay = new JTextField();
		txtDay.setColumns(10);
		txtDay.setBounds(350, 16, 27, 20);
		contentPane.add(txtDay);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMonth.setBounds(398, 12, 46, 25);
		contentPane.add(lblMonth);

		Month = new JTextField();
		Month.setColumns(10);
		Month.setBounds(440, 16, 27, 20);
		contentPane.add(Month);

		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYear.setBounds(486, 11, 34, 25);
		contentPane.add(lblYear);

		Year = new JTextField();
		Year.setColumns(10);
		Year.setBounds(518, 16, 27, 20);
		contentPane.add(Year);

		JLabel lblHoaDon = new JLabel("Các hóa đơn");
		lblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHoaDon.setBounds(20, 62, 134, 25);
		contentPane.add(lblHoaDon);

		JLabel lblChiTiet = new JLabel("Chi tiết hóa đơn");
		lblChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChiTiet.setBounds(20, 220, 134, 25);
		contentPane.add(lblChiTiet);

		// Bảng các hóa đơn (không có dữ liệu, chỉ có tên cột)
		String[] columnNamesHoaDon = { "Mã HD", "Ngày", "Tổng tiền" };
		JTable tableHoaDon = new JTable(new Object[0][columnNamesHoaDon.length], columnNamesHoaDon);
		JScrollPane scrollPaneHoaDon = new JScrollPane(tableHoaDon);
		scrollPaneHoaDon.setBounds(20, 90, 540, 100);
		contentPane.add(scrollPaneHoaDon);

		// Bảng chi tiết hóa đơn (không có dữ liệu, chỉ có tên cột)
		String[] columnNamesChiTiet = { "Tên SP", "Số lượng", "Đơn giá" };
		JTable tableChiTiet = new JTable(new Object[0][columnNamesChiTiet.length], columnNamesChiTiet);
		JScrollPane scrollPaneChiTiet = new JScrollPane(tableChiTiet);
		scrollPaneChiTiet.setBounds(20, 250, 540, 100);
		contentPane.add(scrollPaneChiTiet);
	}
}
