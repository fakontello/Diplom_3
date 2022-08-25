package site.nomoreparties.stellarburgers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class PrivateOfficeTest {

    private MainPage openMainPage;
    private PrivateOfficePage privateOfficePage;
    String password = RandomStringUtils.randomAlphabetic(6);
    String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";


    @Before
    public void preconditions() {
        openMainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        openMainPage.waitForLoadHomePage();
        RegistrationPage newRegistration = new RegistrationPage();
        LoginPage loginPage = new LoginPage();
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

    @Test
    public void enterPersonalOffice() {
        // переход в личный кабинет
        openMainPage.clickPrivetOfficeButton();
        privateOfficePage.waitPrivateOfficePageLoad();
    }
}
