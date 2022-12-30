package OnlineBusPassGeneration.OnlineBusPass.model;

public class PersonalDetails {


        int personID;

        UserLogin  userLogin;
        int userID;
        String firstname;
        String lastname;
        String userIdentity;
        int age;
        String source;
        String destination;
        String fromdate;
//        Date todate;
    String charge;
  //  String passType;


    public PersonalDetails(int personID, int userID, String firstname, String lastname, String userIdentity, int age, String source, String destination,String Date,String charge ) {
        this.personID = personID;
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userIdentity = userIdentity;
        this.age = age;
        this.source = source;
        this.destination = destination;
        this.fromdate = fromdate;
//      this.todate = todate;
        this.charge = charge;


    }



	public PersonalDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

//    public PersonalDetails(int userID, String firstname, String lastname, String userIdentity,int age, String source, String destination) {
//        this.userID = userID;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.userIdentity = userIdentity;
//        this.age = age;
//        this.source = source;
//        this.destination = destination;
//        this.fromdate = fromdate;
//        //this.todate = todate;
//
//    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String  getFromdate() {
        return fromdate;
    }

    public void setFromdate(String  fromdate) {
        this.fromdate = fromdate;
    }
//
//    public Date getTodate() {
//        return todate;
//    }
//
//    public void setTodate(Date todate) {
//        this.todate = todate;
//    }

    public int getPersonID() {
        return personID;
    }
        public void setPersonID(int personID) {
        this.personID = personID;
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

    public String getCharge(int charge) {
        return this.charge;
    }

    public void setCharge() {
        this.charge = charge;
    }

//    public String getPassType() {
//        return passType;
//    }
//
//    public void setPassType(String passType) {
//        this.passType = passType;
//    }

    @Override
    public String toString() {
        return "PersonalDetails{" +
                "personID=" + personID +
                ", userID=" + userID +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", age=" + age +'\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\''+
//                ", fromdate=" + fromdate +'
//                ", todate=" + todate +'
                ", charge='" + charge +

                '}';
    }
}
