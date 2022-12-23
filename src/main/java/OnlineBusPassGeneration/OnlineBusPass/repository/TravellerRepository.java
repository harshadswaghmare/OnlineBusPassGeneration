package OnlineBusPassGeneration.OnlineBusPass.repository;

import OnlineBusPassGeneration.OnlineBusPass.OnlineBusPassApplication;
import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravellerRepository {

    static String uniqueID = UUID.randomUUID().toString();

   // static Logger log = (Logger) LoggerFactory.getLogger(OnlineBusPassApplication.class);
   // private static final Logger log = (Logger) LoggerFactory.getLogger(OnlineBusPassApplication.class);
    /*public static Map<String,Object>retrieveAllData()throws SQLException{
        HashMap<String,Object>map = new HashMap<>();
        List<Object>list = new ArrayList<>();
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

        Pattern pattern = Pattern.compile("^$|^[\\w,]+$");

        UserLogin obj = new UserLogin();


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




    public static String  updateUserLogin(UserLogin userLogin) {


        try {
            Connection connection = Connectivity.CreateConnection();
            String query = "update UserLogin set email = ?, password = ? where userID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
           // preparedStatement.setInt(1, userLogin.getUserid());
            preparedStatement.setString(2, userLogin.getUsername());
            preparedStatement.setString(3, userLogin.getEmail());
            preparedStatement.setString(4, userLogin.getPassword());

            preparedStatement.executeUpdate();



         } catch (Exception e) {
            e.printStackTrace();
        }
         return "record updates successfully";
    }


    //find by ID Command//

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
                obj.put("username: " , rs.getInt("username"));
                obj.put("email  : " ,rs.getInt("email"));
                obj.put("password : ", rs.getInt("password"));

                jsonArray.add(obj);
            }
            jsonObject.put("findByID",jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();

        }
        return null;
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

}
