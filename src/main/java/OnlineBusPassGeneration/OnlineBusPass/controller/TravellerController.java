package OnlineBusPassGeneration.OnlineBusPass.controller;


import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.TravellerRepository;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.HashMap;

@RestController
public class TravellerController {


    /*@GetMapping("retrieveAll")
    public static HashMap<String, Object> GetAllUserData() throws SQLException {
        return (HashMap<String, Object>) TravellerRepository.retrieveAllData();
    }*/

    @PostMapping("insert")
    public String insertData(@RequestBody UserLogin userLogin) {
        return TravellerRepository.insert(userLogin);
    }


    @DeleteMapping("Delete/{UserID}")
    public boolean deleteUiser(@PathVariable int UserID) {
        // boolean result = false;
        boolean result = TravellerRepository.deleteFromUserLogin(UserID);
        return result;
    }

    @GetMapping("findByID/{userID}")
    public static HashMap<String, Object> oneUserData(@PathVariable int userID) throws SQLException {
        return (HashMap<String, Object>) TravellerRepository.findByID(userID);
    }

    @GetMapping("selectAllFromUser")
    public static JSONObject getUserData() throws SQLException {
        return TravellerRepository.retrieveAllData();
    }

    @GetMapping("getInnerJoin")
    public static JSONObject dataFromInnerJoin() throws SQLException {
        System.out.println("you are in a inner join");
        return TravellerRepository.getInnerJoin();
    }

//    @PutMapping("update/{userID}")
//    public static String updateDisplay(@PathVariable  int userID)throws  SQLException{
//        //JSONObject userLogin = new JSONObject();
////        //JSONObject userLogin = new JSONObject();
////        userLogin = TravellerRepository.findByID(userID);
////        System.out.println(userLogin);
//
//      return TravellerRepository.updateUserLogin(userID);
//
//        //return userLogin;
//    }

    @PutMapping("modify/{UserID}")
    public String update(@PathVariable int UserID,@RequestBody UserLogin userLogin) throws SQLException {
     String result = TravellerRepository.updateUserLogin(UserID,userLogin);
     return result;

    }
@GetMapping("findById/{userID}")
    public static JSONObject findUser(@PathVariable  int userID)throws  SQLException{
        JSONObject jsonObject = new JSONObject();
       jsonObject = TravellerRepository.findByID(userID);
       return jsonObject;
    }

}


