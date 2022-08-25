package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {

    private RegistrationPage newRegistration;
    private LoginPage loginPage;

    @Before
    public void preconditions() {
        MainPage openMainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        openMainPage.waitForLoadHomePage();
        newRegistration = new RegistrationPage();
        loginPage = new LoginPage();
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginPage();
    }

    @After
    public void postConditions() {
        Selenide.closeWebDriver();
    }

    // Тест на успешную регистрацию нового пользователя
    @Test
    public void newPositiveRegistration() {
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.RegisterOrderPageFiller(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(6) + "@yandex.ru",
                RandomStringUtils.randomAlphabetic(6));
        newRegistration.clickRegistrationButton();
        loginPage.waitForLoadLoginPage();
    }

    // Тест на не успешную регистрацию нового пользователя, пароль меньше 6 символов
    @Test
    public void newNegativeRegistration() {
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.RegisterOrderPageFiller(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(6) + "@yandex.ru",
                RandomStringUtils.randomAlphabetic(5));
        newRegistration.clickRegistrationButton();
        newRegistration.getUnsuccessfulRegistration().shouldHave(Condition.exactText("Некорректный пароль"));
    }
}
