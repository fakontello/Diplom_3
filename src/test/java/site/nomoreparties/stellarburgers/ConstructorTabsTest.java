package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class ConstructorTabsTest {
    private MainPage openMainPage;
    String password = RandomStringUtils.randomAlphabetic(6);
    String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";


    @Before
    public void preconditions() {
        // Configuration.browser = System.getProperty("browser"); - для запуска в разных браузерах
        openMainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        openMainPage.waitForLoadHomePage();
        // RegistrationPage newRegistration = new RegistrationPage();
        LoginPage loginPage = new LoginPage();

        // регистрация нового пользователя
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginPage();
        loginPage.clickRegistrationButtonOnLoginPage();
//        newRegistration.registerOrderPageFiller(RandomStringUtils.randomAlphabetic(6),
//                email,
//                password);
//        newRegistration.waitForRegistrationButton();
//        newRegistration.clickRegistrationButton();
        loginPage.waitForLoadLoginPage();

        // логин
        loginPage.loginPageFiller(email, password);
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        openMainPage.waitForLoadMainPageAfterLogin();
    }

    @After
    public void postConditions() {
        Selenide.closeWebDriver();
    }

    // переход по разделам Конструктора: Соусы
    @Test
    public void openSauceTab() {
        openMainPage.clickSauceButton();
        openMainPage.waitSaucePageOpen();
    }

    // переход по разделам Конструктора: Начинки
    @Test
    public void openFillersTab() {
        openMainPage.clickFillersButton();
        openMainPage.waitFillersPageOpen();
    }

    // переход по разделам Конструктора: Булки
    @Test
    public void openBunsTab() {
        openMainPage.clickSauceButton();
        openMainPage.waitSaucePageOpen();
        openMainPage.clickBunsButton();
        openMainPage.waitBunsPageOpen();
    }
}
