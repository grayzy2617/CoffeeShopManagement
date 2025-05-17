package Controller;

import Db.UserDAO;
import Model.User;
import Views.Employee.ProfileView;
import Views.Employee.mainEmployeeView;

import java.sql.SQLException;

public class ProfileEmploy {
    private mainEmployeeView view;
    private UserDAO userDAO;
    private int currentUserId;

    public ProfileEmploy(mainEmployeeView view,int currentUserId) {
        this.view = view;
        this.userDAO = new UserDAO();
        this.currentUserId= currentUserId;
        initEvents();
    }

    private void initEvents() {
        view.getProfileButton().addActionListener(e -> {
            try {
                openProfile(currentUserId);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void openProfile(int id) throws SQLException {
        ProfileView profileView = new ProfileView();
        User user = userDAO.getUserById(id);

        // TODO: Thay dữ liệu cứng bằng lấy dữ liệu thực tế từ DB hoặc session người dùng
        String Id = String.valueOf(user.getId());
        String username = user.getUsername();
        String name = user.getName();
        String salary =String.valueOf(user.getSalary());

        profileView.setProfileData(Id, username, name, salary);

        profileView.setVisible(true);
    }
}
