package OnlineBusPassGeneration.OnlineBusPass.model;

import java.sql.Date;

public class PaymentModule
{

    int payID;
    int userID;
    String cardType;
    String cardNo;
    String cardExpiry;
    int cvv;
    String nameOnCard;
    String transactionID;

    PaymentModule paymentModule;

    public PaymentModule(int payID, int userID, String cardType, String cardNo, String cardExpiry, int cvv, String nameOnCard, String transactionID, PaymentModule paymentModule) {
        this.payID = payID;
        this.userID = userID;
        this.cardType = cardType;
        this.cardNo = cardNo;
        this.cardExpiry = cardExpiry;
        this.cvv = cvv;
        this.nameOnCard = nameOnCard;
        this.transactionID = transactionID;
        this.paymentModule = paymentModule;
    }
    public PaymentModule ()
    {

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
}

