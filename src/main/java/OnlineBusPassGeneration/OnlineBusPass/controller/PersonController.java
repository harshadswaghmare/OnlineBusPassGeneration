package OnlineBusPassGeneration.OnlineBusPass.controller;

import OnlineBusPassGeneration.OnlineBusPass.model.PersonalDetails;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.TravellerRepository;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    //***************  select  PersonalData  ***************
    @GetMapping("selectAllFromPersonalDetails")
    public static JSONObject GetAllUserData()throws SQLException {
        return TravellerRepository.retrieveAllDataFromPersonalDetails();
    }


    //***************  Insert Data Into PersonalDetails  ***************
    @PostMapping("insertDataIntoPersonal")
    public String insertData( @RequestBody PersonalDetails personalDetails) throws SQLException {
        return TravellerRepository.insertPersonal(personalDetails);
    }


    //***************  Delete From PersonalDetails  ***************
    @DeleteMapping("deleteFromPersonalDetails/{PersonalID}")
    public boolean delete(@PathVariable int PersonalID) {
        // boolean result = false;
        boolean result = TravellerRepository.delete(PersonalID);
        return result;
    }


    //***************  Insert into source List  ***************
    @PostMapping("/insertIntoSource/{source}")
    public JSONObject result(@PathVariable String source){
       return TravellerRepository.sourceInsert(source);
    }


    //***************  Insert into source List  ***************
    @DeleteMapping("/deleteFromSource/{source}")
    public JSONObject delete(@PathVariable String source){
        return TravellerRepository.sourceDelete(source);

    }



    //************* getList of source  ******************


    //***************  Update from PersonalDetails  ***************
    @PutMapping("updatePersonalDetails/{UserID}")
    public String update(@PathVariable int UserID,@RequestBody PersonalDetails personalDetails) throws SQLException {
        String result = TravellerRepository.updatePersonalDetails(UserID,personalDetails);
        return result;
    }


    //***************  Search User Data  ***************
    @GetMapping("/findByUserLogin/{userID}")
    public static HashMap<String,Object>findByIDUserLogin(@PathVariable("userID") int userID, @RequestBody UserLogin userLogin) throws SQLException {
      return (HashMap<String, Object>) TravellerRepository.findByID(userID);
    }


    //***************  Search PersonalDetails Data  ***************
    @GetMapping("/findByPersonalDetails/{userID}")
    public static HashMap<String,Object>findByIDPersonalDetails(@PathVariable("userID") int userID, @RequestBody PersonalDetails personalDetails) throws SQLException {
        return (HashMap<String, Object>) TravellerRepository.findByIDPersonalDetails(userID);
    }

    // ***************  Update User Login  ***************
    @PutMapping("updateInsert/{personalID}")
    public String updateInsert(@PathVariable int personalID,@RequestBody PersonalDetails personalDetails) throws SQLException {
        String result = TravellerRepository.updateInsertPersonalDetails(personalID,personalDetails);
        return result;
    }


}
