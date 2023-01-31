package OnlineBusPassGeneration.OnlineBusPass.controller;

import OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel;
import OnlineBusPassGeneration.OnlineBusPass.repository.PassAPIRepository;
import OnlineBusPassGeneration.OnlineBusPass.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;

@RestController
@RequestMapping("api/pass")
public class PassAPIController {

   // ***************  select  PersonalData  ***************
    @GetMapping
    public static ResponseEntity<JSONObject> GetAllUserData(PassAPIModel passAPIModel)throws SQLException {
        return PassAPIRepository.retrieveAllDataFromPersonalDetails(passAPIModel);
    }


    //***************  Insert Data Into PersonalDetails  ***************
    @PostMapping
    public String insertData( @RequestBody PassAPIModel passAPIModel) throws SQLException {
        return PassAPIRepository.insertPersonal(passAPIModel);
    }


    // ********************  Renewal Of pass ********************

    @PutMapping("/{PassID}")
    public static String update(@PathVariable int PassID,@RequestBody PassAPIModel passAPIModel) throws SQLException {
        String result = String.valueOf(PassAPIRepository.update(PassID,passAPIModel));
        return result;
    }


    //***************  Delete From PersonalDetails  ***************
    @DeleteMapping("/{passID}")
    public boolean delete(@PathVariable int passID) {
        // boolean result = false;
       boolean result = PassAPIRepository.deletePassAPI(passID);
       return result;
    }



//
//
//    //***************  Insert into source List  ***************
//    @PostMapping("/insertIntoSource/{source}")
//    public JSONObject result(@PathVariable String source){
//       return TravellerRepository.sourceInsert(source);
//    }
//
//
//    //***************  Insert into source List  ***************
//    @DeleteMapping("/deleteFromSource/{source}")
//    public JSONObject delete(@PathVariable String source){
//        return TravellerRepository.sourceDelete(source);
//
//    }
//
//
//    //***************  Update from PersonalDetails only firstname and lastname  ***************

//
//
////    //***************  Search User Data  ***************
////    @GetMapping("selectAllFromUserLoginByUserID/{userID}")
////    public static HashMap<String,Object>findByIDUserLogin(@PathVariable("userID") int userID, @RequestBody UserLogin userLogin) throws SQLException {
////      return (HashMap<String, Object>) TravellerRepository.findByID(userID);
////    }
//
//
//    //***************  Search PersonalDetails Data using userid  ***************
//    @GetMapping("selectAllFromPersonalDetailsWhereUserID/{userID}")
//    public static HashMap<String,Object>findByIDPersonalDetails(@PathVariable("userID") int userID, @RequestBody PersonalDetails personalDetails) throws SQLException {
//        return (HashMap<String, Object>) TravellerRepository.findByIDPersonalDetails(userID);
//    }
//
//    // ***************  renewed pass using personal details  ***************
//    @PutMapping("renewedPass/{personalID}")
//    public String updateInsert(@PathVariable int personalID,@RequestBody PersonalDetails personalDetails) throws SQLException {
//        String result = TravellerRepository.updateInsertPersonalDetails(personalID,personalDetails);
//        return result;
//    }


}
