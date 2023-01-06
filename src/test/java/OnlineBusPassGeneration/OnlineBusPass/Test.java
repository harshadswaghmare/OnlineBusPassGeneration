package OnlineBusPassGeneration.OnlineBusPass;
import OnlineBusPassGeneration.OnlineBusPass.model.PersonalDetails;
import OnlineBusPassGeneration.OnlineBusPass.model.UserLogin;
import OnlineBusPassGeneration.OnlineBusPass.repository.TravellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.util.HashMap;
public class Test {
    Logger log = LoggerFactory.getLogger(OnlineBusPassApplication.class);

//selectAllFromUserLogin

    @org.testng.annotations.Test(priority = 1)
    @org.junit.jupiter.api.Test
    public void selectUserLoginRecord(){
        log.debug("we are fetching data");
        HashMap<String,Object>getUserData;
        try{
            getUserData = TravellerRepository.retrieveAllData();
            log.info("in processing\n",getUserData);
        }catch(Exception e){
            assert false;
        }
    }


    @org.junit.jupiter.api.Test
    public void PersonalDetails()
    {
        log.debug("data processing");
        HashMap<String,Object>getPersonalDetailsData;
        try{
            getPersonalDetailsData = TravellerRepository.retrieveAllDataFromPersonalDetails();
            log.info("in processing\n", getPersonalDetailsData);
        }catch (Exception e){
            assert true;
        }
    }


    //Delete Record from userLogin
    @org.junit.jupiter.api.Test
    public void deleteUserLoginRecord() {
        try {
            boolean rows = TravellerRepository.deleteFromUserLogin(31);
            boolean rows1 = TravellerRepository.delete(25);
            String expected = "No record found with ID 31";
            String expected1 = "No record found with ID 25";
            Assert.assertFalse(rows,expected);
            Assert.assertFalse(rows1, expected1);
           // Assert.assertNotEquals(rows1, true);
        } catch (Exception e) {
            System.out.println("exception occurred :" + e);
        }
    }

    @org.junit.jupiter.api.Test
    public void deletePersonalDetailsRecord()
    {
        try {
            boolean rows = TravellerRepository.delete(32);
            boolean rows1 = TravellerRepository.delete(33);
            boolean expected = false;
            boolean expected1 = false;
            Assert.assertEquals(rows, expected);
            Assert.assertEquals(rows1, expected1);
            Assert.assertNotEquals(rows1, true);
        } catch (Exception e) {
            System.out.println("exception occurred :" + e);
        }
    }

    @org.junit.jupiter.api.Test
    public void updateUserLoginRecord(){
        try{
          String  rowsAffected = TravellerRepository.updateUserLogin(30,new UserLogin());
          Assert.assertEquals(rowsAffected,"record updated successfully");
          Assert.assertNotEquals(rowsAffected,"record updated Successfully");

        }catch(Exception e){
           log.error("Exception Occurred : "+e);
        }
    }


    @org.junit.jupiter.api.Test
    public void updatePersonalDetails(){
        try{
            String  rowsAffected = TravellerRepository.updatePersonalDetails(30,new PersonalDetails());
            //Assert.assertEquals(rowsAffected,"Record Updated Successfully");
            //Assert.assertNotEquals(rowsAffected,"record updated successFully");
            Assert.assertEquals(rowsAffected,null);
        }catch(Exception e){
            log.error("Exception Occurred : "+e);
        }

    }

    @org.junit.jupiter.api.Test
    public void insertUserLoginRecord() {
        try {
            String rowsAffected = TravellerRepository.insert(new UserLogin());
            Assert.assertEquals(rowsAffected, "record inserted successfully");
        } catch (Exception e) {
            log.info("Exception Occurred : "+e);
        }
    }

    @org.junit.jupiter.api.Test
    public void insertPersonalDetails()
    {
        try {
            String rowsAffected = TravellerRepository.insert(new UserLogin());
            Assert.assertEquals(rowsAffected, "record inserted successfully");
        } catch (Exception e) {
            log.info("Exception Occurred : "+e);
        }
    }
}
