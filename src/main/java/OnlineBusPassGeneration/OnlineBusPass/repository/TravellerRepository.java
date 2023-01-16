package OnlineBusPassGeneration.OnlineBusPass.repository;

import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.model.PaymentModule;
import OnlineBusPassGeneration.OnlineBusPass.model.PersonalDetails;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravellerRepository {
    private static final String uniqueID = UUID.randomUUID().toString();
    private static final String mobileRegex = ("(0/91)?[7-9][0-9]{9}");
    private static final String aadhaarRegex = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";
    private static final String password = "^(?=.*[0-9])"  //at least one digit should be
            + "(?=.*[a-z])(?=.*[A-Z])"      // at least one lowercase and uppercase should be there
            + "(?=.*[@#$%^&+=])"            //at least one character should be
            + "(?=\\S+$).{8,20}$";         // Minimum and maximum Length
    private static final String email = "^[a-z0-9._]+@(.+)$";
    public static Logger log = LoggerFactory.getLogger(TravellerRepository.class);
    private static List<String> sourceList;
    private static JSONObject object = new JSONObject();

    //*****************  Insert record into userLogin  ***********************
    public static String insert(UserLogin userLogin) {
        PreparedStatement preparedStatement = null;

        try {

            Connection connection = Connectivity.CreateConnection();
            System.out.println("Connection established successfully");
            String prequery = "select * from userLogin where username = ?";
            preparedStatement = connection.prepareStatement(prequery);
            preparedStatement.setString(1, userLogin.getUsername());
            //preparedStatement.setString(2,userLogin.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                String query = "insert into UserLogin(userName,email,password,uniqueID,mobileNo)values(?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userLogin.getUsername());
                preparedStatement.setString(2, userLogin.getEmail());
                preparedStatement.setString(3, userLogin.getPassword());
                preparedStatement.setString(4, uniqueID);
                preparedStatement.setString(5, userLogin.getMobileNo());

                if (Pattern.matches(email, userLogin.getEmail())) {  //Email Validation
                    log.info(null);
                } else {
                    return "enter correct emailID";
                }

                if (Pattern.matches(password, userLogin.getPassword())) {  //password validation
                    log.info(null);
                } else {
                    return "password should have at least one uppercase letter,one lowercase letter one character" +
                            "and length should be between 8 - 20";
                }

                Pattern mobile = Pattern.compile(mobileRegex);    //mobileNo Validation
                Matcher m1 = mobile.matcher(userLogin.getMobileNo());
                boolean b1 = m1.matches();
                if (b1) {
                    log.info("mobile no is verified");
                } else {
                    return "Enter valid mobile no";
                }

                if (userLogin.getUsername().isEmpty()) {
                    log.info("enter user name");
                    return "enter username";
                } else if (userLogin.getPassword().isEmpty()) {
                    log.info("enter password");
                    return ("return password");
                } else if (userLogin.getEmail().isEmpty()) {
                    log.info("enter email");
                    return ("return email");
                } else if (userLogin.getMobileNo().isEmpty()) {
                    log.info("Enter Mobile number");
                    return ("Enter Mobile Number");
                }


                preparedStatement.setString(1, userLogin.getCardType());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0)
                    return "user created successfully";
            }

        } catch (Exception e) {
            log.info("exception occurred : " + e);
        }
        log.info(userLogin.getUsername() + " already exist");
        return userLogin.getUsername() + " already exist";
    }

    //Delete User Record
    public static boolean deleteFromUserLogin(@PathVariable int UserID) {
        boolean flag = false;
        try {
            Connection connection = Connectivity.CreateConnection();
            String query = "Delete from userLogin where userID = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, UserID);
            int a = preparedStatement.executeUpdate();
            if (a > 0) {
                log.info("User deleted Successfully");
                flag = true;
            } else {
                log.info("No user found against userID : " + UserID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    //Update user Data
    public static String updateUserLogin(@PathVariable int userID, UserLogin userLogin) {
        String query = "update userLogin set email = ?,password =?, mobileNo=? where userID =" + userID;
        try {

            Connection connection = Connectivity.CreateConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userLogin.getEmail());
            preparedStatement.setString(2, userLogin.getPassword());
            preparedStatement.setString(3, userLogin.getMobileNo());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected);
            if (rowsAffected > 0) {
                return "row updated successfully";
            } else {
                return "no any action perform";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "record updated successfully";
    }

    //find User by ID

    public static JSONObject findByID(@PathVariable int userID) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        // PreparedStatement preparedStatement = null;
        // Connection connection = null;
        Connection connection = null;
        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from UserLogin where userID = ?";
            System.out.println("processing Data from database");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", rs.getInt("userid"));
                obj.put("username", rs.getString("username"));
                obj.put("email", rs.getString("email"));
                obj.put("password", rs.getString("password"));
                obj.put("mobileNo", rs.getString("mobileNo"));

                jsonArray.add(obj);
            }
             jsonObject.put("pass",jsonArray);
            if(jsonObject.isEmpty())
            {
                log.info("no data found");
            }else{
                System.out.println(jsonObject);
                return jsonObject;
            }

        } catch (Exception e) {
            log.info("Exception Occurred" + e);
        } finally {
            connection.close();
        }
         //return jsonObject;
      return jsonObject;
    }

    //Select User Data
    public static JSONObject retrieveAllData() throws SQLException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from UserLogin order by userid asc";
            log.debug("processing Data");
            log.info(query);
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                JSONObject obj = new JSONObject();

                obj.put("userid", rs.getInt("userid"));
                obj.put("username: ", rs.getString("username"));
                obj.put("email", rs.getString("email"));
                obj.put("password", rs.getString("password"));
                obj.put("UniqueID", rs.getString("uniqueID"));
                obj.put("mobileNo", rs.getString("mobileNo"));

                jsonArray.add(obj);
            }
            jsonObject.put("User_Data", jsonArray);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
        }
        System.out.println(jsonObject);
        return jsonObject;

    }


    //***********************************************************************************************************************************************************************//


    //****************   Insert PersonalDetails  *****************
    public static String insertPersonal(PersonalDetails personalDetails) {

        LocalDate date = LocalDate.now();
        Date obj = Date.valueOf(LocalDate.now().plusDays(30));
        // UserLogin userLogin = new UserLogin();
        try {
            Connection connection = Connectivity.CreateConnection();
            System.out.println("Connection established successfully");
            String prequery = "select * from userLogin where userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(prequery);
            preparedStatement.setInt(1, personalDetails.getUserID());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String query = "insert into PersonalDetails(userID,firstname,lastname,userIdentity,age,profession,source,destination,charge,fromDate,toDate)values(?,?,?,?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, personalDetails.getUserID());
                preparedStatement.setString(2, personalDetails.getFirstname());
                preparedStatement.setString(3, personalDetails.getLastname());
                preparedStatement.setString(4, personalDetails.getUserIdentity());
                preparedStatement.setInt(5, personalDetails.getAge());
                preparedStatement.setString(6, personalDetails.getProfession());
                preparedStatement.setString(7, personalDetails.getSource());
                preparedStatement.setString(8, personalDetails.getDestination());


                if ((personalDetails.getProfession().contains("Student")) && (personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("Baner")) ||
                        (personalDetails.getSource().contains("Baner") && personalDetails.getDestination().contains("Bhumkar"))) {
                    int ticket = 15;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 35) / 100;
                    preparedStatement.setInt(9, charge);

                } else if ((personalDetails.getProfession().contains("Student")) && (personalDetails.getSource().contains("Hinjwadi") && personalDetails.getDestination().contains("Shivaji Nagar")) ||
                        (personalDetails.getSource().contains("Shivaji Nagar") && personalDetails.getDestination().contains("Hinjwadi"))) {
                    int ticket = 30;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 35) / 100;
                    preparedStatement.setInt(9, charge);

                } else if ((personalDetails.getProfession().contains("Student")) && (personalDetails.getSource().contains("Pashan") && personalDetails.getDestination().contains("Dange Chowk")) ||
                        (personalDetails.getSource().contains("Dange Chowk") && personalDetails.getDestination().contains("Pashan"))) {
                    int ticket = 12;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 35) / 100;
                    preparedStatement.setInt(9, charge);

                } else if ((personalDetails.getProfession().contains("Student")) && (personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("kothrud")) ||
                        (personalDetails.getSource().contains("kothrud") && personalDetails.getDestination().contains("Bhumkar"))) {
                    int ticket = 15;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 35) / 100;
                    preparedStatement.setInt(9, charge);

                } else if ((personalDetails.getProfession().contains("Student")) && (personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("Infosys Phase3")) ||
                        (personalDetails.getSource().contains("Infosys Phase3") && personalDetails.getDestination().contains("Bhumkar"))) {
                    int ticket = 20;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 40) / 100;
                    preparedStatement.setInt(9, charge);

                }
                //
                else if ((personalDetails.getProfession().contains("Senior Citizen")) && (personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("Baner")) ||
                        (personalDetails.getSource().contains("Baner") && personalDetails.getDestination().contains("Bhumkar"))) {
                    int ticket = 15;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 40) / 100;
                    preparedStatement.setInt(9, charge);

                } else if ((personalDetails.getProfession().contains("Senior Citizen")) && (personalDetails.getSource().contains("Hinjwadi") && personalDetails.getDestination().contains("Shivaji Nagar")) ||
                        (personalDetails.getSource().contains("Shivaji Nagar") && personalDetails.getDestination().contains("Hinjwadi"))) {
                    int ticket = 30;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 40) / 100;
                    preparedStatement.setInt(9, charge);

                } else if (((personalDetails.getProfession().contains("Senior Citizen")) && (personalDetails.getSource().contains("Pashan") && personalDetails.getDestination().contains("Dange Chowk")) ||
                        (personalDetails.getSource().contains("Dange Chowk") && personalDetails.getDestination().contains("Pashan")))) {
                    int ticket = 12;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 40) / 100;
                    preparedStatement.setInt(9, charge);

                } else if ((personalDetails.getProfession().contains("Senior Citizen")) && (personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("kothrud")) ||
                        (personalDetails.getSource().contains("kothrud") && personalDetails.getDestination().contains("Bhumkar"))) {
                    int ticket = 15;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 40) / 100;
                    preparedStatement.setInt(9, charge);

                } else if ((personalDetails.getProfession().contains("Senior Citizen")) && (personalDetails.getSource().contains("Bhumkar") && personalDetails.getDestination().contains("Infosys Phase3")) ||
                        (personalDetails.getSource().contains("Infosys Phase3") && personalDetails.getDestination().contains("Bhumkar"))) {
                    int ticket = 20;
                    int amount = (ticket * 2 * 30);
                    int charge = (amount * 40) / 100;
                    preparedStatement.setInt(9, charge);

                } else {
                    if (personalDetails.getSource().isEmpty()) {
                        return "enter source";
                    } else if (personalDetails.getDestination().isEmpty()) {
                        return "enter destination";
                    } else if (personalDetails.getProfession().isEmpty()) {
                        return "enter profession";
                    } else {
                        int charge = 2000;
                        preparedStatement.setInt(9, charge);
                    }
                }

                //check isEmpty()method of personalDetails
                if (personalDetails.getFirstname().isEmpty()) {
                    return "enter first name";
                } else if (personalDetails.getLastname().isEmpty()) {
                    return "enter last name";
                } else if (personalDetails.getUserIdentity().isEmpty()) {
                    return "enter Aadhaar number";
                }
                preparedStatement.setDate(10, Date.valueOf(date));
                preparedStatement.setDate(11, obj);
                Pattern p = Pattern.compile(aadhaarRegex);
                Matcher m = p.matcher(personalDetails.getUserIdentity());
                boolean b = m.matches();

                if (b) {
                    log.info("Aadhaar is verified");
                } else {
                    return "Enter valid Aadhaar Number as  per Aadhaar Card";
                }

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    log.info("user Data saved Successfully");
                    return "user details submit successfully";
                }

            } else {
                log.info(personalDetails.getUserID() + " not exist");
                return "user id not exist";
            }

        } catch (Exception e) {
            log.error("Exception Occurred : " + e);
        }
        return null;
    }


    //DELETE
    public static boolean delete(@PathVariable int personalID) {
        boolean flag = false;
        try {
            Connection connection = Connectivity.CreateConnection();
            String query = "Delete from PersonalDetails where personalID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, personalID);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("user Detail Deleted Successfully");
                flag = true;
            } else {
                log.info("No record found with ID " + personalID);
            }

        } catch (Exception e) {
            log.error("Exception occurred : " + e);
        }
        return flag;

    }


    public static String updatePersonalDetails(@PathVariable int userID, PersonalDetails personalDetails) {
        String query = "update personalDetails set firstname = ?, lastname =? where userID =" + userID;
        try {
            Connection connection = Connectivity.CreateConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, personalDetails.getFirstname());
            preparedStatement.setString(2, personalDetails.getLastname());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.info("RowsAffected ", rowsAffected);
                log.info("record updated successfully");
            } else {
                log.info("no record updated");
            }

        } catch (Exception e) {
            log.error("Error Occurred : " + e);
        }
        return null;
    }


    //Select Personal Details Records
    public static JSONObject retrieveAllDataFromPersonalDetails() throws SQLException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        Connection connection = null;
        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from PersonalDetails";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("personalID ", rs.getInt("personalID"));
                obj.put("userID", rs.getInt("userID"));
                obj.put("firstname", rs.getString("firstname"));
                obj.put("lastname", rs.getString("lastname"));
                obj.put("userIdentity", rs.getString("userIdentity"));
                obj.put("age", rs.getInt("age"));
                obj.put("profession", rs.getString("profession"));
                obj.put("source", rs.getString("source"));
                obj.put("destination", rs.getString("destination"));
                obj.put("fromDate", rs.getString("fromDate"));
                obj.put("toDate", rs.getString("toDate"));
                obj.put("charge", rs.getString("charge"));

                jsonArray.add(obj);
            }
            jsonObject.put("Personal Details", jsonArray);
            //  }
        } catch (Exception e) {
            log.error("exception occurred :" + e);
        } finally {
            connection.close();
        }
        return jsonObject;

    }

    //Find By ID From personalDetails

    public static JSONObject findByIDPersonalDetails(@PathVariable int userID) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Connection connection = null;
        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from PersonalDetails where userID = ?";
            System.out.println("processing Data from database");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("personalID ", rs.getInt("personalID"));
                obj.put("userID", rs.getInt("userID"));
                obj.put("firstName", rs.getString("firstname"));
                obj.put("lastName", rs.getString("lastname"));
                obj.put("userIdentity", rs.getString("userIdentity"));
                obj.put("age", rs.getInt("age"));
                obj.put("source", rs.getString("source"));
                obj.put("destination", rs.getString("destination"));
                obj.put("fromDate", rs.getString("fromDate"));
                obj.put("toDate", rs.getString("toDate"));
                obj.put("charge", rs.getString("charge"));

                jsonArray.add(obj);
            }
            jsonObject.put("findByID", jsonArray);

        } catch (Exception e) {
            log.error("Exception Occurred" + e);
        } finally {
            connection.close();
        }
        return jsonObject;
    }


    //*****************************************************  JOINS  **************************************************************************************************//


    //Inner join of UserLogin and PersonalDetails
    public static JSONObject getInnerJoin() throws SQLException {
        PersonalDetails personalDetails = new PersonalDetails();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Connectivity.CreateConnection();
            String query = "select p.*,u.username,u.email from personalDetails as p inner join userLogin as u on u.userID = p.userID";
            log.debug("we are processing Data");
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", rs.getInt("userid"));
                obj.put("username", rs.getString("username"));
                obj.put("email", rs.getString("email"));
                //obj.put("UniqueID : ", rs.getString("uniqueID"));
                obj.put("firstname", rs.getString("firstname"));
                obj.put("lastname", rs.getString("lastname"));
                obj.put("userIdentity", rs.getString("userIdentity"));
                obj.put("age", rs.getString("age"));
                obj.put("profession", rs.getString("profession"));
                obj.put("source", rs.getString("source"));
                obj.put("destination", rs.getString("destination"));
                obj.put("fromDate", rs.getString("fromDate"));
                obj.put("toDate", rs.getString("toDate"));
                obj.put("charge", rs.getString("charge"));


                jsonArray.add(obj);
            }
            jsonObject.put("inner join", jsonArray);


        } catch (Exception e) {
            log.error("Exception occurred :" + e);
        }
        System.out.println(jsonObject);
        return jsonObject;
    }


    //*****************  check record based on date  #LEFT JOIN  ***************
    public static JSONObject selectUsingDate(@PathVariable Date fromdate) throws SQLException {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Connection connection = null;

        try {
            connection = Connectivity.CreateConnection();
            String query = "select u.username,u.email,p.userID,p.charge,p.fromDate,p.firstname,p.lastname,p.userIdentity," +
                    "p.source,p.destination from personalDetails as p left join userLogin as u on u.userID= p.userID where fromDate =? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, fromdate);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("fromDate ", rs.getDate("fromDate"));
                obj.put("userID ", rs.getInt("userID"));
                obj.put("username", rs.getString("username"));
                obj.put("email", rs.getString("email"));
                ;
                obj.put("firstname", rs.getString("firstname"));
                obj.put("lastname", rs.getString("lastname"));
                obj.put("userIdentity", rs.getString("userIdentity"));
                obj.put("source", rs.getString("source"));
                obj.put("destination", rs.getString("destination"));
                obj.put("charge", rs.getString("charge"));


                jsonArray.add(obj);
            }
            jsonObject.put("leftJoin", jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return jsonObject;
    }


    //*******************  Display Record From Date to Date  **************
    // select u.username,u.email,p.userID,p.charge,p.fromDate,p.firstname,p.lastname,p.userIdentity,
    //p.source,p.destination from personalDetails as p left join userLogin as u on u.userID= p.userID where fromDate between '2023-01-01' and '2023-01-06'

    public static JSONObject selectFromDateToDate(@PathVariable Date fromdate, @PathVariable Date fromDate) throws SQLException {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Connection connection = null;

        try {
            connection = Connectivity.CreateConnection();
            String query = "select u.username,u.email,p.userID,p.charge,p.fromDate,p.firstname,p.lastname,p.userIdentity," +
                    "p.source,p.destination from personalDetails as p left join userLogin as u on u.userID= p.userID where fromDate between " + "'" + fromdate + "'" + " and " + "' " + fromDate + " '";
            System.out.println("in a method");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // preparedStatement.setDate(1,fromdate);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                log.info("No Record Found");
            } else {
                while (rs.next()) {
                    JSONObject obj = new JSONObject();
                    obj.put("fromDate ", rs.getDate("fromDate"));
                    obj.put("userID ", rs.getInt("userID"));
                    obj.put("username", rs.getString("username"));
                    obj.put("email", rs.getString("email"));
                    obj.put("firstname", rs.getString("firstname"));
                    obj.put("lastname", rs.getString("lastname"));
                    obj.put("userIdentity", rs.getString("userIdentity"));
                    obj.put("source", rs.getString("source"));
                    obj.put("destination", rs.getString("destination"));
                    obj.put("charge", rs.getString("charge"));

                    jsonArray.add(obj);
                }
                jsonObject.put("leftJoin", jsonArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return jsonObject;
    }


    //****************************************************  AUDIT AND SOURCE,DESTINATION   ***************************************************************************//


    //******************  insert source value  ****************
    public static JSONObject sourceInsert(@PathVariable String source) {
        object = new JSONObject();
        sourceList = new ArrayList<>();
        sourceList.add(source);
        object.put("SourceList", sourceList);
        return object;
    }

    //***************  Insert Destination Value  *************
    public static JSONObject sourceDelete(@PathVariable String source) {
        JSONObject object = new JSONObject();
        object = new JSONObject();
        if (sourceList.contains(source)) {
            sourceList.remove(source);
            log.info(source + " is permanently remove ");
        } else {
            log.info("no item were found against " + source);
        }
        object.put("SourceList", sourceList);
        return object;
    }


    //********************************  Total Rupees of the Day  **********************************

    //Eg. select sum(charge)from personalDetails where fromDate ='2023-01-01'
    public static JSONObject selectTotalOfDate(@PathVariable Date fromdate) throws SQLException {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Connection connection = null;
        int sum = 0;

        try {
            connection = Connectivity.CreateConnection();
            String query = "select sum(charge)from personalDetails where fromDate =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, fromdate);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Total = rs.getInt(1);
                sum = sum + Total;
                log.info(String.valueOf(sum));
            }
        } catch (Exception e) {
            log.warn("Exception Occurred : " + e);
        } finally {
            connection.close();
        }
        return jsonObject;
    }


    //*****************  Display total Amount of FromDate To ToDate *******************
    // Eg. select sum(charge)from personalDetails where fromDate between '2023-01-01'and '2023-01-07'
    public static JSONObject CalculateTotalFromDateToDate(@PathVariable Date fromdate, @PathVariable Date fromDate) throws SQLException {

        JSONObject jsonObject = new JSONObject();
        Connection connection = null;
        int sum = 0;
        try {
            connection = Connectivity.CreateConnection();
            String query = "select sum(charge)from personalDetails where fromDate between " + "'" + fromdate + "'" + " and " + "' " + fromDate + " '";
            System.out.println("in a method");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Total = rs.getInt(1);
                sum = Total + sum;
                log.info("Total " + fromdate + " to " + fromDate + " is : " + sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return jsonObject;
    }


    //****************************************************   PaymentModule    ***************************************************************************//


    public static String insertPaymentModule(PaymentModule paymentModule) {
        LocalDate date = LocalDate.now();
        YearMonth ym = YearMonth.of(date.getYear(), date.getMonth());
//        itn a = Integer.parseInt(ym.toStrign());
        try {

            Connection connection = Connectivity.CreateConnection();
            System.out.println("Connection established successfully");
            String query = "insert into paymentDetails(userID,cardType,cardNo,cardExpiry,cvv,nameOnCard,transactionID)values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, paymentModule.getUserID());
            preparedStatement.setString(2, paymentModule.getCardType());
            preparedStatement.setString(3, paymentModule.getCardNo());
            preparedStatement.setString(4, paymentModule.getCardExpiry());
            preparedStatement.setInt(5, paymentModule.getCvv());
            preparedStatement.setString(6, paymentModule.getNameOnCard());
            preparedStatement.setString(7, String.valueOf(uniqueID));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return "payment successful";
            } else {
                return null;
            }
        } catch (Exception e) {
            log.info("exception occurred : " + e);
        }
        return "User Created Successfully";
    }


    //  ********************************  UPDATE INSET INTO PERSONAL DETAILS  ******************************

    public static String updateInsertPersonalDetails(@RequestBody int personalID, PersonalDetails personalDetails) {
        LocalDate date = LocalDate.now();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {

            //  if (personalDetails.getTodate().contains(date1)) {
            connection = Connectivity.CreateConnection();
            String Query = "update PersonalDetails  set source =?, destination=?, charge =? where personalID =?";
            preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setString(1, personalDetails.getSource());
            preparedStatement.setString(2, personalDetails.getDestination());
            if ((personalDetails.getSource().contains("Dange") && personalDetails.getDestination().contains("Baner")) ||
                    (personalDetails.getSource().contains("Baner") && personalDetails.getDestination().contains("Dange"))) {
                int ticket = 50;
                int amount = (ticket * 2 * 30);
                int charge = (amount * 35) / 100;
                preparedStatement.setInt(3, charge);

            }
            preparedStatement.setInt(4, personalDetails.getPersonID());


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return "pass renewed successfully";
            } else {
                return "server not responding please try after some time";
            }


        } catch (Exception e) {
            log.info("exception occurred : " + e);
        }
        return null;
    }


    // ****************************  get pass Details using inner join and left join  ****************************//
    public static JSONObject passGeneration(@PathVariable int personID) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            connection = Connectivity.CreateConnection();
            log.info("connection established successfully");
            String query = "select p.firstname,p.lastname,p.personalID,u.mobileNo,p.source,p.destination,p.fromdate,p.todate,p.charge from personalDetails as p inner join userLogin as u on u.userid = p.userid left join paymentDetails as pd on p.personalID = pd.personalID where p.personalId =?";
            preparedStatement = connection.prepareStatement(query);
            log.info("hear we are processing out your pass");
            preparedStatement.setInt(1, personID);
            //preparedStatement.setInt(2,);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("personalID",rs.getInt("personalID"));
                obj.put("firstname", rs.getString("firstname"));
                obj.put("LastName", rs.getString("lastname"));
                obj.put("Source", rs.getString("source"));
                obj.put("Destination", rs.getString("destination"));
                obj.put("FromDate", rs.getString("fromdate"));
                obj.put("ToDate",rs.getString("todate"));
                obj.put("Charge Paid",rs.getInt("charge"));
                obj.put("Mobile Number",rs.getString("mobileNo"));
                jsonArray.add(obj);

            }
                jsonObject.put("pass", jsonArray);
                log.info("pass Renewed Successfully");
                if(!jsonObject.isEmpty()){
                    return jsonObject;
                }else{
                 log.info("No data found against "+personID);
                }
        } catch (Exception e) {
            log.info("Exception occurred " + e);
        }
        return null;
    }
}
