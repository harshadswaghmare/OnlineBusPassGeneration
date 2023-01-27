package OnlineBusPassGeneration.OnlineBusPass.model;

public class UserLogin {
    int userid;
    String username;
    String password;
    String email;
    String mobileNo;

    String firstname;
    String lastname;
    String userIdentity;
    int age;
    String profession;

    public UserLogin(int userid, String username, String password, String email, String mobileNo, String firstname, String lastname, String userIdentity, int age, String profession) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userIdentity = userIdentity;
        this.age = age;
        this.profession = profession;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    //    public String getCardType() {
//        return cardType;
//    }
//
//    public void setCardType(String cardType) {
//        this.cardType = cardType;
//    }


    @Override
    public String toString() {
        return "UserLogin{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                '}';
    }
}
