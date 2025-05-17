package Views.Employee;


import javax.swing.*;
import java.awt.*;

public class ProfileView extends JFrame {
    private JTextField txtID, txtUsername, txtName, txtSalary;
    private JButton btnExit;

    public ProfileView() {
        setTitle("Profile");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng cửa sổ này thôi, không thoát app

        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(new Color(230, 230, 230));
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Profile");
        lblTitle.setFont(new Font("Brush Script MT", Font.BOLD, 36));
        lblTitle.setBounds(120, 20, 150, 40);
        contentPane.add(lblTitle);

        JLabel lblID = new JLabel("ID");
        lblID.setBounds(40, 80, 80, 25);
        contentPane.add(lblID);

        txtID = new JTextField();
        txtID.setBounds(130, 80, 150, 25);
        txtID.setEditable(false); // không cho sửa nếu muốn
        contentPane.add(txtID);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(40, 120, 80, 25);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(130, 120, 150, 25);
        txtUsername.setEditable(false);
        contentPane.add(txtUsername);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(40, 160, 80, 25);
        contentPane.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 160, 150, 25);
        txtName.setEditable(false);
        contentPane.add(txtName);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setBounds(40, 200, 80, 25);
        contentPane.add(lblSalary);

        txtSalary = new JTextField();
        txtSalary.setBounds(130, 200, 150, 25);
        txtSalary.setEditable(false);
        contentPane.add(txtSalary);

        btnExit = new JButton("Exit");
        btnExit.setBounds(120, 260, 100, 35);
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        // Nút thoát đóng cửa sổ Profile
        btnExit.addActionListener(e -> dispose());
    }

    // Hàm set dữ liệu profile
    public void setProfileData(String id, String username, String name, String salary) {
        txtID.setText(id);
        txtUsername.setText(username);
        txtName.setText(name);
        txtSalary.setText(salary);
    }
//    public static void main(String[] args) {
//        ProfileView profileView = new ProfileView();
//        profileView.setProfileData("001", "user01", "John Doe", "10,000,000 VND");
//        profileView.setVisible(true);
//    }

}

