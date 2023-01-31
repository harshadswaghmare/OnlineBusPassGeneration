package OnlineBusPassGeneration.OnlineBusPass.controller;


import OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel;
import OnlineBusPassGeneration.OnlineBusPass.model.PaymentAPIModel;

import OnlineBusPassGeneration.OnlineBusPass.repository.PaymentAPIRepositoy;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;


@RequestMapping("api/payment")
@RestController
public class PaymentController {
    //*********make payment or insert payment  ************
    @PostMapping
    public String insertData(@RequestBody PaymentAPIModel paymentModule, PassAPIModel passAPIModel)throws SQLException {
        return PaymentAPIRepositoy.insertPaymentModule(paymentModule,passAPIModel);
    }


    //********* calculate total amount of the day  ***********
    @GetMapping("/{date}")
    public static String TotalOfTheDay(@PathVariable Date date)throws  SQLException{
        String  a = String.valueOf(PaymentAPIRepositoy.selectTotalOfDate(date));
        System.out.println(a);
        return a;
    }


    @GetMapping("/{date}/{todate}")
    public static String fromDateBetween(@PathVariable Date date, @PathVariable Date todate)throws  SQLException{
        System.out.println("In a between method ");
        String a= null;
        a = String.valueOf(PaymentAPIRepositoy.CalculateTotalFromDateToDate(date,todate));
        System.out.println(a);
        return a;
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
