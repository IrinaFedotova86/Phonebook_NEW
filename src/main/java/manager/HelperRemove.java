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

    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of contacts before is --->"+ before);
        removeContact();
        int after = countOfContacts();
        logger.info("Number of contacts after is --->"+ after);
        return before-after;
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(500);
    }

    private int countOfContacts() {

        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }

    public void removeAllCont(){
        while (countOfContacts()!=0){
            removeContact();
        }
    }




}
