package OnlineBusPassGeneration.OnlineBusPass.controller;


import OnlineBusPassGeneration.OnlineBusPass.OnlineBusPassApplication;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.TravellerRepository;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
    public boolean delete(@PathVariable int UserID) {
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
}
