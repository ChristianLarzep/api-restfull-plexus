
package models;

/**
 *
 * @author christian larzep
 */
public class User {
    private long id_user;
    private String username;
    private String email;
    private String password;
    private String role;
    private long cell;
    private long tel;
    private String rfc;
    private String r_social;
    
    public User(){}

    public long getUserId() {
        return id_user;
    }

    public void setUserId(long userId) {
        this.id_user = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getCell() {
        return cell;
    }

    public void setCell(long cell) {
        this.cell = cell;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getR_social() {
        return r_social;
    }

    public void setR_social(String r_social) {
        this.r_social = r_social;
    }
}
