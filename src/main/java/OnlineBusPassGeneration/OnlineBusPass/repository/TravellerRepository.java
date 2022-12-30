package OnlineBusPassGeneration.OnlineBusPass.repository;

import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.model.PersonalDetails;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.util.*;

public class TravellerRepository {

    static String uniqueID = UUID.randomUUID().toString();

   // static Logger log = (Logger) LoggerFactory.getLogger(OnlineBusPassApplication.class);
   // private static final Logger log = (Logger) LoggerFactory.getLogger(OnlineBusPassApplication.class);
    /*public static Map<String,Object>retrieveAllData()throws SQLException{
        HashMap<String,Object>map = new HashMap<>();
        List<Object>list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        trycca
        {
            connection= Connectivity.CreateConnection();
            String query = "select * from UserLogin order by userid asc";
            System.out.println("processing Data");
            System.out.println(query);
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                System.out.println("userid : " + rs.getInt("userid"));
                list.add (0,"userid : " +rs.getInt("userid"));

                System.out.println("username : " + rs.getString("username"));
                list.add(1,"username : " +rs.getString("username"));

                System.out.println("password : " + rs.getString("password"));
                list.add(2,"password : " +rs.getString("password"));

                System.out.println("email : " + rs.getString("email"));
                list.add(3,"email : " +rs.getString("email"));


                System.out.println("_________________________________");
            }
            System.out.println(List.of(list));

        }catch(Exception e)
        {
            e.printStackTrace();
        }

        finally {
            connection.close();
        }
        System.out.println("thanks");
        return map;

    }*/



    public static String insert(UserLogin userLogin) {

        boolean flag = false;
       // Pattern pattern = Pattern.compile("^$|^[\\w,]+$");
        //  UserLogin obj = new UserLogin();
        try {

            Connection connection = Connectivity.CreateConnection();
            System.out.println("Connection established successfully");
            String query = "insert into UserLogin(userName,email,password,uniqueID)values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userLogin.getUsername());
            preparedStatement.setString(2, userLogin.getEmail());
            preparedStatement.setString(3, userLogin.getPassword());
            preparedStatement.setString(4,uniqueID);
            System.out.println("Thanks for working");

            //preparedStatement.setString(4, student.getAddress());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            preparedStatement=connection.prepareStatement("insert into paymentDetails(cardType)values(?)");
            preparedStatement.setString(1,userLogin.getCardType());
            preparedStatement.executeUpdate();

            System.out.println("Finally Inserted");
            flag = true;
        } catch (Exception e) {
            System.out.println("exception occurred : "+e);
        }
        return "record Inserted Successfully";
    }

    public static boolean deleteFromUserLogin(@PathVariable int UserID) {
        boolean flag = false;
        try {
            Connection connection = Connectivity.CreateConnection();
            String query = "Delete from userLogin where userID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, UserID);
            int a = preparedStatement.executeUpdate();
            if(a>0)
            {
                System.out.println("successfully deleted");
                flag = true;
            }
            else {
                System.out.println("No record found with ID "+UserID);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



    //for PersonalDetails
    public static String  updateUserLogin(@PathVariable int userID, UserLogin userLogin) {
        String query = "update userLogin set email = ?,username =?,password =? where userID ="+userID;
        try {
            Connection connection = Connectivity.CreateConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,userLogin.getEmail());
            preparedStatement.setString(2,userLogin.getUsername());
            preparedStatement.setString(3,userLogin.getPassword());

            int rowAffected = preparedStatement.executeUpdate();
            System.out.println(rowAffected);
//            ResultSet rs = preparedStatement.executeQuery();
//            while(rs.next())
//            {
//                System.out.print("ID: " + rs.getInt("id"));
//                System.out.print("username: " + rs.getInt("age"));
//                System.out.print("email: " + rs.getString("first"));
//                System.out.println("password: " + rs.getString("last"));
//            }
//          rs.close();
//
//            if (a > 0) {
//                System.out.println("Record Updated Successfully");
//            } else {
//                System.out.println("No Record Updated");
//            }

//            while(rs.next())
//            {
//                int id = rs.getInt("userID");
//                String username = rs.getString("username");
//                String email = rs.getString("email");
//                String password = rs.getString("password");

            //               System.out.print("UserID" + userID);
//                System.out.print(" username" + username);
//                System.out.print(" email" + email);
//                System.out.println(" password" +password);
//            }
            //rs.close();
           // return "record updated successfully";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Record Updated Successfully";
    }

    //find by ID Command//  #### ForPersonalRepository

    public static JSONObject findByID(@PathVariable int userID)throws SQLException {
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
                obj.put("userid : " , rs.getInt("userid"));
                obj.put("username: " , rs.getString("username"));
                obj.put("email  : " ,rs.getString("email"));
                obj.put("password : ", rs.getString("password"));

                jsonArray.add(obj);
            }
            jsonObject.put("findByID",jsonArray);

        } catch (Exception e) {
            System.out.println("Exception Occurred" +e);
        } finally {
            connection.close();

        }
        return jsonObject;
    }

    public static JSONObject retrieveAllData()throws SQLException{
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try
        {
            connection= Connectivity.CreateConnection();
            String query = "select * from UserLogin order by userid asc";
            System.out.println("processing Data");
            System.out.println(query);
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            JSONObject obj = new JSONObject();

            obj.put("userid : " ,rs.getInt("userid"));
            obj.put("username : " , rs.getString("username"));
            obj.put("email : " , rs.getString("email"));
            obj.put("password : " ,rs.getString("password"));
            obj.put("UniqueID : " ,rs.getString("uniqueID"));

            jsonArray.add(obj);

            }
            jsonObject.put("User_Data",jsonArray);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        finally {
            connection.close();
        }
        System.out.println(jsonObject);
        return jsonObject;

    }

    public static JSONObject getInnerJoin()throws SQLException {
        PersonalDetails personalDetails = new PersonalDetails();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Connectivity.CreateConnection();
            String query = "select * from userLogin as u inner join personalDetails as p on u.userID = p.userID";
            System.out.println("we are processing Data");
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
  System.out.println
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("userid : ", rs.getInt("userid"));
                obj.put("username : ", rs.getString("username"));
                obj.put("email : ", rs.getString("email"));
                obj.put("password : ", rs.getString("password"));
                obj.put("UniqueID : ", rs.getString("uniqueID"));
                obj.put("firstname",rs.getString("firstname"));
                obj.put("lastname: ", rs.getString("Lastname"));
                obj.put("userIdentity: ", rs.getString("userIdentity"));
                obj.put("age: ", rs.getString("age"));
                obj.put("source: ", rs.getString("source"));
                obj.put("destination: ", rs.getString("destination"));
                obj.put("fromDate: ", rs.getString("fromDate"));
                obj.put("toDate: ", rs.getString("toDate"));
                obj.put("charge: ", rs.getString("charge"));

                jsonArray.add(obj);
            }
            jsonObject.put("innerJoin",jsonArray);
        }catch(Exception e){
            System.out.println("Exception occurred :"+e);
        }
        System.out.println(jsonObject);
        return jsonObject;
    }

//    public List<Student> listStudents() {
//        String SQL = "select * from Student";
//        List <Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
//        return students;
//    }



}
