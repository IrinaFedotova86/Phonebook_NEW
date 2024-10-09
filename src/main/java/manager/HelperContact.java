package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperContact extends  HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {

        WebElement el = wd.findElement(By.xpath("//a[text()='ADD']"));
        el.click();
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
}
