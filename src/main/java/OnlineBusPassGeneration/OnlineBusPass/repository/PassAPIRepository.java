package OnlineBusPassGeneration.OnlineBusPass.repository;

import OnlineBusPassGeneration.OnlineBusPass.OnlineBusPassApplication;
import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel;
import OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel;
import org.apache.coyote.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Queue;

import static java.sql.Date.*;

public class PassAPIRepository {

   static Connection connection = null;
   static PreparedStatement preparedStatement = null;
   static Logger log = LoggerFactory.getLogger(OnlineBusPassApplication.class);


    //Select Personal Details Records
    public static ResponseEntity<JSONObject> retrieveAllDataFromPersonalDetails(PassAPIModel passAPIModel) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        Connection connection = null;
        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from PersonalDetails";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("personalID ", rs.getInt("passID"));
                obj.put("userID", rs.getInt("userID"));
                obj.put("profession", rs.getString("profession"));
                obj.put("source", rs.getString("source"));
                obj.put("destination", rs.getString("destination"));
                obj.put("fromDate", rs.getString("fromDate"));
                obj.put("toDate", rs.getString("toDate"));
                obj.put("charge", rs.getString("charge"));
                obj.put("status",rs.getString("status"));
                jsonArray.add(obj);
            }
             jsonObject.put("Personal Details", jsonArray);

        } catch (Exception e) {
            log.error("exception occurred :" + e);
        } finally {
            connection.close();
        }
        System.out.println(jsonObject);
        return ResponseEntity.ok(jsonObject);

    }



    //****************   Insert PersonalDetails  *****************
    public static String insertPersonal(PassAPIModel passAPIModel) {

        LocalDate date = LocalDate.now();
        Date obj = valueOf(LocalDate.now().plusDays(30));
        // UserLogin userLogin = new UserLogin();
        try {
            connection = Connectivity.CreateConnection();
            log.info("Connection established successfully");
            String preQuery = "select * from userLogin where userId = ?";
            preparedStatement = connection.prepareStatement(preQuery);
            preparedStatement.setInt(1, passAPIModel.getUserID());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                    String query = "insert into PersonalDetails(userID,profession,source,destination,charge,fromDate,toDate,status)values(?,?,?,?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, passAPIModel.getUserID());
                    preparedStatement.setString(2, passAPIModel.getProfession());
                    preparedStatement.setString(3, passAPIModel.getSource());
                    preparedStatement.setString(4, passAPIModel.getDestination());
                    if ((passAPIModel.getProfession().contains("Student")) && (passAPIModel.getSource().contains("Bhumkar") && passAPIModel.getDestination().contains("Baner")) ||
                            (passAPIModel.getSource().contains("Baner") && passAPIModel.getDestination().contains("Bhumkar"))) {
                        int ticket = 15;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 35) / 100;
                        preparedStatement.setInt(5, charge);

                    } else if ((passAPIModel.getProfession().contains("Student")) && (passAPIModel.getSource().contains("Hinjwadi") && passAPIModel.getDestination().contains("Shivaji Nagar")) ||
                            (passAPIModel.getSource().contains("Shivaji Nagar") && passAPIModel.getDestination().contains("Hinjwadi"))) {
                        int ticket = 30;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 35) / 100;
                        preparedStatement.setInt(5, charge);

                    } else if ((passAPIModel.getProfession().contains("Student")) && (passAPIModel.getSource().contains("Pashan") && passAPIModel.getDestination().contains("Dange Chowk")) ||
                            (passAPIModel.getSource().contains("Dange Chowk") && passAPIModel.getDestination().contains("Pashan"))) {
                        int ticket = 12;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 35) / 100;
                        preparedStatement.setInt(5, charge);

                    } else if ((passAPIModel.getProfession().contains("Student")) && (passAPIModel.getSource().contains("Bhumkar") && passAPIModel.getDestination().contains("kothrud")) ||
                            (passAPIModel.getSource().contains("kothrud") && passAPIModel.getDestination().contains("Bhumkar"))) {
                        int ticket = 15;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 35) / 100;
                        preparedStatement.setInt(5, charge);

                    } else if ((passAPIModel.getProfession().contains("Student")) && (passAPIModel.getSource().contains("Bhumkar") && passAPIModel.getDestination().contains("Infosys Phase3")) ||
                            (passAPIModel.getSource().contains("Infosys Phase3") && passAPIModel.getDestination().contains("Bhumkar"))) {
                        int ticket = 20;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 40) / 100;
                        preparedStatement.setInt(5, charge);

                    }
                    //
                    else if ((passAPIModel.getProfession().contains("Senior Citizen")) && (passAPIModel.getSource().contains("Bhumkar") && passAPIModel.getDestination().contains("Baner")) ||
                            (passAPIModel.getSource().contains("Baner") && passAPIModel.getDestination().contains("Bhumkar"))) {
                        int ticket = 15;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 40) / 100;
                        preparedStatement.setInt(5, charge);

                    } else if ((passAPIModel.getProfession().contains("Senior Citizen")) && (passAPIModel.getSource().contains("Hinjwadi") && passAPIModel.getDestination().contains("Shivaji Nagar")) ||
                            (passAPIModel.getSource().contains("Shivaji Nagar") && passAPIModel.getDestination().contains("Hinjwadi"))) {
                        int ticket = 30;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 40) / 100;
                        preparedStatement.setInt(5, charge);

                    } else if (((passAPIModel.getProfession().contains("Senior Citizen")) && (passAPIModel.getSource().contains("Pashan") && passAPIModel.getDestination().contains("Dange Chowk")) ||
                            (passAPIModel.getSource().contains("Dange Chowk") && passAPIModel.getDestination().contains("Pashan")))) {
                        int ticket = 12;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 40) / 100;
                        preparedStatement.setInt(5, charge);

                    } else if ((passAPIModel.getProfession().contains("Senior Citizen")) && (passAPIModel.getSource().contains("Bhumkar") && passAPIModel.getDestination().contains("kothrud")) ||
                            (passAPIModel.getSource().contains("kothrud") && passAPIModel.getDestination().contains("Bhumkar"))) {
                        int ticket = 15;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 40) / 100;
                        preparedStatement.setInt(5, charge);

                    } else if ((passAPIModel.getProfession().contains("Senior Citizen")) && (passAPIModel.getSource().contains("Bhumkar") && passAPIModel.getDestination().contains("Infosys Phase3")) ||
                            (passAPIModel.getSource().contains("Infosys Phase3") && passAPIModel.getDestination().contains("Bhumkar"))) {
                        int ticket = 20;
                        int amount = (ticket * 2 * 30);
                        int charge = (amount * 40) / 100;
                        preparedStatement.setInt(5, charge);

                    } else {
                        if (passAPIModel.getSource().isEmpty()) {
                            return "enter source";
                        } else if (passAPIModel.getDestination().isEmpty()) {
                            return "enter destination";
                        } else if (passAPIModel.getProfession().isEmpty()) {
                            return "enter profession";
                        } else {
                            int charge = 2000;
                            preparedStatement.setInt(5, charge);
                        }
                    }

                    preparedStatement.setDate(6, Date.valueOf(passAPIModel.getFromdate()));
                    preparedStatement.setDate(7, Date.valueOf(valueOf(passAPIModel.getFromdate()).toLocalDate().plusDays(30)));

                    preparedStatement.setString(8,"Pending");

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        log.info("user Data saved Successfully");
                        return "user details submit successfully";
                    }

                } else {
                    //log.info(passAPIModel.getUserID() + " not exist");
                    return passAPIModel.getUserID()+" not exist";
                }


        } catch (Exception e) {
            log.error("Exception Occurred : " + e);
        }
        return null;
    }


//    public static ResponseEntity<String> update(@RequestBody int passID, PassAPIModel passAPIModel) {
//        LocalDate date = LocalDate.now();
//        Date obj = Date.valueOf(LocalDate.now().plusDays(30));
//        try {
//
//            //  if (personalDetails.getTodate().contains(date1)) {
//            connection = Connectivity.CreateConnection();
//            String query ="select * from personaldetails where passID = ?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1,passID);
//            ResultSet rs = preparedStatement.executeQuery();
//            if(!rs.next()) {
//                String Query = "update PersonalDetails  set source =?, destination=?, charge =?,  fromdate = ?, todate = ? where passID = " + passID;
//                preparedStatement = connection.prepareStatement(Query);
//                preparedStatement.setString(1, passAPIModel.getSource());
//                preparedStatement.setString(2, passAPIModel.getDestination());
//                if ((passAPIModel.getSource().contains("Bhumkar") && passAPIModel.getDestination().contains("Baner")) ||
//                        (passAPIModel.getSource().contains("Baner") && passAPIModel.getDestination().contains("Bhumkar"))) {
//                    int ticket = 15;
//                    int amount = (ticket * 2 * 30);
//                    int charge = (amount * 35) / 100;
//                    preparedStatement.setInt(3, charge);
//
//                } else if ((passAPIModel.getSource().contains("Dange Chowk") && passAPIModel.getDestination().contains("Pashan")) ||
//                        (passAPIModel.getSource().contains("Pashan") && passAPIModel.getDestination().contains("Dange Chowk"))) {
//                    int ticket = 12;
//                    int amount = (ticket * 2 * 30);
//                    int charge = (amount * 35) / 100;
//                    preparedStatement.setInt(3, charge);
//
//                } else if ((passAPIModel.getSource().contains("Hinjwadi") && passAPIModel.getDestination().contains("Shivaji Nagar")) ||
//                        (passAPIModel.getSource().contains("Shivaji Nagar") && passAPIModel.getDestination().contains("Hinjwadi"))) {
//                    int ticket = 30;
//                    int amount = (ticket * 2 * 30);
//                    int charge = (amount * 35) / 100;
//                    preparedStatement.setInt(3, charge);
//
//                } else {
//                    int amount = 1600;
//                    preparedStatement.setInt(3, amount);
//                }
//                preparedStatement.setDate(4, Date.valueOf(passAPIModel.getFromdate()));
//                preparedStatement.setDate(5, obj);
//                //  preparedStatement.setInt(6, passAPIModel.getPassID());
//
//                int rowsAffected = preparedStatement.executeUpdate();
//                try {
//                    if (rowsAffected > 0) {
//                        return ResponseEntity.ok("pass renewed successfully");
//                    }
//                }catch (Exception e)
//                {
//                    System.out.println("Exception occurred :"+e);
//                }
//            }
//        } catch (Exception e) {
//            log.info("exception occurred : " + e);
//           System.out.println("Exception occurred"+e);
//        }
//
//        return null;
//    }



    public static ResponseEntity<String> updateInsertPersonalDetails(@RequestBody int PassID, PassAPIModel passAPIModel) {
        LocalDate date = LocalDate.now();
        Date obj = valueOf(LocalDate.now().plusDays(30));
        try {

            connection = Connectivity.CreateConnection();
            String query ="select * from personaldetails where passID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,PassID);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                String Query = "update PersonalDetails  set source =?, destination=?, charge =?,  fromdate = ?, todate = ? where passID =" + PassID;
                preparedStatement = connection.prepareStatement(Query);
                preparedStatement.setString(1, passAPIModel.getSource());
                preparedStatement.setString(2, passAPIModel.getDestination());
                if ((passAPIModel.getSource().contains("Bhumkar") && passAPIModel.getDestination().contains("Baner")) ||
                        (passAPIModel.getSource().contains("Baner") && passAPIModel.getDestination().contains("Bhumkar"))) {
                    int ticket = 15;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 35) / 100;
                    preparedStatement.setInt(3, charge);

                } else if ((passAPIModel.getSource().contains("Dange Chowk") && passAPIModel.getDestination().contains("Pashan")) ||
                        (passAPIModel.getSource().contains("Pashan") && passAPIModel.getDestination().contains("Dange Chowk"))) {
                    int ticket = 12;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 35) / 100;
                    preparedStatement.setInt(3, charge);

                } else if ((passAPIModel.getSource().contains("Hinjwadi") && passAPIModel.getDestination().contains("Shivaji Nagar")) ||
                        (passAPIModel.getSource().contains("Shivaji Nagar") && passAPIModel.getDestination().contains("Hinjwadi"))) {
                    int ticket = 30;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 35) / 100;
                    preparedStatement.setInt(3, charge);

                } else {
                    int amount = 1600;
                    preparedStatement.setInt(3, amount);
                }
                preparedStatement.setDate(4, Date.valueOf(passAPIModel.getFromdate()));
                preparedStatement.setDate(5, Date.valueOf(valueOf(passAPIModel.getFromdate()).toLocalDate().plusDays(30)));
                //preparedStatement.setInt(6, passAPIModel.getPassID());


                int rowsAffected = preparedStatement.executeUpdate();
                try {
                    if (rowsAffected > 0) {
                        return ResponseEntity.ok("Pass Updated Successfully, now you can pay");

                    }
                } catch (Exception e) {
                    System.out.println("Exception occurred" + e);
                }
            }



                else{
                return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
            }


        } catch (Exception e) {
            log.info("exception occurred : " + e);
        }
        return null;
    }




    //  *******************  Delete from pass only if not exist in userlogin  ***********//
     public static Boolean deletePassAPI(@PathVariable int passID) {
        boolean flag = false;
        try {
            connection = Connectivity.CreateConnection();
            String query = "Delete from PersonalDetails where passID = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, passID);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                  new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
                flag = true;
            } else {
                 new ResponseEntity<>("No record found with ID " + passID,HttpStatus.NOT_FOUND);
                 flag = false;
            }

        } catch (Exception e) {
            log.error("Exception occurred : " + e);
        }
       return flag;
    }

}
