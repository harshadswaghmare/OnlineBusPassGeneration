package OnlineBusPassGeneration.OnlineBusPass.controller;

import OnlineBusPassGeneration.OnlineBusPass.model.PersonalDetails;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.PersonalDetailsRepository;
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

    @GetMapping("retrieveAllFromPersonal")
    public static JSONObject GetAllUserData()throws SQLException {
        return PersonalDetailsRepository.retrieveAllDataFromPersonalDetails();
    }

    @PostMapping("insertData")
    public String insertData(@RequestBody PersonalDetails personalDetails) throws SQLException {
        return PersonalDetailsRepository.insert(personalDetails);
    }


    @DeleteMapping("DeletePersonal/{PersonalID}")
    public boolean delete(@PathVariable int PersonalID) {
        // boolean result = false;
        boolean result = PersonalDetailsRepository.delete(PersonalID);
        return result;
    }

    @PostMapping("/source/{source}")

    public String result(@PathVariable String source){
       List<String>list = Collections.singletonList(PersonalDetailsRepository.source(source));
        return list.toString();
    }


    @PutMapping("/userLogin/{userID}")
    public static HashMap<String,Object>updateStudent(@PathVariable("userID") int userID, @RequestBody UserLogin userLogin) throws SQLException {
      return (HashMap<String, Object>) TravellerRepository.findByID(userID);
    }


}
