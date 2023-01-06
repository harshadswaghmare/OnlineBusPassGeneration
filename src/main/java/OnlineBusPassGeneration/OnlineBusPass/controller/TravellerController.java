package OnlineBusPassGeneration.OnlineBusPass.controller;

import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.TravellerRepository;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.sql.SQLException;

@RestController
public class TravellerController {


    //***************  Insert into userLogin  ******************
    @PostMapping("insertIntoUserLogin")
    public String insertData(@RequestBody UserLogin userLogin) {
        return TravellerRepository.insert(userLogin);
    }


    //***********  Delete from user Login  *****************
    @DeleteMapping("deleteFromUserLogin/{UserID}")
    public boolean deleteUser(@PathVariable int UserID) {
        // boolean result = false;
        boolean result = TravellerRepository.deleteFromUserLogin(UserID);
        return result;
    }


    // *************** Select User Records  ***************
    @GetMapping("selectAllFromUserLogin")
    public static JSONObject getUserData() throws SQLException {
        return TravellerRepository.retrieveAllData();
    }



    // ***************  Update User Login  ***************
    @PutMapping("updateFromUserLogin/{UserID}")
    public String update(@PathVariable int UserID,@RequestBody UserLogin userLogin) throws SQLException {
     String result = TravellerRepository.updateUserLogin(UserID,userLogin);
     return result;
    }


    // ***************  Find PersonalDetails by UserID  ***************
    @GetMapping("selectFromUserLoginByID/{userID}")
    public static JSONObject findUser(@PathVariable  int userID)throws  SQLException{
       JSONObject jsonObject = new JSONObject();
       jsonObject = TravellerRepository.findByID(userID);
       return jsonObject;
    }


    // ***************  join ov userLogin and personalDetails ***************
    @GetMapping("innerJoin")
    public static JSONObject dataFromInnerJoin() throws SQLException {
        return TravellerRepository.getInnerJoin();
    }


    @GetMapping("selectFirstName/{fromdate}")
    public static JSONObject findFirstName(@PathVariable Date fromdate)throws  SQLException{
        JSONObject jsonObject;
        jsonObject = TravellerRepository.selectUsingDate(fromdate);
        return jsonObject;
    }

//********* calculate total amount of the day  ***********
    @GetMapping("total/{fromdate}")
    public static JSONObject TotalOfTheDay(@PathVariable Date fromdate)throws  SQLException{
        JSONObject a = new JSONObject();
        a = TravellerRepository.selectTotalOfDate(fromdate);
        System.out.println(a);
        return a;
    }



    //****************  Display total Record  fromDate to toDate
    @GetMapping("selectFromDateToDate/{fromdate}/{fromDate}")
    public static JSONObject fromDateBetween(@PathVariable Date fromdate,@PathVariable Date fromDate)throws  SQLException{
        System.out.println("In a between method ");
        JSONObject a = new JSONObject();
        a = TravellerRepository.selectFromDateToDate(fromdate,fromDate);
        System.out.println(a);
        return a;
    }

    //************ Calculate total amount value fromDate to ToDate

    @GetMapping("calculateTotalAmountFromDateToDate/{fromdate}/{fromDate}")
    public static JSONObject calculateTotalAmountFromDateToDate(@PathVariable Date fromdate,@PathVariable Date fromDate)throws  SQLException{
        System.out.println("In a between method ");
        JSONObject a = new JSONObject();
        a = TravellerRepository.CalculateTotalFromDateToDate(fromdate,fromDate);
        System.out.println(a);
        return a;
    }


}


