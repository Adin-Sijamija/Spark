package e.adin_pc.spark;

/**
 * Created by Adin-PC on 3/29/2018.
 */

public class Users {


    int id;
    String UserName;
    String Password;
    String Email;
    String Birthday;
    Boolean Is_Admin;

    public Users(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public void setIs_Admin(Boolean is_Admin) {
        Is_Admin = is_Admin;
    }

    public int getId() {

        return id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getBirthday() {
        return Birthday;
    }

    public Boolean getIs_Admin() {
        return Is_Admin;
    }

    public Users(int id, String userName, String password, String email, String birthday, Boolean is_Admin) {

        this.id = id;
        UserName = userName;
        Password = password;
        Email = email;
        Birthday = birthday;
        Is_Admin = is_Admin;
    }
}
