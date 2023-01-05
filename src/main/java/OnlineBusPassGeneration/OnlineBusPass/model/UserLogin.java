package OnlineBusPassGeneration.OnlineBusPass.model;

public class UserLogin {
    int userid;
    String username;
    String password;
    String email;

    String cardType;
    String mobileNo;

    PersonalDetails personalDetails;


    public UserLogin(int userid, String username, String password, String email,String cardType,String mobileNo,PersonalDetails personalDetails) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cardType = cardType;
        this.mobileNo = mobileNo;
        this.personalDetails  = personalDetails;
    }

    public UserLogin() {
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
