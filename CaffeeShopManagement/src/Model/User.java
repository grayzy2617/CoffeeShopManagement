package Model;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
    private String name;
    private Double salary;

    public User(Integer id, String username, String password, Integer roleId, String name, Double salary) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Integer getRoleId() { return roleId; }
    public String getName() { return name; }
    public Double getSalary() { return salary; }
}
