package tests;

import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void loginSuccess(){
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


}
