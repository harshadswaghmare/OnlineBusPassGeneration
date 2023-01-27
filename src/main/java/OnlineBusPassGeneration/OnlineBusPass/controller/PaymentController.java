package OnlineBusPassGeneration.OnlineBusPass.controller;


import OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel;
import OnlineBusPassGeneration.OnlineBusPass.model.PaymentAPIModel;

import OnlineBusPassGeneration.OnlineBusPass.repository.PaymentAPIRepositoy;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/payment")
@RestController
public class PaymentController {
    //*********make payment or insert payment  ************
    @PostMapping("/post")
    public String insertData(@RequestBody PaymentAPIModel paymentModule, PassAPIModel passAPIModel) {
        return PaymentAPIRepositoy.insertPaymentModule(paymentModule,passAPIModel);
    }
}

//
//
//    //*******************  selectAllFromPaymentDetails *****************
//    @GetMapping("selectAllFromPaymentDetails")
//    public static HashMap<String, Object> selectAllFromPaymentDetails()throws SQLException {
//        return TravellerRepository.selectAllFromPaymentDetails();
//    }
//
//
//
//
//    // ***************  Find PersonalDetails by UserID  ***************
//    @GetMapping("selectAllFromPaymentDetailsForPass/{personId}")
//    public static JSONObject findUser(@PathVariable int personId, PassAPIModel passAPIModel)throws SQLException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject = TravellerRepository.passGeneration(personId);
//        return jsonObject;
//    }
//}
