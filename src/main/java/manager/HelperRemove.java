package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperRemove extends HelperBase{
    public HelperRemove(WebDriver wd) {
        super(wd);
    }

    public void removeFirstContact() {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        list.get(0).click();
        click(By.xpath("//button[text()='Remove']"));
        pause(20);
        //for(WebElement el:list) {
        //    el.click();
        //    click(By.xpath("//button[text()='Remove']"));
        //    break;
        //}
    }

    public int contactSize() {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        return list.size();
    }

    public void removeAllContact() {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for(WebElement el:list) {
                el.click();
                click(By.xpath("//button[text()='Remove']"));
            }
        pause(5000);
    }
}
