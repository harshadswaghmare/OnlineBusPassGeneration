package OnlineBusPassGeneration.OnlineBusPass.controller;

import OnlineBusPassGeneration.OnlineBusPass.model.PaymentModule;
import OnlineBusPassGeneration.OnlineBusPass.model.PersonalDetails;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.TravellerRepository;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class PaymentController {

    @PostMapping("insertPayment")
    public String insertData(@RequestBody PaymentModule paymentModule) {
        return TravellerRepository.insertPaymentModule(paymentModule);
    }

    // ***************  Find PersonalDetails by UserID  ***************
    @GetMapping("selectAllFromPaymentDetailsForPass/{personId}")
    public static JSONObject findUser(@PathVariable int personId, PersonalDetails personalDetails)throws SQLException {
        JSONObject jsonObject = new JSONObject();
        jsonObject = TravellerRepository.passGeneration(personId);
        return jsonObject;
    }
}
