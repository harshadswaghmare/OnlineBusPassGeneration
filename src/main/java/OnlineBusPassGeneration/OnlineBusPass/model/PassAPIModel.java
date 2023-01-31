package OnlineBusPassGeneration.OnlineBusPass.model;

public class PassAPIModel {
        int passID;

        UserLogin  userLogin;
       // PersonalDetails personalDetails;
        int userID;
        String profession;
        String source;
        String destination;
        String fromdate;
        String charge;

        String todate;
        String status;


    public PassAPIModel(int passID, int userID, String profession, String source, String destination, String charge,String status) {
        this.passID = passID;
        this.userID = userID;
        this.profession = profession;
        this.source = source;
        this.destination = destination;
        this.fromdate = fromdate;
        this.todate = todate;
        this.charge = charge;
        this.status = status;

        //this.personalDetails = personalDetails;
    }

    public PassAPIModel()
    {
        super();
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    public String  getFromdate() {
        return fromdate;
    }

    public void setFromdate(String  fromdate) {
        this.fromdate = fromdate;
    }


    public int getPassID() {
        return passID;
    }

    public void setPersonID(int personID) {
        this.passID = personID;
    }


    public String getCharge(int charge)
    {
        return this.charge;
    }

    public void setCharge() {
        this.charge = charge;
    }


    @Override
    public String toString() {
        return "PersonalDetails{" +
                "passID=" + passID +
                ", userID=" + userID +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\''+
//                ", fromdate=" + fromdate +'
//                ", todate=" + todate +'
                ", charge='" + charge +

                '}';
    }
}
