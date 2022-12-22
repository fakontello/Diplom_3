package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static site.nomoreparties.stellarburgers.Urls.mainPageUrl;

public class UnsuccessfullyRegistrationTest {

    private LoginPage loginPage;
    private RegistrationPage newRegistration;
    private User newUser;
    private BurgersApiUserClient client;

    @Before
    public void preconditions() {
        // Configuration.browser = System.getProperty("browser"); - для запуска в разных браузерах
        MainPage openMainPage = open(mainPageUrl, MainPage.class);
        openMainPage.waitForLoadHomePage();
        client = new BurgersApiUserClient();
        newRegistration = new RegistrationPage();
        loginPage = new LoginPage();
        newUser = new User(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(6) + "@yandex.ru",
                RandomStringUtils.randomAlphabetic(5));
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginPage();
    }

    @After
    public void postConditions() {
        Selenide.closeWebDriver();
    }

    // Тест на не успешную регистрацию нового пользователя, пароль меньше 6 символов
    @Test
    public void newNegativeRegistration() {
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.registerOrderPageFiller
                (newUser.getName(), newUser.getEmail(), newUser.getPassword());
        newRegistration.clickRegistrationButton();
        newRegistration.getUnsuccessfulRegistration().shouldHave(Condition.exactText("Некорректный пароль"));

        User existingUser = new User(newUser.getName(), newUser.getEmail(), newUser.getPassword());
        Response responseLogin = client.loginUser(existingUser);
        assertEquals(SC_UNAUTHORIZED, responseLogin.statusCode());

        // Добавил проверку что если вдруг обязательное поле заполнится то созданный юзер удалится
        if (responseLogin.statusCode() == SC_OK) {
            User newSuccessUser = new User(newUser.getName(), newUser.getEmail(), newUser.getPassword());
            Response responseSuccessLogin = client.loginUser(newSuccessUser);
            String accessToken = responseSuccessLogin.body().jsonPath().getString("accessToken");
            assertEquals(SC_OK, responseSuccessLogin.statusCode());
            Response responseDeleteUser = client.deleteUser(accessToken);
            assertEquals(SC_ACCEPTED, responseDeleteUser.statusCode());
            String responseMessage = responseDeleteUser.body().jsonPath().getString("message");
            assertEquals(responseMessage, "User successfully removed");
        }
    }
}
