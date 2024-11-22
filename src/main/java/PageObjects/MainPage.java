package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    private By dropDownListQuestions = By.xpath("(.//*[@class =  'accordion__button'])[1]");
    private By closeCookie = By.xpath(".//*[text() = 'да все привыкли']");
    private By orderButtton = By.className("Button_Button__ra12g");
    private By orderButttonMiddle = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickDropDownListQuestions(String questionText) {
        driver.findElement(By.xpath(".//*[@class =  'accordion__button' and text() = '" +questionText+ "']")).click();
    }

    public void clickCloseCookie() {
        driver.findElement(closeCookie).click();
    }

    public void clickOrderButtton() {
        driver.findElement(orderButtton).click();
    }

    public void clickOrderButttonMiddle() {
        driver.findElement(orderButttonMiddle).click();
    }

    public void scrollToDropDownListQuestions(){
        WebElement element = driver.findElement(dropDownListQuestions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void checktextQuestions(String textAnswerER, String textQuestion) {
        String checktextQuestionsAR = driver.findElement(By.xpath("//*[@class =  'accordion__button' and text() = '" + textQuestion + "']/../..//div[@class = 'accordion__panel']")).getAttribute("textContent");
        assertEquals("Text not found", textAnswerER, checktextQuestionsAR);
    }
}
