package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("rnk87@mail.ru").setPassword("QweAsd12$"));
        }

        app.getHelperContact().provideContacts();//if list size <3 -> add 3 contacts
        //app.getHelperRemove().pause(25);
    }

    @Test
    public void removeFirstContact(){
       // Assert -> size less by one
        int i = app.getHelperRemove().contactSize();
        app.getHelperRemove().removeFirstContact();
        app.getHelperRemove().pause(10000);
        Assert.assertEquals(app.getHelperRemove().contactSize(), i-1);

    }

    @Test
    public void removeAllContact(){
       //"No Contacts here!"
        app.getHelperRemove().removeAllContact();
        app.getHelperRemove().pause(10000);
        Assert.assertEquals(app.getHelperRemove().contactSize(), 0);
        Assert.assertTrue(app.getHelperRemove().isElementPresent(By.xpath("//h1[text()=' No Contacts here!']")));
    }

    @AfterMethod
    public void postCondition(){

        app.getHelperUser().goHomePage();
    }

}
