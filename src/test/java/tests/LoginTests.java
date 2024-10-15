package tests;

import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void  precondition(){
        //is SignOut present ==> then do logout

        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess(){
        logger.info("Start");
       app.getHelperUser().openLoginRegistrationForm();
       app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar123456$");
       app.getHelperUser().submitLogin();
       // Assert
//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("End");
    }

    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar123456$");
        app.getHelperUser().submitLogin();
        // Assert
//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("maragmail.com","Mmar123456$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("Wrong email or password"));
    }

    @Test
    public void loginWrongPassowrd(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar123$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("Wrong email or password"));
    }

    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara123@gmail.com","Mmar123456$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("Wrong email or password"));
    }
}
