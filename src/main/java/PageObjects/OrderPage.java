package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By fieldFirstName = By.xpath(".//input[@placeholder =  '* Имя']");
    private By fieldLastName = By.xpath(".//input[@placeholder =  '* Фамилия']");
    private By fieldAddress = By.xpath(".//input[@placeholder =  '* Адрес: куда привезти заказ']");
    private By listMetro = By.xpath(".//input[@placeholder =  '* Станция метро']");
    private By fieldPhoneNumber = By.xpath(".//input[@placeholder =  '* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath(".//*[text() = 'Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickOrderNextButton() {
        driver.findElement(nextButton).click();
    }

    public void setFirstName(String firstName) {
        driver.findElement(fieldFirstName).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(fieldLastName).sendKeys(lastName);
    }

    public void setAddress(String address) {
        driver.findElement(fieldAddress).sendKeys(address);
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(fieldPhoneNumber).sendKeys(phoneNumber);
    }

    public void setMetroField(String station) {
        waitForElementVisible(listMetro);
        driver.findElement(listMetro).click();
        driver.findElement(listMetro).sendKeys(station);

        waitForElementVisible(By.xpath(".//input[@value='" + station + "']"));
        driver.findElement(listMetro).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(listMetro).sendKeys(Keys.ENTER);
    }
}