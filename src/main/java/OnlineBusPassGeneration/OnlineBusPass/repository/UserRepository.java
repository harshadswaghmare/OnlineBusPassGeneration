package OnlineBusPassGeneration.OnlineBusPass.repository;

import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRepository {

    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;

   public static  Logger log = LoggerFactory.getLogger(UserRepository.class);

    private static final String mobileRegex = ("(0/91)?[7-9][0-9]{9}");
    private static final String aadhaarRegex = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";
    private static final String password = "^(?=.*[0-9])"  //at least one digit should be
                                           + "(?=.*[a-z])(?=.*[A-Z])"      // at least one lowercase and uppercase should be there
                                           + "(?=.*[@#$%^&+=])"            //at least one character should be
                                           + "(?=\\S+$).{8,20}$";         // Minimum and maximum Length
    private static final String email = "^[a-z0-9._]+@(.+)$";





    //Select All data from userAPI

    public static ResponseEntity<String> retrieveAllData() throws SQLException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from UserLogin order by userID asc";
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
                obj.put("mobileNo", rs.getString("mobileNo"));
                obj.put("firstname", rs.getString("firstname"));
                obj.put("lastname", rs.getString("lastname"));
                obj.put("userIdentity", rs.getString("userIdentity"));
                obj.put("age", rs.getInt("age"));
                //obj.put("profession", rs.getString("profession"));
                jsonArray.add(obj);
                jsonObject.put("User_Data", jsonArray);
            }

            if(jsonObject.isEmpty())
            {
                return new ResponseEntity("no user register with "+email ,HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(String.valueOf(jsonObject), HttpStatus.OK);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            connection.close();
        }
        System.out.println(jsonObject);
        return null;
    }

  //************Insert of user api ***********//
//    public static ResponseEntity <String> insert(UserLogin userLogin) {
//        PreparedStatement preparedStatement = null;
//
//        try {
//
//            Connection connection = Connectivity.CreateConnection();
//            log.info("Connection established successfully");
//            String prequery = "select * from userLogin where username = ?";
//            preparedStatement = connection.prepareStatement(prequery);
//            preparedStatement.setString(1, userLogin.getUsername());
//            //preparedStatement.setString(2,userLogin.getEmail());
//            ResultSet rs = preparedStatement.executeQuery();
//            if (!rs.next()) {
//                String query = "insert into UserLogin(userName,email,password,mobileNo,firstname,lastname,useridentity,age)" +
//                        "values(?,?,md5(?),?,?,?,?,?)";
//                preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, userLogin.getUsername());
//                preparedStatement.setString(2, userLogin.getEmail());
//                preparedStatement.setString(3, userLogin.getPassword());
//                preparedStatement.setString(4, userLogin.getMobileNo());
//
//                preparedStatement.setString(5, userLogin.getFirstname());
//                preparedStatement.setString(6, userLogin.getLastname());
//                preparedStatement.setString(7, userLogin.getUserIdentity());
//                preparedStatement.setInt(8, userLogin.getAge());
//                //preparedStatement.setString(9, userLogin.getProfession());
//
//
//                if (Pattern.matches(email, userLogin.getEmail())) {  //Email Validation
//                    log.info("Email verified");
//                } else {
//                    return new ResponseEntity<>("enter valid emailID", HttpStatus.EXPECTATION_FAILED);
//                }
//
//                if (Pattern.matches(password, userLogin.getPassword())) {  //password validation
//                    log.info(null);
//                } else {
//                    return new ResponseEntity<>("password should have at least one uppercase letter,one lowercase letter one character" +
//                            "and length should be between 8 - 20", HttpStatus.EXPECTATION_FAILED);
//                }
//
//                if (Pattern.matches(aadhaarRegex, userLogin.getUserIdentity())) {
//                    log.info("Aadhaar is verified");
//                } else {
//                    return new ResponseEntity("Enter valid Aadhaar number", HttpStatus.EXPECTATION_FAILED);
//                }
//
//                Pattern mobile = Pattern.compile(mobileRegex);    //mobileNo Validation
//                Matcher m1 = mobile.matcher(userLogin.getMobileNo());
//                boolean b1 = m1.matches();
//                if (b1) {
//                    log.info("mobile no is verified");
//                } else {
//                    return new ResponseEntity<>("Enter valid mobile no", HttpStatus.EXPECTATION_FAILED);
//                }
//
//                if (userLogin.getUsername().isEmpty()) {
//                    log.info("enter user name");
//                    return ResponseEntity.ok("enter username");
//                }
//                if (userLogin.getPassword().isEmpty()) {
//                    log.info("enter password");
//                    return ResponseEntity.ok("return password");
//                }
//                if (userLogin.getEmail().isEmpty()) {
//                    log.info("enter email");
//                    return ResponseEntity.ok("return email");}
//                    if (userLogin.getMobileNo().isEmpty()) {
//                        log.info("Enter Mobile number");
//                        return ResponseEntity.ok("Enter Mobile Number");
//                    }
//
//                    if (userLogin.getFirstname().isEmpty()) {
//                        log.info("Enter First name");
//                        return new ResponseEntity<>("enter first name",HttpStatus.EXPECTATION_FAILED);
//                    }
//                    if (userLogin.getLastname().isEmpty()) {
//                        log.info("Enter Last Name");
//                        return ResponseEntity.ok("enter lastname");
//                    }
//                    if (userLogin.getUserIdentity().isEmpty()) {
//                        log.info("Enter Aadhaar Number");
//                        return ResponseEntity.ok("Enter Aadhaar Number");
//                    }
//                    if (String.valueOf(userLogin.getAge()).isEmpty()) {
//                        log.info("Enter Age ");
//                        return new ResponseEntity<>("Enter your age",HttpStatus.EXPECTATION_FAILED);
//                    }
//                    if (userLogin.getProfession().isEmpty()) {
//                        log.info("Enter your profession ");
//                        return new ResponseEntity<>("Enter your profession",HttpStatus.EXPECTATION_FAILED);
//                    }
//
//                    int rowAffected = preparedStatement.executeUpdate();
//                    if (rowAffected > 0) {
//                        String sqlQuery = "select userID from userLogin where username =?";
//                        preparedStatement = connection.prepareStatement(sqlQuery);
//                        preparedStatement.setString(1, userLogin.getUsername());
//                        rs = preparedStatement.executeQuery();
//                        if (rs.next()) {
//                            int id = rs.getInt(1);
//                            return new ResponseEntity<>("User created UserID " + id, HttpStatus.CREATED);
//                        }
//                    } else {
//                        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                }
//
//
//        } catch (Exception e) {
//            log.info("exception occurred : " + e);
//        }
//        log.info(userLogin.getUsername() + " already exist");
//         return new ResponseEntity(userLogin.getUsername() + " already exist",HttpStatusCode.valueOf(409));
//    }


    //*******************  update users api ***********
    public static ResponseEntity<String> updateUserLogin(@PathVariable int userID, UserLogin userLogin) {

        try {
            connection = Connectivity.CreateConnection();
            String Query = "select userid from userlogin where userID= "+userID;
            preparedStatement = connection.prepareStatement(Query);
            //preparedStatement.setInt(1,userLogin.getUserid());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                String query = "update userLogin set email = ?,password =?, mobileNo=? where userID =" + userID;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userLogin.getEmail());
                preparedStatement.setString(2, userLogin.getPassword());
                preparedStatement.setString(3, userLogin.getMobileNo());
                int rowsAffected = preparedStatement.executeUpdate();
                log.info(String.valueOf(rowsAffected));
                if (rowsAffected > 0) {
                    return ResponseEntity.ok("user profile Updated successfully");
                } else {
                    return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else{
                return ResponseEntity.ok("UserID "+userID+" not found");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


   //*************** **************   Delete user  **************** ********************

    public static ResponseEntity<String> deleteFromUserLogin(@PathVariable int UserID) {
        boolean flag = false;
        try {
            connection = Connectivity.CreateConnection();
            String Query = "Select * from userLogin where userID = ?";
            preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1,UserID);
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()) {
                String query = "Delete from userLogin where userID = ? ";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, UserID);
                int a = preparedStatement.executeUpdate();
                if (a > 0) {
                    return new ResponseEntity<>("user Deleted Successfully",HttpStatus.OK);

                } else {
                    return ResponseEntity.ok("user not found");
                }
            }
           // return ResponseEntity.ok("User Not found against UserID "+UserID);


        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }


    //***************** ****** Find user by id  ********************

    public static ResponseEntity<String> findByID(@PathVariable int userID) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        //List<String>list = new ArrayList<>();


        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from UserLogin where userID= ? ";
            log.info("processing Data from database");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", rs.getInt("userid"));
                obj.put("username", rs.getString("username"));
                obj.put("email", rs.getString("email"));
                obj.put("password", rs.getString("password"));
                obj.put("mobileNo", rs.getString("mobileNo"));
                obj.put("firstname", rs.getString("firstname"));
                obj.put("lastname", rs.getString("lastname"));
                obj.put("userIdentity", rs.getString("userIdentity"));
                obj.put("age", rs.getString("age"));

                jsonObject.put("pass", obj);

                //list.add(String.valueOf(obj));
            }
            if(jsonObject.isEmpty())
            {
                return new ResponseEntity("no user register with "+email ,HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(String.valueOf(jsonObject), HttpStatus.OK);
            }



        } catch (Exception e) {
            log.info("Exception Occurred " + e.getMessage());
        } finally {
            connection.close();
        }
        return null;
        //return jsonObject;
        // return ResponseEntity.ok(jsonObject);
    }



    //Inner join of UserLogin and PersonalDetails
    public static ResponseEntity<String> findUserPassByEmail(@PathVariable String email) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            connection = Connectivity.CreateConnection();
            String query = "select u.*,p.passID,p.source,p.destination,p.fromDate, p.toDate,p.charge,p.status from userLogin as u inner join personalDetails as p on p.userID = u.userID where email like " + " '%"+email+ "%'" + " and status = "+"'Active'"+"";
            log.debug("we are processing Data");
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
           while(rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("userid", rs.getInt("userid"));
                //obj.put("username", rs.getString("username"));
                obj.put("email", rs.getString("email"));
                obj.put("firstname", rs.getString("firstname"));
                obj.put("lastname", rs.getString("lastname"));
                obj.put("userIdentity", rs.getString("userIdentity"));
                obj.put("age", rs.getString("age"));
                obj.put("source", rs.getString("source"));
                obj.put("destination", rs.getString("destination"));
                obj.put("fromDate", rs.getString("fromDate"));
                obj.put("toDate", rs.getString("toDate"));
                obj.put("charge", rs.getString("charge"));
                jsonArray.add(obj);
                jsonObject.put("pass",jsonArray);

            }
          if(jsonObject.isEmpty())
          {
              return new ResponseEntity("no user register with "+email ,HttpStatus.NOT_FOUND);
          }
          else {
              return new ResponseEntity<>(String.valueOf(jsonObject), HttpStatus.OK);
          }
        } catch (Exception e) {
            log.error("Exception occurred :" + e);
        }
       return null;
    }




//    public static ResponseEntity<String> findUserPassByUserID(@PathVariable int userID) throws SQLException {
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        try {
//            connection = Connectivity.CreateConnection();
//            String query = "select u.*,p.passID,p.source,p.destination,p.fromDate, p.toDate,p.charge,p.status from userLogin as u inner join personalDetails as p on p.userID = u.userID where userID = ? and status = "+"'Active'"+"";
//            log.debug("we are processing Data");
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1,userID);
//            ResultSet rs = preparedStatement.executeQuery();
//            while(rs.next()) {
//                JSONObject obj = new JSONObject();
//                obj.put("userid", rs.getInt("userid"));
//                obj.put("username", rs.getString("username"));
//                obj.put("email", rs.getString("email"));
//                obj.put("firstname", rs.getString("firstname"));
//                obj.put("lastname", rs.getString("lastname"));
//                obj.put("userIdentity", rs.getString("userIdentity"));
//                obj.put("age", rs.getString("age"));
//                obj.put("source", rs.getString("source"));
//                obj.put("destination", rs.getString("destination"));
//                obj.put("fromDate", rs.getString("fromDate"));
//                obj.put("toDate", rs.getString("toDate"));
//                obj.put("charge", rs.getString("charge"));
//                jsonArray.add(obj);
//                jsonObject.put("pass",jsonArray);
//            }
//            if(jsonObject.isEmpty())
//            {
//                return new ResponseEntity("no user register with "+userID ,HttpStatus.NOT_FOUND);
//            }
//            else {
//                return new ResponseEntity<>(String.valueOf(jsonObject), HttpStatus.OK);
//            }
//
//        } catch (Exception e) {
//            log.error("Exception occurred :" + e);
//        }
//        return null;
//    }



    public static ResponseEntity <String> insert(UserLogin userLogin) {
        PreparedStatement preparedStatement = null;
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();

        try {

            Connection connection = Connectivity.CreateConnection();
            log.info("Connection established successfully");
            String prequery = "select * from userLogin where username = ?";
            preparedStatement = connection.prepareStatement(prequery);
            preparedStatement.setString(1, userLogin.getUsername());
            //preparedStatement.setString(2,userLogin.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                String query = "insert into UserLogin(userName,email,password,mobileNo,firstname,lastname,useridentity,age)" +
                        "values(?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userLogin.getUsername());
                preparedStatement.setString(2, userLogin.getEmail());
                preparedStatement.setString(3, encoder.encodeToString(userLogin.getPassword().getBytes()));
                preparedStatement.setString(4, userLogin.getMobileNo());

                preparedStatement.setString(5, userLogin.getFirstname());
                preparedStatement.setString(6, userLogin.getLastname());
                preparedStatement.setString(7, userLogin.getUserIdentity());
                preparedStatement.setInt(8, userLogin.getAge());
                //preparedStatement.setString(9, userLogin.getProfession());
                 byte [] bytes = decoder.decode(encoder.encodeToString(userLogin.getPassword().getBytes()));
                 String result = String.valueOf(bytes);
                 System.out.println(result);
                 if(userLogin.getPassword().contains(result)  || (userLogin.getPassword()==result)){
                    System.out.println("Password matches");
                }else {
                    System.out.println("password failed");
                }

                if (Pattern.matches(email, userLogin.getEmail())) {  //Email Validation
                    log.info("Email verified");
                } else {
                    return new ResponseEntity<>("enter valid emailID", HttpStatus.EXPECTATION_FAILED);
                }

                if (Pattern.matches(password, userLogin.getPassword())) {  //password validation
                    log.info(null);
                } else {
                    return new ResponseEntity<>("password should have at least one uppercase letter,one lowercase letter one character" +
                            "and length should be between 8 - 20", HttpStatus.EXPECTATION_FAILED);
                }

                if (Pattern.matches(aadhaarRegex, userLogin.getUserIdentity())) {
                    log.info("Aadhaar is verified");
                } else {
                    return new ResponseEntity("Enter valid Aadhaar number", HttpStatus.EXPECTATION_FAILED);
                }

                Pattern mobile = Pattern.compile(mobileRegex);    //mobileNo Validation
                Matcher m1 = mobile.matcher(userLogin.getMobileNo());
                boolean b1 = m1.matches();
                if (b1) {
                    log.info("mobile no is verified");
                } else {
                    return new ResponseEntity<>("Enter valid mobile no", HttpStatus.EXPECTATION_FAILED);
                }

                if (userLogin.getUsername().isEmpty()) {
                    log.info("enter user name");
                    return ResponseEntity.ok("enter username");
                }
                if (userLogin.getPassword().isEmpty()) {
                    log.info("enter password");
                    return ResponseEntity.ok("return password");
                }
                if (userLogin.getEmail().isEmpty()) {
                    log.info("enter email");
                    return ResponseEntity.ok("return email");}
                if (userLogin.getMobileNo().isEmpty()) {
                    log.info("Enter Mobile number");
                    return ResponseEntity.ok("Enter Mobile Number");
                }

                if (userLogin.getFirstname().isEmpty()) {
                    log.info("Enter First name");
                    return new ResponseEntity<>("enter first name",HttpStatus.EXPECTATION_FAILED);
                }
                if (userLogin.getLastname().isEmpty()) {
                    log.info("Enter Last Name");
                    //return ResponseEntity.ok("enter lastname");
                    return new ResponseEntity<>("enter lastname",HttpStatus.EXPECTATION_FAILED);
                }
                if (userLogin.getUserIdentity().isEmpty()) {
                    log.info("Enter Aadhaar Number");
                    //return ResponseEntity.ok("Enter Aadhaar Number");
                    return new ResponseEntity<>("Enter Aadhaar Number",HttpStatus.EXPECTATION_FAILED);
                }
                if (String.valueOf(userLogin.getAge()).isEmpty()) {
                    log.info("Enter Age ");
                    return new ResponseEntity<>("Enter your age",HttpStatus.EXPECTATION_FAILED);
                }
                if (userLogin.getProfession().isEmpty()) {
                    log.info("Enter your profession ");
                    return new ResponseEntity<>("Enter your profession",HttpStatus.EXPECTATION_FAILED);
                }

                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0) {
                    String sqlQuery = "select userID from userLogin where username =?";
                    preparedStatement = connection.prepareStatement(sqlQuery);
                    preparedStatement.setString(1, userLogin.getUsername());
                    rs = preparedStatement.executeQuery();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        return new ResponseEntity<>("User created UserID " + id, HttpStatus.CREATED);
                    }
                } else {
                    return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }


        } catch (Exception e) {
            log.info("Exception Occurred : "+e);
            return new ResponseEntity<>("exception occurred "+e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info(userLogin.getUsername() + " already exist");
        return new ResponseEntity(userLogin.getUsername() + " already exist",HttpStatus.CONFLICT);
    }

}
