package OnlineBusPassGeneration.OnlineBusPass.repository;

import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel;
import OnlineBusPassGeneration.OnlineBusPass.model.PaymentAPIModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class PaymentAPIRepositoy {

    private static Logger log = LoggerFactory.getLogger(PaymentAPIRepositoy.class);
    private static final String uniqueID = UUID.randomUUID().toString();
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    //Insert payment or make payment
    public static String insertPaymentModule(PaymentAPIModel paymentAPIModel, PassAPIModel passAPIModel) {
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
            if (rs.next()) {
                int charge = rs.getInt(1);
//                String Query = "select * from personalDetails where personalID = ?";
//                PreparedStatement preparedStatement1 = connection.prepareStatement(Query);
//                preparedStatement1.setInt(1, paymentModule.getPersonalID());
//                ResultSet vs = preparedStatement1.executeQuery();
//                if (vs.next()) {
                System.out.println("Connection established successfully");
                String query = "insert into paymentDetails(userID,cardType,cardNo,cardExpiry,cvv,nameOnCard,transactionID,passID,amount,date)values(?,?,?,?,?,?,?,?,?,?)";
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
                preparedStatement2.setDate(10, Date.valueOf(date));

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

                    String updateQuery = "update personalDetails set status = " + "'Active'" + " where passID =?";
                    preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, paymentAPIModel.getPassID());

                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {

                        return "payment successful";

                    }
//                    } else {
//                        return null;
//
//                    }
                } else {
                    return "sorry.! no record found against " + paymentAPIModel.getPassID();
                }
            } else {
                return "PassID " + paymentAPIModel.getPassID() + " Not Found";
            }
        } catch (Exception e) {
            log.info("exception occurred : " + e);
        }
        return "UserID " + paymentAPIModel.getUserID() + " not found";
    }


    //********************************  Total Rupees of the Day  **********************************

    //Eg. select sum(charge)from personalDetails where fromDate ='2023-01-01'
    public static String selectTotalOfDate(@PathVariable Date date) throws SQLException {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int sum = 0;

        try {
            connection = Connectivity.CreateConnection();
            String query = "select sum(amount)from paymentDetails where date =?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, date);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Total = rs.getInt(1);
                sum = sum + Total;
                log.info(String.valueOf("Audit of the date " + date + ": Rs." + sum));
                return ("Audit of the date " + date + ": Rs." + sum);

            }

        } catch (Exception e) {
            log.warn("Exception Occurred : " + e);
        } finally {
            connection.close();
        }
        return null;

    }


    //*****************  Display total Amount of FromDate To ToDate *******************
    // Eg. select sum(charge)from personalDetails where fromDate between '2023-01-01'and '2023-01-07'
    public static String CalculateTotalFromDateToDate(@PathVariable Date date, @PathVariable Date todate) throws SQLException {

        JSONObject jsonObject = new JSONObject();
        int sum = 0;
        try {
            connection = Connectivity.CreateConnection();
            String query = "select sum(amount)from PaymentDetails where date between " + "'" + date + "'" + " and " + "' " + todate + " '";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Total = rs.getInt(1);
                sum = Total + sum;
                log.info("Total " + date + " to " + todate + " is : " + sum);
                return ("Total audit from date " + date + " to " + todate + " is : " + sum);
            }
        } catch (Exception e) {
           log.info(e.getMessage());
        }
        return null;
    }
}

