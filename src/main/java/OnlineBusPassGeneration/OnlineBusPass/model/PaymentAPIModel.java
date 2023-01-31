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
    int amount;

    int passID;

    //*******newly added

    String date;
    String status;
    //************


    public PaymentAPIModel(int payID, int userID,int passID, String cardType, String cardNo, String cardExpiry, int cvv, String nameOnCard, String transactionID, int amount,String date) {
        this.payID = payID;
        this.userID = userID;
        this.passID = passID;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.cardExpiry = cardExpiry;
        this.cvv = cvv;
        this.nameOnCard = nameOnCard;
        this.transactionID = transactionID;
        this.amount = amount;
        this.date = date;



    }
    public PaymentAPIModel ()
    {

    }

    public int getPassID() {
        return passID;
    }

    public void setPassID(int passID) {
        this.passID = passID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getAmount(int charge) {
        return this.amount;
    }

    public void setAmount(int charge) {
        this.amount = amount;
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
                ", amount=" + amount +
                ", passID=" + passID +
                ", fromdate='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

