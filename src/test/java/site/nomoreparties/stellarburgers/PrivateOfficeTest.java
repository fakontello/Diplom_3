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

public class PrivateOfficeTest {

    private MainPage openMainPage;
    private PrivateOfficePage privateOfficePage;
    private LoginPage loginPage;
    private BurgersApiUserClient client;
    private User newUser;


    @Before
    public void preconditions() {
        // Configuration.browser = System.getProperty("browser"); - для запуска в разных браузерах

        loginPage = new LoginPage();
        privateOfficePage = new PrivateOfficePage();
        client = new BurgersApiUserClient();
        newUser = getRandomUser();

        // регистрация нового пользователя
        Response responseCreate = client.createUser(newUser);
        assertEquals(SC_OK, responseCreate.statusCode());
        String responseSuccess = responseCreate.body().jsonPath().getString("success");
        MatcherAssert.assertThat(responseSuccess, true);

        // открытие главной страницы после создания и логина пользователя
        openMainPage = open(mainPageUrl, MainPage.class);
        openMainPage.waitForLoadHomePage();

        // логин
        openMainPage.clickLoginAccountButton();
        loginPage.waitForLoadLoginPage();
        loginPage.loginPageFiller(newUser.getEmail(), newUser.getPassword());
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        openMainPage.waitForLoadMainPageAfterLogin();
    }

    @After
    public void postConditions() {
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
