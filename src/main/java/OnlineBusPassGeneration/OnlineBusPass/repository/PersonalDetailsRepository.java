package OnlineBusPassGeneration.OnlineBusPass.repository;

import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.model.PersonalDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.*;
import java.util.Date;
public class PersonalDetailsRepository {

    //Select
    public static JSONObject retrieveAllDataFromPersonalDetails()throws SQLException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        Connection connection = null;
        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from PersonalDetails";
            System.out.println("processing Data");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("personalID : ", rs.getInt("personalID"));
                obj.put("firstname : ", rs.getInt("firstname"));
                obj.put("lastname : ", rs.getInt("lastname"));
                obj.put("userIdentity : ", rs.getInt("userIdentity"));
                obj.put("age : ", rs.getInt("age"));
                obj.put("source : ", rs.getInt("source"));
                obj.put("destination : ", rs.getInt("destination"));
                obj.put("fromDate : ", rs.getInt("fromDate"));
                obj.put("toDate : ", rs.getInt("toDate"));
                obj.put("passCharge : ", rs.getInt("passCharge"));
                jsonArray.add(obj);
            }
            jsonObject.put("Personal Details",jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return jsonObject;
    }


    //INSERT
    public static String insert(PersonalDetails personalDetails) {
        boolean flag = false;
        Date date = new Date();

        try {

            Connection connection = Connectivity.CreateConnection();
            System.out.println("Connection established successfully");
            String query = "insert into PersonalDetails(userID,firstname,lastname,userIdentity,age,source,destination,charge)values(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, personalDetails.getUserID());
            preparedStatement.setString(2, personalDetails.getFirstname());
            preparedStatement.setString(3, personalDetails.getLastname());
            preparedStatement.setString(4, personalDetails.getUserIdentity());
            preparedStatement.setInt(5, personalDetails.getAge());
            preparedStatement.setString(6, personalDetails.getSource());
            preparedStatement.setString(7, personalDetails.getDestination());
           // preparedStatement.setDate(7, java.sql.Date.valueOf(now()));
//          preparedStatement.setDate(9, (Date)(personalDetails.getTodate()));
           // preparedStatement.setString(8,uniqueID);
            System.out.println("Thanks for working");


            preparedStatement.executeUpdate();
            System.out.println("Finally Inserted");
            flag = true;

        } catch (Exception e) {
            System.out.println("Exception Occurred : "+ e);
        }

        return null;

    }

    //DELETE
    public static boolean delete(@PathVariable int
                                 PersonalID) {

        boolean flag = false;
        try {
            Connection connection = Connectivity.CreateConnection();
            String query = "Delete from PersonalDetails where personalID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, PersonalID);
            preparedStatement.executeUpdate();
            if(flag == true) {
                System.out.println("Record Deleted  Successfully");
            }
            else {
                System.out.println("operation failed");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;

    }

    //ADD SOURCE

    public static String source(@PathVariable String source){
        List<String>list = new ArrayList<>();
        list.add(source);

        return source;
    }

}
