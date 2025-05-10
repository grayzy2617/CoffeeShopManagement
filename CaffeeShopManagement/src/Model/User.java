package Model;

public class User {
    private int id;
    private String username;
    private String password;
    private int roleId;
    private String name;
    private double salary;

    public User(int id, String username, String password, int roleId, String name, double salary) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getRoleId() { return roleId; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
}
