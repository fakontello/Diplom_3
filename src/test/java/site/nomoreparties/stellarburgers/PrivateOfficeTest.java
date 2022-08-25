package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class PrivateOfficeTest {

    private MainPage openMainPage;
    private PrivateOfficePage privateOfficePage;
    private LoginPage loginPage;
    String password = RandomStringUtils.randomAlphabetic(6);
    String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";


    @Before
    public void preconditions() {
        openMainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        openMainPage.waitForLoadHomePage();
        RegistrationPage newRegistration = new RegistrationPage();
        loginPage = new LoginPage();
        privateOfficePage = new PrivateOfficePage();

        // регистрация нового пользователя
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginPage();
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.RegisterOrderPageFiller(RandomStringUtils.randomAlphabetic(6),
                email,
                password);
        newRegistration.waitForRegistrationButton();
        newRegistration.clickRegistrationButton();
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

    // переход в личный кабинет
    @Test
    public void enterPersonalOffice() {
        openMainPage.clickPrivetOfficeButton();
        privateOfficePage.waitPrivateOfficePageLoad();
    }

    // переход из личного кабинета в конструктор
    @Test
    public void enterConstructor() {
        openMainPage.clickPrivetOfficeButton();
        privateOfficePage.waitPrivateOfficePageLoad();
        privateOfficePage.clickConstructorButton();
        openMainPage.waitForLoadMainPageAfterLogin();
    }

    // выход из личного кабинета
    @Test
    public void exitPrivateOffice() {
        openMainPage.clickPrivetOfficeButton();
        privateOfficePage.waitPrivateOfficePageLoad();
        privateOfficePage.clickExitButton();
        loginPage.waitForLoadLoginPage();
    }

}
