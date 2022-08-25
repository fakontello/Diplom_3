package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
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
        Configuration.browser = System.getProperty("browser"); // для запуска в разных браузерах
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

    @After
    public void postConditions() {
        loginPage.waitForLoadLoginPage();
        loginPage.loginPageFiller(email, password);
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        openMainPage.waitForLoadMainPageAfterLogin();
        Selenide.closeWebDriver();
    }

    // тест входа по кнопке «Войти в аккаунт» на главной странице
    @Test
    public void loginFromMainPage() {
        openMainPage.clickHomePageButton(); // вход по кнопке Stellar Burgers
        openMainPage.waitForLoadHomePage();
        openMainPage.clickLoginAccountButton();
    }

    // тест входа по кнопке "Личный кабинет"
    @Test
    public void loginFromPrivetOfficeButton() {
        openMainPage.clickHomePageButton(); // вход по кнопке Stellar Burgers
        openMainPage.waitForLoadHomePage();
        openMainPage.clickPrivetOfficeButton();
    }

    // тест входа в личный кабинет через кнопку Войти в форме регистрации
    @Test
    public void loginFromRegistrationLoginButton() {
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.waitRegistrationLoginButton();
        newRegistration.clickRegistrationLoginButton();
    }

    // вход через кнопку Войти в форме восстановления пароля
    @Test
    public void loginFromResetPasswordLoginButton() {
        loginPage.clickResetPasswordButton();
        resetPasswordPage.waitResetPasswordPage();
        resetPasswordPage.clickResetPassLoginButton();
    }
}
