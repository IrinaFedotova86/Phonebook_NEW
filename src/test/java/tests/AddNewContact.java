package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewContact extends TestBase{

    @BeforeClass
    public void precondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("rnk87@mail.ru").setPassword("QweAsd12$"));
        }
    }

    @Test
    public void addNewContactSuccess(){
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        Contact contact = Contact.builder()
                .name("Roma")
                .lastName("Abramovich")
                .phone("89523123"+i)
                .email("abramovich"+i+"@gmail.com")
                .address("Herclia")
                .description("CDa")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().getScreen("src/test/screenshots/screen"+i+".png");
        app.getHelperContact().submit();
        //Assert.assertTrue(app.getHelperContact().contactAddedSuccess(contact));
        Assert.assertEquals(app.getHelperContact().newContactname(contact), contact.getName());
    }

    @Test
    public void addNewContactRequiredFieldsSuccess() {
        Contact contact = Contact.builder()
                .name("Kate")
                .lastName("Asty")
                .phone("165612233338")
                .email("astykate@gmail.com")
                .address("NY")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().contactAddedByNameSuccess(contact.getName()));
        Assert.assertTrue(app.getHelperContact().contactAddedByPhonrSuccess(contact.getPhone()));
        Assert.assertEquals(app.getHelperContact().newContactname(contact), contact.getName());
    }

    @Test
    public void addNewContactEmptyName(){

        Contact contact = Contact.builder()
                .name("")
                .lastName("Vady")
                .phone("89534967778")
                .email("vady@gmail.com")
                .address("Praga")
                .description("No")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddButtonBlack());

    }

    @Test
    public void addNewContactEmptyLastName(){

        Contact contact = Contact.builder()
                .name("Vera")
                .lastName("")
                .phone("89534967778")
                .email("vady@gmail.com")
                .address("Praga")
                .description("No")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().submit();
        //Assert.assertTrue(app.getHelperUser().isSaveButtonNotActive());
        Assert.assertTrue(app.getHelperContact().isAddFormPresent());
    }

    @Test
    public void addNewContactEmptyPhone(){

        Contact contact = Contact.builder()
                .name("vera")
                .lastName("vady")
                .phone("")
                .email("vady@gmail.com")
                .address("Praga")
                .description("No")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().submit();
        //Assert.assertTrue(app.getHelperUser().isAllertPresent("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAllertPresent("Phone not valid"));
    }

    @Test
    public void addNewContactWrongPhone(){

        Contact contact = Contact.builder()
                .name("Vera")
                .lastName("Vady")
                .phone("234")
                .email("vady@gmail.com")
                .address("Praga")
                .description("No")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAllertPresent("Phone not valid"));
    }

    @Test
    public void addNewContactEmptyEmail(){

        Contact contact = Contact.builder()
                .name("Vera")
                .lastName("Vady")
                .phone("89534967778")
                .email("")
                .address("Praga")
                .description("No")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().submit();
        Assert.assertEquals(app.getHelperContact().newContactname(contact), contact.getName());
    }

    @Test
    public void addNewContactWrongEmail(){

        Contact contact = Contact.builder()
                .name("Vera")
                .lastName("Vady")
                .phone("89534967778")
                .email("rfte")
                .address("Praga")
                .description("No")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAllertPresent("Email not valid"));
    }

    @Test
    public void addNewContactEmptyAddress(){

        Contact contact = Contact.builder()
                .name("Vera")
                .lastName("Vady")
                .phone("89534967778")
                .email("vady@gmail.com")
                .address("")
                .description("No")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);

        app.getHelperContact().submit();
        //Assert.assertTrue(app.getHelperUser().isSaveButtonNotActive());
        Assert.assertTrue(app.getHelperContact().isAddFormPresent());
    }


    @AfterMethod
    public void postCondition(){

       app.getHelperUser().goHomePage();
    }
}

