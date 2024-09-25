package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    HelperUser helperUser;

    WebDriver wd;

    public void init() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://telranedu.web.app/");
        helperUser=new HelperUser(wd);
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public void stop() {
        wd.quit();
    }
}
