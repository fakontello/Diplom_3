package site.nomoreparties.stellarburgers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    private MainPage openMainPage;
    private RegistrationPage newRegistration;
    private LoginPage loginPage;
    private ResetPasswordPage resetPasswordPage;
    String password = RandomStringUtils.randomAlphabetic(6);
    String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";


    @Before
    public void preconditions() {
        openMainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        openMainPage.waitForLoadHomePage();
        newRegistration = new RegistrationPage();
        loginPage = new LoginPage();
        resetPasswordPage = new ResetPasswordPage();
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
    }

    // тест входа по кнопке «Войти в аккаунт» на главной странице
    @Test
    public void loginFromMainPage() {
        // вход в личный кабинет нового пользователя
        openMainPage.clickHomePageButton();
        openMainPage.waitForLoadHomePage();
        openMainPage.clickLoginAccountButton();
        loginPage.waitForLoadLoginPage();
        loginPage.loginPageFiller(email,
                password);
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        loginPage.clickLoginEnterButton();
        loginPage.clickLoginEnterButton();
        openMainPage.waitForLoadMainPageAfterLogin();
    }

    // тест входа по кнопке "Личный кабинет"
    @Test
    public void loginFromPrivetOfficeButton() {
        // вход в личный кабинет нового пользователя
        openMainPage.clickHomePageButton();
        openMainPage.waitForLoadHomePage();
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginPage();
        loginPage.loginPageFiller(email,
                password);
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        loginPage.clickLoginEnterButton();
        loginPage.clickLoginEnterButton();
        openMainPage.waitForLoadMainPageAfterLogin();
    }

    // тест входа в личный кабинет через кнопку Войти в форме регистрации
    @Test
    public void loginFromRegistrationLoginButton() {
        // вход в личный кабинет нового пользователя
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.waitRegistrationLoginButton();
        newRegistration.clickRegistrationLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.loginPageFiller(email,
                password);
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        openMainPage.waitForLoadMainPageAfterLogin();
    }

    // вход через кнопку в форме восстановления пароля
    @Test
    public void loginFromResetPasswordLoginButton() {
        loginPage.clickResetPasswordButton();
        resetPasswordPage.waitResetPasswordPage();
        resetPasswordPage.clickResetPassLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.loginPageFiller(email,
                password);
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        openMainPage.waitForLoadMainPageAfterLogin();
    }
}
