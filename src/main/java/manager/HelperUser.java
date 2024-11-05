package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HelperUser extends HelperBase {


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm() {
//        WebElement loginTab = wd.findElement(By.cssSelector("a[href='/login']"));
//        //xpath//a[text()='LOGIN']
//        loginTab.click();

        click(By.cssSelector("a[href='/login']"));
    }


    public void fillLoginRegistrationForm(String email, String password) {
//        WebElement emailInput = wd.findElement(By.name("email"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys(email);
        type(By.name("email"), email);
//
//        WebElement passwordInput = wd.findElement(By.xpath("//input[last()]"));
//        passwordInput.click();
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
        type(By.xpath("//input[last()]"),password);

    }

    public void fillLoginRegistrationForm(User user){
        type(By.name("email"), user.getEmail());
        type(By.xpath("//input[last()]"),user.getPassword());
    }

    public void submitLogin() {
        click(By.xpath("//button[text()='Login']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public void submitRegistration() {
        click(By.xpath("//button[text()='Registration']"));
    }

    public boolean isRegistered() {
        //.c0ntact-page_message__2qafk>h1
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")),"No Contacts here!"));
        //return isElementPresent(By.xpath("//h1[text()=' No Contacts here!']"));
    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }

    public boolean isSaveButtonNotActive() {
        WebElement el = wd.findElement(By.xpath("//b[text()='Save']"));
        boolean res1 = el.isEnabled();
        return !res1;

    }

    public void goHomePage() {
        WebElement el = wd.findElement(By.xpath("//a[text()='HOME']"));
        el.click();
    }
}
