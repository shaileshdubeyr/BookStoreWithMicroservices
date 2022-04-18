package cart.carts.dto;

public class UserDTO {
    public String userName;
    public String eMail;
    public String address;
    public String password;

    public UserDTO(String userName, String eMail, String address, String password) {
        this.userName = userName;
        this.eMail = eMail;
        this.address = address;
        this.password = password;
    }

    public UserDTO() {
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
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
