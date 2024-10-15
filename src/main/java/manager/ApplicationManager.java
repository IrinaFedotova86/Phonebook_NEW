package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    HelperRemove helperRemove;
    HelperUser helperUser;
    HelperContact helperContact;

    WebDriver wd;

    public void init() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://telranedu.web.app/");
        helperUser=new HelperUser(wd);
        helperContact=new HelperContact(wd);
        helperRemove =new HelperRemove(wd);
    }

    public HelperRemove getHelperRemove() {
        return helperRemove;
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }

    public void stop() {
        wd.quit();
    }
}
