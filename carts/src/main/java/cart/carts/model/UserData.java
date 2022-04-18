package cart.carts.model;

import cart.carts.dto.UserDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class UserData {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String eMail;
    private String password;
    private String address;
    private boolean verified;
    public UserData() {
    }

    public UserData(UserDTO userDTO){
    this.userName = userDTO.userName;
    this.eMail = userDTO.eMail;
    this.address = userDTO.address;
    this.password = userDTO.password;
    this.verified = false;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\''+
                '}';
    }
}
