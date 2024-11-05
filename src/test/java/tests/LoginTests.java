package tests;

import manager.DataProviderUser;
import models.User;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void  precondition(){
        //is SignOut present ==> then do logout

        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method logout finish");
        }
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
        logger.info("Start test LoginSuccess");
        logger.info("Test data ---> email: "+email+" & password: "+password);
       app.getHelperUser().openLoginRegistrationForm();
       app.getHelperUser().fillLoginRegistrationForm(email,password);
       app.getHelperUser().submitLogin();
       // Assert
//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element Button 'Sign out' present");
    }

  //  @DataProvider
   // public Iterator<Object[]> loginData(){
   //     List<Object[]> list = new ArrayList<>();

   //     list.add(new Object[]{"mara@gmail.com","Mmar123456$"});
   //     list.add(new Object[]{"sonya@gmail.com","Ss12345$"});
   //     return list.iterator();
   // }

    @Test(dataProvider =  "loginModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){
        logger.info("Test data ---> email: " + user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        // Assert
//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element Button 'Sign out' present");
    }

    @Test(groups = {"smoke"})
    public void loginWrongEmail(){
        logger.info("Test data ---> email: 'maragmail.com' & password: 'Mmar123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("maragmail.com","Mmar123456$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("Wrong email or password"));
        logger.info("Assert check is alert 'Wrong email or password' present");
    }

    @Test
    public void loginWrongPassowrd(){
        logger.info("Test data ---> email: 'mara@gmail.com' & password: 'Mmar123$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar123$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("Wrong email or password"));
        logger.info("Assert check is alert 'Wrong email or password' present");
    }

    @Test
    public void loginUnregisteredUser(){
        logger.info("Test data ---> email: 'mara123@gmail.com' & password: 'Mmar123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara123@gmail.com","Mmar123456$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("Wrong email or password"));
        logger.info("Assert check is alert 'Wrong email or password' present");
    }
}
