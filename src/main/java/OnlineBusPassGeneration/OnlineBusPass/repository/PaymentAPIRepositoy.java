package OnlineBusPassGeneration.OnlineBusPass.repository;

import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel;
import OnlineBusPassGeneration.OnlineBusPass.model.PaymentAPIModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;

public class PaymentAPIRepositoy {

    private static Logger log = LoggerFactory.getLogger(PaymentAPIRepositoy.class);
    private static final String uniqueID = UUID.randomUUID().toString();
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    //Insert payment or make payment
    public static String insertPaymentModule(PaymentAPIModel paymentAPIModel,PassAPIModel passAPIModel) {
        LocalDate date = LocalDate.now();
        //PassAPIModel passAPIModel = null;
        try {

             connection = Connectivity.CreateConnection();
            System.out.println("Connection established successfully");
            String prequery = "select charge from personalDetails where passID = ?";
            preparedStatement = connection.prepareStatement(prequery);
            preparedStatement.setInt(1, paymentAPIModel.getPassID());
            //preparedStatement.setString(2,userLogin.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int charge = rs.getInt(1);
//                String Query = "select * from personalDetails where personalID = ?";
//                PreparedStatement preparedStatement1 = connection.prepareStatement(Query);
//                preparedStatement1.setInt(1, paymentModule.getPersonalID());
//                ResultSet vs = preparedStatement1.executeQuery();
//                if (vs.next()) {
                System.out.println("Connection established successfully");
                String query = "insert into paymentDetails(userID,cardType,cardNo,cardExpiry,cvv,nameOnCard,transactionID,passID,amount)values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query);
                preparedStatement2.setInt(1, paymentAPIModel.getUserID());
                preparedStatement2.setString(2, paymentAPIModel.getCardType());
                preparedStatement2.setString(3, paymentAPIModel.getCardNo());
                preparedStatement2.setString(4, paymentAPIModel.getCardExpiry());
                preparedStatement2.setInt(5, paymentAPIModel.getCvv());
                preparedStatement2.setString(6, paymentAPIModel.getNameOnCard());
                preparedStatement2.setString(7, String.valueOf(uniqueID));
                preparedStatement2.setInt(8, paymentAPIModel.getPassID());
                preparedStatement2.setInt(9, charge);

                if (String.valueOf(paymentAPIModel.getPassID()).isEmpty()) {
                    return "Enter personalID";
                }
                if (String.valueOf(paymentAPIModel.getCvv()).isEmpty()) {
                    return "Enter cvv";
                }
                if (paymentAPIModel.getCardExpiry().isEmpty()) {
                    return "Enter card Expiry Date";
                }
                if (paymentAPIModel.getCardNo().isEmpty()) {
                    return "Enter card number";
                }

                int rowsAffected = preparedStatement2.executeUpdate();
                if (rowsAffected > 0) {

                    String updateQuery = "update personalDetails set status = " + "'Active'" +" where passID =?";
                    preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1,paymentAPIModel.getPassID());

                    int result =preparedStatement.executeUpdate();
                    if(result > 0){

                      return "payment successful";

                    }
//                    } else {
//                        return null;
//                    }
                } else {
                    return "sorry.! no record found against " + paymentAPIModel.getPassID();
                }
            }
        }catch (Exception e) {
            log.info("exception occurred : " + e);
        }
        return "sorry no user found against "+paymentAPIModel.getPassID();
    }
}
