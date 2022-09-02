package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static site.nomoreparties.stellarburgers.Urls.mainPageUrl;
import static site.nomoreparties.stellarburgers.User.getRandomUser;

public class LoginTest {

    private MainPage openMainPage;
    private RegistrationPage newRegistration;
    private LoginPage loginPage;
    private ResetPasswordPage resetPasswordPage;
    private BurgersApiUserClient client;
    private User newUser;


    @Before
    public void preconditions() {
        // Configuration.browser = System.getProperty("browser"); - для запуска в разных браузерах
        client = new BurgersApiUserClient();
        newUser = getRandomUser();
        loginPage = new LoginPage();
        resetPasswordPage = new ResetPasswordPage();
        newRegistration = new RegistrationPage();

        // регистрация нового пользователя
        Response responseCreate = client.createUser(newUser);
        assertEquals(SC_OK, responseCreate.statusCode());
        String responseSuccess = responseCreate.body().jsonPath().getString("success");
        MatcherAssert.assertThat(responseSuccess, true);

        // открытие главной страницы после создания пользователя
        openMainPage = open(mainPageUrl, MainPage.class);
        openMainPage.waitForLoadHomePage();
    }

    @After
    public void postConditions() {
        // логин
        loginPage.waitForLoadLoginPage();
        loginPage.loginPageFiller(newUser.getEmail(), newUser.getPassword());
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        openMainPage.waitForLoadMainPageAfterLogin();

        // удаление пользователя
        User existingUser = new User(newUser.getName(), newUser.getEmail(), newUser.getPassword());
        Response responseLogin = client.loginUser(existingUser);
        String accessToken = responseLogin.body().jsonPath().getString("accessToken");
        assertEquals(SC_OK, responseLogin.statusCode());
        Response responseDeleteUser = client.deleteUser(accessToken);
        assertEquals(SC_ACCEPTED, responseDeleteUser.statusCode());
        String responseMessage = responseDeleteUser.body().jsonPath().getString("message");
        assertEquals(responseMessage, "User successfully removed");
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
