import PageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import PageObjects.MainPage;
import PageObjects.OrderPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class TestOrderFunctionality {
    private WebDriver driver;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String station;
    private String date;
    private String term;
    private String description;

    public TestOrderFunctionality(String firstName, String lastName, String address, String phoneNumber, String station, String date, String term, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.station = station;
        this.date = date;
        this.term = term;
        this.description = description;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                { "Сеня", "Иванов", "Гагарина 1", "89745127456", "Черкизовская", "10.05.2025", "сутки", "Оставить у двери"},
                { "Вася", "Петров", "Дыбенко", "89245651232", "Сокольники", "10.01.2025", "двое суток", "домофон 55" }
        };
    }

    @Before
    public void prepare(){
        WebDriverManager.chromiumdriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
    }

    @Test
    public void testOrderFunctionalityByFirstButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickCloseCookie();
        mainPage.clickOrderButtton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.setFirstName(firstName);
        orderPage.setLastName(lastName);
        orderPage.setAddress(address);
        orderPage.setMetroField(station);
        orderPage.setPhoneNumber(phoneNumber);
        orderPage.clickOrderNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.setFieldDate(date);
        rentPage.clickCheckBoxColor("black");
        rentPage.selectRentalPeriod(term);
        rentPage.setDescription(description);
        rentPage.clickOrderButton();
        rentPage.clickPopUpButton("Да");
        rentPage.checkNotificationOfOrder("Заказ оформлен");
        rentPage.clickGetStatusButton();
    }

    @Test
    public void testOrderFunctionalityBySecondButton(){
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickCloseCookie();
        mainPage.clickOrderButttonMiddle();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.setFirstName(firstName);
        orderPage.setLastName(lastName);
        orderPage.setAddress(address);
        orderPage.setMetroField(station);
        orderPage.setPhoneNumber(phoneNumber);
        orderPage.clickOrderNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.setFieldDate(date);
        rentPage.clickCheckBoxColor("grey");
        rentPage.selectRentalPeriod(term);
        rentPage.setDescription(description);
        rentPage.clickOrderButton();
        rentPage.clickPopUpButton("Да");
        rentPage.checkNotificationOfOrder("Заказ оформлен");
        rentPage.clickGetStatusButton();
    }

    @After
    public void closeBrowser () {
        driver.quit();
    }
}
