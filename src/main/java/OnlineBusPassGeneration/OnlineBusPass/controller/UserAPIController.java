package OnlineBusPassGeneration.OnlineBusPass.controller;

import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("api/users")
public class UserAPIController {


    //***************  Insert into userLogin  ******************
    @PostMapping
    public static ResponseEntity<String> insertData(@RequestBody UserLogin userLogin) {
        return UserRepository.insert(userLogin);
    }


    //***********  Delete from user Login  *****************
    @DeleteMapping("/{UserID}")
    public static ResponseEntity<String> deleteUser(@PathVariable int UserID) {
        // boolean result = false;

        String result = String.valueOf(UserRepository.deleteFromUserLogin(UserID));
       return ResponseEntity.ok(result);
    }

    // *************** Select User Records  ***************
    @GetMapping
    public static ResponseEntity<String> getUserData() throws SQLException {
        return UserRepository.retrieveAllData();
    }




    // ***************  Update User Login  ***************
    @PutMapping("/{UserID}")
    public static ResponseEntity<String> update(@PathVariable int UserID, @RequestBody UserLogin userLogin) throws SQLException {
     return UserRepository.updateUserLogin(UserID,userLogin);
    }


    // ***************  Find user details by UserID  ***************
    @GetMapping("/{userID}")
    public static ResponseEntity<String> findByUserID(@PathVariable  int userID)throws  SQLException{
        return UserRepository.findByID(userID);
    }


    // ******************** Find pass of the user using email and status = active  *******************//

    @GetMapping("/get/{email}")
    public static ResponseEntity<String>ByEmail(@PathVariable String email)throws  SQLException{
       return UserRepository.findUserPassByEmail(email);
    }


//    @GetMapping("/get/{userID}")
//    public static ResponseEntity<String> findUserPassByUserId(@PathVariable int userID)throws  SQLException{
//        return UserRepository.findUserPassByUserID(userID);
//    }


//    // ***************  join ov userLogin and personalDetails using userid ***************
//    @GetMapping("innerJoin")
//    public static JSONObject dataFromInnerJoin() throws SQLException {
//        return TravellerRepository.getInnerJoin();
//    }


//    // *****************   Here we can check todays  record *************
//    @GetMapping("selectAllWhereDate/{fromdate}")
//    public static JSONObject findFirstName(@PathVariable Date fromdate)throws  SQLException{
//        JSONObject jsonObject;
//        jsonObject = TravellerRepository.selectUsingDate(fromdate);
//        return jsonObject;
//    }
//
////********* calculate total amount of the day  ***********
//    @GetMapping("SelectTotalWhereDate/{fromdate}")
//    public static int TotalOfTheDay(@PathVariable Date fromdate)throws  SQLException{
//        int a = TravellerRepository.selectTotalOfDate(fromdate);
//        System.out.println(a);
//        return a;
//    }
//
//
//
//    //****************  Display total Record  fromDate to toDate
//    @GetMapping("selectFromDateToDate/{fromdate}/{fromDate}")
//    public static JSONObject fromDateBetween(@PathVariable Date fromdate,@PathVariable Date fromDate)throws  SQLException{
//        System.out.println("In a between method ");
//        JSONObject a = new JSONObject();
//        a = TravellerRepository.selectFromDateToDate(fromdate,fromDate);
//        System.out.println(a);
//        return a;
//    }

//    //************ Calculate total amount value fromDate to ToDate
//
//    @GetMapping("calculateTotalAmountFromDateToDate/{fromdate}/{fromDate}")
//    public static int calculateTotalAmountFromDateToDate(@PathVariable Date fromdate,@PathVariable Date fromDate)throws  SQLException{
//        System.out.println("In a between method ");
//        int a = TravellerRepository.CalculateTotalFromDateToDate(fromdate,fromDate);
//        System.out.println(a);
//        return a;
//    }



}


