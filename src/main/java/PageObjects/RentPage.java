package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

public class RentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By fieldDate = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    private By fieldRentalPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");
    private By description = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    private By orderButton = By.xpath("(.//button[text() = 'Заказать'])[2]");
    private By popup = By.xpath(".//*[@class = 'Order_ModalHeader__3FDaJ']");
    private By getStatusButton = By.xpath(".//button[text() = 'Посмотреть статус']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void setFieldDate(String date) {
        waitForElementVisible(fieldDate);
        driver.findElement(fieldDate).sendKeys(date);
    }

    public void clickCheckBoxColor(String color) {
        driver.findElement(By.xpath(".//*[@id = '" + color + "' and @class = 'Checkbox_Input__14A2w']")).click();
    }

    public void setDescription(String firstName) {
        driver.findElement(description).sendKeys(firstName);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickPopUpButton(String button) {
        waitForElementVisible(popup);
        driver.findElement(By.xpath(".//*[text() = '" + button + "' and @class = 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }

    public void selectRentalPeriod(String term) {
        driver.findElement(fieldRentalPeriod).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='" + term + "']")).click();
    }

    public void checkNotificationOfOrder(String checktextNotificationER) {
        String checktextNotificationAR = driver.findElement(By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']")).getAttribute("textContent");
        assertThat("Text not found", checktextNotificationAR, containsString(checktextNotificationER));
    }

    public void clickGetStatusButton() {
        driver.findElement(getStatusButton).click();
    }
}
