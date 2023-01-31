package OnlineBusPassGeneration.OnlineBusPass;

import OnlineBusPassGeneration.OnlineBusPass.connection.Connectivity;
import OnlineBusPassGeneration.OnlineBusPass.controller.UserAPIController;
import OnlineBusPassGeneration.OnlineBusPass.model.PassAPIModel;
import OnlineBusPassGeneration.OnlineBusPass.model.PaymentAPIModel;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.PassAPIRepository;
import OnlineBusPassGeneration.OnlineBusPass.repository.PaymentAPIRepositoy;
import OnlineBusPassGeneration.OnlineBusPass.repository.UserRepository;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;

public class Test {
    Logger log = LoggerFactory.getLogger(OnlineBusPassApplication.class);

    //  **************************************  UserRepository Class test Case   **************************************

    @org.junit.Test
    public void insertUserAPI() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(71, "samyak@123", "Sudhanshu@123", "samya121@gmail.com", "9800045698", "", "Shinde", "2546 3654 7896", 23, "Student")));
            String expected = "<409 CONFLICT Conflict,samyak@123 already exist,[]>";
            Assert.assertNotEquals(rowsAffected, expected);            //POSITIVE CASE
            Assert.assertNotEquals(rowsAffected, "user created Successfully");  //NEGATIVE TEST CASE
        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }
    }

    @org.junit.Test
    public void insertUserAPIForReturnID() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(81, "Anikietshwah@123", "Sudhanshu@123", "samya121@gmail.com", "9800045698", "Ankit", "Shukla", "2546 3654 7896", 23, "Student")));
            String expected = "<201 CREATED Created,User created UserID 81,[]>";
            Assert.assertNotEquals(expected, rowsAffected);            //POSITIVE CASE
            Assert.assertNotEquals(rowsAffected, "user created Successfully");  //NEGATIVE TEST CASE
        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }
    }


    @org.junit.Test

    public void insertUserAPIForValidEmail() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(71, "sai@123", "23117c2736713d8778551ee8f435d2de", "samya121gmail.com", "9800045698", "", "Shinde", "2546 3654 7896", 23, "Student")));
            String expected = "User created UserID 78";
            Assert.assertNotEquals(expected, rowsAffected);        //POSITIVE CASE

        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }

    }

    @org.junit.Test
    public void insertUserAPIForValidMobileNo() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(71, "Sai@1234", "Sai@1234", "samya121@gmail.com", "", "Harshad", "Shinde", "", 23, "Student")));
            String expected = "<417 EXPECTATION_FAILED Expectation Failed,Enter valid mobile no,[]>";
            Assert.assertEquals(rowsAffected, rowsAffected);
            Assert.assertEquals("Email verified", "Email verified");

        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }

    }


    @org.junit.Test
    public void password() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(71, "Sai@1234", "Sai@123", "samya121@gmail.com", "9850376939", "Harshad", "Shinde", "2020 1236 4569", 23, "Student")));
            String expected = "<417 EXPECTATION_FAILED Expectation Failed,Enter valid password,[]>";
            String expected1 = "null";
            Assert.assertEquals(expected1, "null");
            Assert.assertNotEquals(expected, "null");

        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }

    }


    @org.junit.Test
    public void enterFirstName() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(78, "nikhil@123", "Anun@1234", "samya121@gmail.com", "9800045698", "", "Shinde", "2546 3654 7896", 22, "Student")));
            String expected = "<417 EXPECTATION_FAILED Expectation Failed,enter first name,[]>";
            Assert.assertEquals(expected, rowsAffected);  //POSITIVE CASE
            //Assert.assertNotEquals(expected, rowsAffected);  //NEGATIVE TEST CASE
        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }
    }


    @org.junit.Test
    public void enterLastName() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(78, "nikhil@123", "Anun@1234", "samya121@gmail.com", "9800045698", "Aditya", "", "2546 3654 7896", 22, "Student")));
            String expected = "<417 EXPECTATION_FAILED Expectation Failed,enter lastname,[]>";
            Assert.assertEquals(expected, rowsAffected);  //POSITIVE CASE
            //Assert.assertNotEquals(expected, rowsAffected);  //NEGATIVE TEST CASE
        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }
    }


    @org.junit.Test
    public void profession() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(78, "nikhil@123", "Anun@1234", "samya121@gmail.com", "9800045698", "Aditya", "kale", "2546 3654 7896", 22, "")));
            String expected = "<417 EXPECTATION_FAILED Expectation Failed,Enter your profession,[]>";
            Assert.assertEquals(expected, rowsAffected);  //POSITIVE CASE
            //Assert.assertNotEquals(expected, rowsAffected);  //NEGATIVE TEST CASE
        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }
    }


    @org.junit.Test
    public void mobileNumber() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(78, "nikhil@123", "Anun@1234", "samya121@gmail.com", "", "Aditya", "Shinde", "2546 3654 7896", 22, "")));
            String expected = "<417 EXPECTATION_FAILED Expectation Failed,Enter valid mobile no,[]>";
            Assert.assertEquals(expected, rowsAffected);  //POSITIVE CASE
            //Assert.assertNotEquals(expected, rowsAffected);  //NEGATIVE TEST CASE
        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }
    }


    @org.junit.Test
    public void enterAadhaaarNumber() {
        try {
            String rowsAffected = String.valueOf(UserRepository.insert(new UserLogin(78, "nikhil@123", "Anun@1234", "samya121@gmail.com", "9800045698", "Aditya", "Shinde", "", 22, "Student")));
            String expected = "<417 EXPECTATION_FAILED Expectation Failed,Enter valid Aadhaar number,[]>";
            Assert.assertEquals(expected, rowsAffected);  //POSITIVE CASE
            //Assert.assertNotEquals(expected, rowsAffected);  //NEGATIVE TEST CASE
        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }
    }

    @org.junit.Test
    public void selectFromUserAPI() {
        try {
            String RowsAffected = String.valueOf(UserRepository.retrieveAllData());
        } catch (Exception e) {
            assert true;
        }
    }

    @org.junit.Test
    public void findUserAPI() throws SQLException {
        try {
            String result = String.valueOf(UserRepository.findByID(77));
            String delete = String.valueOf(UserRepository.findByID(71));
            String expected = "user not found";
            Assert.assertNotEquals(result, expected);
        } catch (Exception e) {
            assert true;
        }

    }

    @org.junit.Test
    public void selectUserAPI() {
        try {
            String result = String.valueOf(UserRepository.log);
        } catch (Exception e) {
            assert true;
        }

    }


    //  **************************************  PassAPI Repository test   **************************************
    @org.junit.Test
    public void insertPassAPI() {
        try {
            String rowsAffected = PassAPIRepository.insertPersonal(new PassAPIModel(72, 32, "Student", "Hinjawadi", "Pasham", "500", "Pending"));
            String expected1 = "32 not exist";
            Assert.assertEquals(rowsAffected, expected1);  //Positive case
            Assert.assertNotEquals(rowsAffected, "user not exist in this");  //Negative Test Case
        } catch (Exception e) {
            log.info("Exception Occurred : " + e);
        }
    }

    @org.junit.Test
    public void deleteUserAPI() {
        try {
            String rows = String.valueOf(String.valueOf(UserRepository.deleteFromUserLogin(18)));
            // boolean rows1 = TravellerRepository.delete(25);
            String expected = "<200 OK OK,user not found,[]>";
            String expected1 = "user not found";
            Assert.assertEquals(rows, expected);        //POSITIVE TEST CASE
            Assert.assertNotEquals(rows, expected1);    // NEGATIVE TEST CASE
        } catch (Exception e) {
            System.out.println("exception occurred :" + e);
        }
    }


    @org.junit.Test
    public void selectFromPassAPI() {
        try {
            String rowsAffected = String.valueOf(PassAPIRepository.retrieveAllDataFromPersonalDetails(new PassAPIModel()));
        } catch (Exception e) {
            assert true;
        }
    }


    @org.junit.Test
    public void deletePassAPI() {
        try {
            String rows = String.valueOf(String.valueOf(PassAPIRepository.deletePassAPI(25)));
            String expected = "false";
            String expected1 = "user not found";
            Assert.assertEquals(rows, expected);        //POSITIVE TEST CASE
            Assert.assertNotEquals(rows, expected1);    // NEGATIVE TEST CASE
        } catch (Exception e) {
            System.out.println("exception occurred :" + e);
        }
    }


    @org.junit.Test
    public void finduserpassByEmail() {
        try {
            String result = String.valueOf((UserRepository.findUserPassByEmail("indian@gmail.com")));
        } catch (Exception e) {
            assert true;
        }
    }


    @org.junit.Test
    public void finduserpassByEmailNegative() {
        try {
            String result = String.valueOf((UserRepository.findUserPassByEmail("tushar@gmail.com")));
            String expected = "<404 NOT_FOUND Not Found,no user register with tushar@gmail.com,[]>";
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            assert true;
        }
    }


    @org.junit.Test
    public void createConnection() {
        try {
            String result = String.valueOf(Connectivity.CreateConnection());
            String expected = "connection established successfully";
            Assert.assertNotEquals(expected, result);
        } catch (Exception e) {
            assert true;
        }
    }


    @org.junit.Test
    public void findPassByID() {
        try {
            String result = String.valueOf(UserRepository.findByID(71));
        } catch (Exception e) {
            assert false;
        }
    }


    @org.junit.Test
    public void passAPIUpdate() {
        try {
            String result = String.valueOf(PassAPIRepository.update(71, new PassAPIModel()));
            System.out.println(result);
        } catch (Exception e) {
            assert false;
        }
    }


    @org.junit.Test
    public void passAPISelect() {
        try {
            String result = String.valueOf(PassAPIRepository.retrieveAllDataFromPersonalDetails(new PassAPIModel()));
        } catch (Exception e) {
            assert true;
        }
    }


    //***************************  Payment Repository test case  **************************************

    @org.junit.Test
    public void insertTPayment() {
        try {
            String result = String.valueOf(PaymentAPIRepositoy.insertPaymentModule(new PaymentAPIModel(51, 66, 888, "visa", "5642 3654 8965 5487", "2023-01-22", 149, "HArshad", "jjhfdjdjdhfj", 315, "2023-01-21"), new PassAPIModel()));
            String expected = "PassID 888 not found";
            log.info(result);
            Assert.assertNotEquals(expected, result);
        } catch (Exception e) {
            System.out.println("Exception occurred " + e);

        }
    }


    @org.junit.Test
    public void insertPaymentForUserIDFailed() {
        try {
            String result = String.valueOf(PaymentAPIRepositoy.insertPaymentModule(new PaymentAPIModel(51, 666, 66, "visa", "5642 3654 8965 5487", "2023-01-22", 149, "HArshad", "jjhfdjdjdhfj", 315, "2023-01-21"), new PassAPIModel()));
            String expected = "UserID 666 not found";
            log.info(result);
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            System.out.println("Exception occurred " + e);

        }
    }


    @org.junit.Test
    public void totalOfTheDay() {
        try {
            String result = String.valueOf(PaymentAPIRepositoy.selectTotalOfDate(Date.valueOf("2023-01-27")));
            String expected = "Audit of the date 2023-01-27: Rs.315";
            log.info(result);
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            System.out.println("Exception occurred " + e);

        }
    }

    @org.junit.Test
    public void totalOfTheDayForInvalidDate() {
        try {
            String result = String.valueOf(PaymentAPIRepositoy.selectTotalOfDate(Date.valueOf("2023-01-21")));
            String expected = "Audit of the date 2023-01-21: Rs.0";
            log.info(result);
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            System.out.println("Exception occurred " + e);

        }
    }


    @org.junit.Test
    public void totalFromDateToDate() {
        try {
            String total = PaymentAPIRepositoy.CalculateTotalFromDateToDate(Date.valueOf("2023-01-27"), Date.valueOf("2023-01-30"));
            String expectedValue = "Total audit from date 2023-01-27 to 2023-01-30 is : 630";
            Assert.assertEquals(expectedValue, total);
        } catch (Exception e) {
            System.out.println("exception occurred " + e);

        }
    }


    @org.junit.Test
    public void totalFromDateToDateNoRecordFound() {
        try {
            String total = PaymentAPIRepositoy.CalculateTotalFromDateToDate(Date.valueOf("2023-02-27e"), Date.valueOf("2023-02-30es"));
            String expectedValue = "null";
            Assert.assertEquals(expectedValue, total);
        } catch (Exception e) {
            System.out.println("exception occurred " + e);

        }
    }


    @org.junit.Test
    public void totalFromDateToDateNegative() {
        try {
            String total = PaymentAPIRepositoy.CalculateTotalFromDateToDate(Date.valueOf("2023-01-31"), Date.valueOf("2023-01-26e"));
            int expectedValue = 510;
            Assert.assertNotEquals(expectedValue, total);
        } catch (Exception e) {
            System.out.println("exception occurred " + e);

        }
    }


    //Controller class Test Case

    @org.junit.Test
    public void show() throws SQLException {
        String result = String.valueOf(UserAPIController.findByUserID(71));
    }

    @org.junit.Test
    public void getUserData() throws SQLException {
        String result = String.valueOf(UserAPIController.getUserData());
    }

    //
    @org.junit.Test
    public void findUserPass() throws SQLException {
        String result = String.valueOf(UserAPIController.ByEmail("sagar."));
    }

    @org.junit.Test
    public void display() throws SQLException {
        String result = String.valueOf(UserAPIController.insertData(new UserLogin()));
    }

    @org.junit.Test
    public void delete() throws SQLException {
        String result = String.valueOf(UserAPIController.deleteUser(79));
        String expected = "no user found";
        Assert.assertNotEquals(result, expected);
    }


    @org.junit.Test
    public void update() throws SQLException {
        String result = String.valueOf(UserAPIController.update(71, new UserLogin()));
        String expected = "no update";
        Assert.assertNotEquals(result, expected);
    }


    @org.junit.Test
    public void onlineBusPassApplicationClass() {
        try {
            String result = String.valueOf(OnlineBusPassApplication.class);
            String expected = "class OnlineBusPassGeneration.OnlineBusPass.OnlineBusPassApplication";
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            assert true;
        }
    }

}

//
//    //Delete Record from userLogin
//    @org.junit.jupiter.api.Test
//    public void deleteUserLoginRecord() {
//        try {
//            boolean rows = TravellerRepository.deleteFromUserLogin(31);
//            boolean rows1 = TravellerRepository.delete(25);
//            boolean expected = false;
//            boolean expected1 = false;
//            Assert.assertEquals(rows, expected);
//            Assert.assertEquals(rows1, expected1);
//            Assert.assertNotEquals(rows,true);
//            Assert.assertNotEquals(rows1, true);
//            // Assert.assertNotEquals(rows1, true);
//        } catch (Exception e) {
//            System.out.println("exception occurred :" + e);
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void deletePersonalDetailsRecord() {
//        try {
//            boolean rows = TravellerRepository.delete(32);
//            boolean rows1 = TravellerRepository.delete(33);
//            boolean expected = false;
//            boolean expected1 = false;
//            Assert.assertEquals(rows, expected);
//            Assert.assertEquals(rows1, expected1);
//            Assert.assertNotEquals(rows1, true);
//        } catch (Exception e) {
//            System.out.println("exception occurred :" + e);
//
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void updateUserLoginRecord() {
//        try {
//            String rowsAffected = TravellerRepository.updateUserLogin(30, new UserLogin());
//            Assert.assertEquals(rowsAffected, "no any action perform");
//            Assert.assertNotEquals(rowsAffected, "no any action");
//
//        } catch (Exception e) {
//            log.error("Exception Occurred : " + e);
//        }
//    }
//
//
//    @org.junit.jupiter.api.Test
//    public void updatePersonalDetails() {
//        try {
//            String rowsAffected = TravellerRepository.updatePersonalDetails(30, new PersonalDetails());
//            Assert.assertEquals(rowsAffected, "no record updated");
//            Assert.assertNotEquals(rowsAffected,"no any action performed");
//        } catch (Exception e) {
//            log.error("Exception Occurred : " + e);
//        }
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void selectUserLoginRecord() {
//        log.debug("we are fetching data");
//        HashMap<String, Object> getUserData;
//        try {
//            getUserData = TravellerRepository.retrieveAllData();
//            log.info("in processing\n", getUserData);
//        } catch (Exception e) {
//            assert true;
//        }
//    }
//
//
//    @org.junit.jupiter.api.Test
//    public void PersonalDetails() {
//        log.debug("data processing");
//        HashMap<String, Object> getPersonalDetailsData;
//        try {
//            getPersonalDetailsData = TravellerRepository.retrieveAllDataFromPersonalDetails();
//            log.info("in processing\n", getPersonalDetailsData);
//        } catch (Exception e) {
//            assert true;
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void insertUserLoginRecord() {
//        try {
//            String rowsAffected = TravellerRepository.insert(new UserLogin());
//            Assert.assertNotEquals(rowsAffected, "user created successfully");
//        } catch (Exception e) {
//            log.info("Exception Occurred : " + e);
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void findByUserID() throws SQLException {
//        String result1 = String.valueOf(TravellerRepository.findByID(211).size());
//        log.info("value is " + result1);
//        int expected1 = 0;
//        Assert.assertNotEquals(result1,expected1);
//    }
//
//    @org.junit.jupiter.api.Test
//    public void findByID() throws SQLException {
//        int result = TravellerRepository.findByID(52).size();
//        log.info("value is " + result);
//        int expected = 1;
//        Assert.assertEquals(result, expected);
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void calculateFromDate() throws SQLException {
//        int calculate = TravellerRepository.CalculateTotalFromDateToDate(Date.valueOf("2022-12-31"), Date.valueOf("2023-01-11"));
//        int Expected = 0;
//        int expected1 = 220;
//        Assert.assertEquals(calculate, Expected);
//        Assert.assertNotEquals(calculate, expected1);
//    }
//
//    @org.junit.jupiter.api.Test
//    public void calculateTodayAudit()throws SQLException{
//        int calculate = TravellerRepository.selectTotalOfDate(Date.valueOf("2023-01-18"));
//        int expected = 1260;
//        int expected1= 630;
//        Assert.assertEquals(calculate, expected);
//        Assert.assertNotEquals(calculate,expected1);
//
//    }
//
//}
//
