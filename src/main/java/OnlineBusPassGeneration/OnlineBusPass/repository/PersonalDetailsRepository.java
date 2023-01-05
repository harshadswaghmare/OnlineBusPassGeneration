//package OnlineBusPassGeneration.OnlineBusPass.repository;
//
//import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
//import OnlineBusPassGeneration.OnlineBusPass.model.PersonalDetails;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.sql.*;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class PersonalDetailsRepository {
//
//    //Select
//    public static JSONObject retrieveAllDataFromPersonalDetails()throws SQLException {
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        Connection connection = null;
//        try {
//            connection = Connectivity.CreateConnection();
//            String query = "select * from PersonalDetails";
//            System.out.println("processing Data");
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                JSONObject obj = new JSONObject();
//                obj.put("PersonalID ", rs.getInt("personalID"));
//                obj.put("UserID",rs.getInt("userID"));
//                obj.put("FirstName", rs.getString("firstname"));
//                obj.put("LastName", rs.getString("lastname"));
//                obj.put("UserIdentity", rs.getString("userIdentity"));
//                obj.put("Age", rs.getInt("age"));
//                obj.put("Source", rs.getString("source"));
//                obj.put("Destination", rs.getString("destination"));
//                obj.put("FromDate", rs.getString("fromDate"));
//                obj.put("ToDate", rs.getString("toDate"));
//                obj.put("Charge" ,rs.getString("charge"));
//
//                jsonArray.add(obj);
//            }
//            jsonObject.put("Personal Details",jsonArray);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connection.close();
//        }
//
//        return jsonObject;
//    }
//
//
//    //INSERT
//    public static String insert(PersonalDetails personalDetails) {
//
//       LocalDate date = LocalDate.now();
//       Date obj = Date.valueOf(LocalDate.now().plusDays(30));
//       String regex ="^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";
//
//
//
//        try {
//
//            Connection connection = Connectivity.CreateConnection();
//            System.out.println("Connection established successfully");
//            String query = "insert into PersonalDetails(userID,firstname,lastname,userIdentity,age,source,destination,charge,fromDate,toDate)values(?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, personalDetails.getUserID());
//            preparedStatement.setString(2, personalDetails.getFirstname());
//            preparedStatement.setString(3, personalDetails.getLastname());
//            preparedStatement.setString(4, personalDetails.getUserIdentity());
//            preparedStatement.setInt(5, personalDetails.getAge());
//            preparedStatement.setString(6, personalDetails.getSource());
//            preparedStatement.setString(7, personalDetails.getDestination());
//
//
//            if((personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("Baner")) ||
//                    (personalDetails.getSource().contains("Baner") && personalDetails.getDestination().contains("Bhumkar"))){
//                int ticket = 15;
//                int  charge =ticket*2*22 ;
//                preparedStatement.setInt(8, charge);
//            }
//
//            else if((personalDetails.getSource().contains("Hinjwadi") && personalDetails.getDestination().contains("Shivaji Nagar")) ||
//                    (personalDetails.getSource().contains("Shivaji Nagar") && personalDetails.getDestination().contains("Hinjwadi"))){
//                int ticket = 30;
//                int  charge =ticket*2*22 ;
//                preparedStatement.setInt(8, charge);
//            }
//
//            else if((personalDetails.getSource().contains("Pashan") && personalDetails.getDestination().contains("Dange Chowk")) ||
//                    (personalDetails.getSource().contains("Dange Chowk") && personalDetails.getDestination().contains("Pashan"))){
//                int ticket = 12;
//                int  charge =ticket*2*22 ;
//                preparedStatement.setInt(8, charge);
//            }
//
//            else if((personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("kothrud")) ||
//                    (personalDetails.getSource().contains("kothrud") && personalDetails.getDestination().contains("Bhumkar"))){
//                int ticket = 15;
//                int  charge =ticket*2*22 ;
//                preparedStatement.setInt(8, charge);
//            }
//
//            else if((personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("Infosys Phase3")) ||
//                    (personalDetails.getSource().contains("Infosys Phase3") && personalDetails.getDestination().contains("Bhumkar"))){
//                int ticket = 20;
//                int  charge =ticket*2*22 ;
//                preparedStatement.setInt(8, charge);
//            }
//            else{
//               return "please provide valid Source and Destination";
//            }
//
//            preparedStatement.setDate(9, Date.valueOf(date));
//            preparedStatement.setDate(10, obj);
//
//            Pattern p = Pattern.compile(regex);
//            Matcher m = p.matcher(personalDetails.getUserIdentity());
//            boolean b = m.matches();
//
//            if(b)
//            {
//                System.out.println("your Aadhaar is verified");
//            }
//            else{
//                return"Enter valid Aadhaar Number as  per Aadhaar Card";
//            }
//            preparedStatement.executeUpdate();
//
//            System.out.println("Finally Inserted");
//
//
//        } catch (Exception e) {
//            System.out.println("Exception Occurred : "+ e);
//        }
//
//        return "record  inserted successfully";
//    }
//
//    //DELETE
//    public static boolean delete(@PathVariable int PersonalID) {
//        boolean flag = false;
//        try {
//            Connection connection = Connectivity.CreateConnection();
//            String query = "Delete from PersonalDetails where personalID = ? ";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, PersonalID);
//            int rowsAffected = preparedStatement.executeUpdate();
//            if(rowsAffected > 0) {
//                System.out.println("Record Deleted  Successfully");
//                flag = true;
//            }
//            else {
//                System.out.println("operation failed");
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return flag;
//
//    }
//
//    //ADD SOURCE
//
//    public static JSONObject source(@PathVariable String source){
//       JSONObject jsonObject = new JSONObject();
//       List<String> list = new ArrayList<>();
//        list.add(source);
//        jsonObject.put("SourceList",list);
//        return jsonObject;
////        JSONObject jsonObject = new JSONObject();
////        JSONArray jsonArray = new JSONArray();
////        jsonObject.put("source ", source);
////        System.out.println(jsonObject);
////        return jsonObject;
//
//    }
//
//}
