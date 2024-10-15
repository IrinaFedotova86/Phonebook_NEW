package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperContact extends  HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {

        WebElement el = wd.findElement(By.xpath("//a[text()='ADD']"));
        el.click();
       // click(By.cssSelector("a[href='/add']"));
    }

    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    public void submit() {

        click(By.xpath("//b[text()='Save']"));
        //click(By.cssSelector(".add_form_2rsm2>button"));
    }

    public boolean contactAddedSuccess(Contact contact) {
        return isElementPresent(By.xpath("//div[@h2='"+contact.getName()+"']"));
    }

    public String newContactname(Contact contact) {
        WebElement el = wd.findElement(By.xpath("//h2[text()='"+contact.getName()+"']"));
        String text = el.getText();
        return text;
    }

    public boolean isAddFormPresent() {
        return isElementPresent(By.cssSelector("div.add_form__2rsm2"));
    }

    public boolean contactAddedByNameSuccess(String name) {
        List<WebElement>list = wd.findElements(By.cssSelector("h2"));
        for(WebElement el:list){
            if(el.getText().equals(name)){
                return  true;
            }
        }
        return false;
    }

    public boolean contactAddedByPhonrSuccess(String phone) {
        List<WebElement>list = wd.findElements(By.cssSelector("h3"));
        for(WebElement el:list){
            if(el.getText().equals(phone)){
                return  true;
            }
        }
        return false;
    }

    public boolean isAddButtonBlack() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }


    public void provideContacts() {
        List<WebElement>list = wd.findElements(By.cssSelector("h3"));
        if(list.size() <3){
            Contact contact;
            for(int j=0;j<3;j++) {
                int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
                    contact = Contact.builder()
                        .name("Roma"+i)
                        .lastName("Abramovich")
                        .phone("89523123" + i)
                        .email("abramovich" + i + "@gmail.com")
                        .address("Herclia")
                        .description("CDa")
                        .build();
                openContactForm();
                fillContactForm(contact);
                submit();
                pause(5000);
            }
        }
    }
}
