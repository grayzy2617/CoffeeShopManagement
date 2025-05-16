package Views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.CategoryController;
import Controller.ProductController;
import Model.Category;
import Model.Product;

public class addAndEditProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPrice;
	private String productID;
	private Product product;
	private JComboBox cbbCate;
	private ProductController productCtrl;
	private CategoryController categoryCtrl;

//	

	public addAndEditProduct(String idProduct) {
		categoryCtrl = new CategoryController();
		this.productID = idProduct;
		productCtrl = new ProductController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cafe Management");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 0, 202, 46);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 100, 83, 24);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(20, 160, 66, 24);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(20, 220, 66, 24);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Category");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(10, 280, 96, 24);
		contentPane.add(lblNewLabel_5);

		txtID = new JTextField();
		txtID.setBounds(100, 98, 160, 32);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(100, 158, 160, 32);
		contentPane.add(txtName);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(100, 218, 160, 32);
		contentPane.add(txtPrice);

		cbbCate = new JComboBox();
		cbbCate.setBounds(100, 278, 160, 32);
		contentPane.add(cbbCate);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(productID).isEmpty()) {
					if (addProduct()) {
						JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công", "Thành công",
								JOptionPane.OK_OPTION);
						new foodMenuView().setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);

					}

				} else {
					if (updateProduct()) {
						JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công", "Thành công",
								JOptionPane.OK_OPTION);
						new foodMenuView().setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thất bại ", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSave.setBounds(60, 352, 80, 35);
		contentPane.add(btnSave);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new foodMenuView().setVisible(true);
				dispose();
			}
		});
		btnExit.setBounds(194, 352, 80, 35);
		contentPane.add(btnExit);
		GUi();
		loadCate();
	}

	public void GUi() {
		if (!String.valueOf(productID).isEmpty()) {
			txtID.setEditable(false);
			product = productCtrl.getProduct(Integer.parseInt(this.productID));
			txtID.setText(String.valueOf(product.getId()));
			txtName.setText(product.getName());
			txtPrice.setText(String.valueOf(product.getPrice()));
		}
	}

	public boolean addProduct() {

		Product tmpProduct = new Product(Integer.parseInt(txtID.getText()), txtName.getText(),
				Double.valueOf(txtPrice.getText()), Integer.parseInt(cbbCate.getSelectedItem().toString()), "cup");
		if (productCtrl.createProduct(tmpProduct))
			return true;
		else {
			return false;
		}
	}

	public boolean updateProduct() {
		Product tmpProduct = new Product(Integer.parseInt(txtID.getText()), txtName.getText(),
				Double.valueOf(txtPrice.getText()), Integer.parseInt(cbbCate.getSelectedItem().toString()), "1");
		if (productCtrl.updateProduct(tmpProduct))
			return true;
		else {
			return false;
		}

	}

	private void loadCate() {
		try {
			List<Category> categories = categoryCtrl.getAllCategories();
			cbbCate.removeAllItems(); // Xóa các mục cũ nếu có

			for (Category cate : categories) {
				cbbCate.addItem(cate.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null, "Lỗi khi tải danh mục!");
		}
	}
}
