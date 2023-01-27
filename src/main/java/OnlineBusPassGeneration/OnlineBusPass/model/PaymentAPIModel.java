package OnlineBusPassGeneration.OnlineBusPass.model;

public class PaymentAPIModel
{
    int payID;
    int userID;
    String cardType;
    String cardNo;
    String cardExpiry;
    int cvv;
    String nameOnCard;
    String transactionID;
    int charge;

    int passID;

    //*******newly added

    String fromdate;
    String todate;
    String status;
    //************
    OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel passAPIModel;
    UserLogin userLogin;

    public PaymentAPIModel(int payID, int userID, int passID, String cardType, String cardNo, String cardExpiry, int cvv, String nameOnCard, String transactionID,PassAPIModel passAPIModel, UserLogin userLogin, int charge, String fromdate, String todate, String status) {
        this.payID = payID;
        this.userID = userID;
        this.passID = passID;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.cardExpiry = cardExpiry;
        this.cvv = cvv;
        this.nameOnCard = nameOnCard;
        this.transactionID = transactionID;
        this.passAPIModel = passAPIModel;
        this.userLogin = userLogin;
        this.charge = charge;
        this.fromdate = fromdate;
        this.todate = todate;
        this.status = status;

    }
    public PaymentAPIModel ()
    {

    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public int getCharge(int charge) {
        return this.charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getPassID() {
        return passID;
    }

    public void setPassID(int personalID) {
        this.passID = personalID;
    }

    public int getPayID() {
        return payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    @Override
    public String toString() {
        return "PaymentAPIModel{" +
                "payID=" + payID +
                ", userID=" + userID +
                ", cardType='" + cardType + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardExpiry='" + cardExpiry + '\'' +
                ", cvv=" + cvv +
                ", nameOnCard='" + nameOnCard + '\'' +
                ", transactionID='" + transactionID + '\'' +
                ", charge=" + charge +
                ", passID=" + passID +
                ", fromdate='" + fromdate + '\'' +
                ", todate='" + todate + '\'' +
                ", status='" + status + '\'' +
                ", passAPIModel=" + passAPIModel +
                ", userLogin=" + userLogin +
                '}';
    }
}

