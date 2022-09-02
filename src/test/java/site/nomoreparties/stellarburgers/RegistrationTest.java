package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static site.nomoreparties.stellarburgers.Urls.mainPageUrl;

public class RegistrationTest {

    private RegistrationPage newRegistration;
    private LoginPage loginPage;
    private BurgersApiUserClient client;
    private User newUser;

    @Before
    public void preconditions() {
        // Configuration.browser = System.getProperty("browser"); - для запуска в разных браузерах
        MainPage openMainPage = open(mainPageUrl, MainPage.class);
        openMainPage.waitForLoadHomePage();
        newUser = new User(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(6) + "@yandex.ru",
                RandomStringUtils.randomAlphabetic(6));
        newRegistration = new RegistrationPage();
        loginPage = new LoginPage();
        client = new BurgersApiUserClient();
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginPage();
    }

    @After
    public void postConditions() {
        User existingUser = new User
                (newUser.getName(), newUser.getEmail(), newUser.getPassword());
        Response responseLogin = client.loginUser(existingUser);
        String accessToken = responseLogin.body().jsonPath().getString("accessToken");
        assertEquals(SC_OK, responseLogin.statusCode());
        Response responseDeleteUser = client.deleteUser(accessToken);
        assertEquals(SC_ACCEPTED, responseDeleteUser.statusCode());
        String responseMessage = responseDeleteUser.body().jsonPath().getString("message");
        assertEquals(responseMessage, "User successfully removed");
        Selenide.closeWebDriver();
    }

    // Тест на успешную регистрацию нового пользователя
    @Test
    public void newPositiveRegistration() {
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.registerOrderPageFiller
                (newUser.getName(), newUser.getEmail(), newUser.getPassword());
        newRegistration.clickRegistrationButton();
        loginPage.waitForLoadLoginPage();
    }

}
