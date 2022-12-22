package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
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

public class ConstructorTabsTest {

    private MainPage openMainPage;
    private BurgersApiUserClient client;
    private User newUser;

    @Before
    public void preconditions() {
        // Configuration.browser = System.getProperty("browser"); - для запуска в разных браузерах
        client = new BurgersApiUserClient();
        newUser = getRandomUser();

        // регистрация нового пользователя
        Response responseCreate = client.createUser(newUser);
        assertEquals(SC_OK, responseCreate.statusCode());
        String responseSuccess = responseCreate.body().jsonPath().getString("success");
        MatcherAssert.assertThat(responseSuccess, true);

        // логин
        User existingUser = new User(newUser.getName(), newUser.getEmail(), newUser.getPassword());
        Response responseLogin = client.loginUser(existingUser);
        assertEquals(SC_OK, responseLogin.statusCode());
        String responseLoginSuccess = responseLogin.body().jsonPath().getString("success");
        MatcherAssert.assertThat(responseLoginSuccess, true);

        // открытие главной страницы после создания и логина пользователя
        openMainPage = open(mainPageUrl, MainPage.class);
        openMainPage.waitForLoadHomePage();
    }

    @After
    public void postConditions() {
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
