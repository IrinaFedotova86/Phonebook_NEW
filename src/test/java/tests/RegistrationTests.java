package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void  precondition(){
        //is SignOut present ==> then do logout

        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        int z = (int) (System.currentTimeMillis() / 1000) % 3600;
        User user = new User().setEmail("mara"+z+"@gmail.com").setPassword("Wwon"+z+"$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        //app.getHelperUser().fillLoginRegistrationForm("mara"+z+"@gmail.com","Mmar"+z+"$");
        app.getHelperUser().submitRegistration();


        Assert.assertTrue(app.getHelperUser().isRegistered());
    }

    @Test
    public void loginSuccessModel(){
        int z = (int) (System.currentTimeMillis() / 1000) % 3600;
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara"+z+"@gmail.com","Mmar"+z+"$");
        app.getHelperUser().submitRegistration();


        Assert.assertTrue(app.getHelperUser().isRegistered());
    }

    //@Test(description = "Bug report #23456", enabled = false)
    @Test
    public void registrationWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("maragmail.com","Mmar123456$");
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("Wrong email or password"));
    }

    @Test
    public void registrationWrongPassowrd(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("Wrong email or password"));
    }

    @Test
    public void registrationExistUser(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar123456$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAllertPresent("User already exist"));
    }

}
