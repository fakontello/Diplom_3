package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {

    private MainPage openMainPage;
    private RegistrationPage newRegistration;
    private LoginPage loginPage;

    @Before
    public void preconditions() {
        openMainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        openMainPage.waitForLoadHomePage();
        newRegistration = new RegistrationPage();
        loginPage = new LoginPage();
    }

    // Тест на успешную регистрацию нового пользователя
    @Test
    public void newPositiveRegistration() {
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginHomePage();
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.RegisterOrderPageFiller(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(6) + "@yandex.ru",
                RandomStringUtils.randomAlphabetic(6));
        newRegistration.clickRegistrationButton();
    }

    // Тест на не успешную регистрацию нового пользователя, пароль меньше 6 символов
    @Test
    public void newNegativeRegistration() {
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginHomePage();
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.RegisterOrderPageFiller(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(6) + "@yandex.ru",
                RandomStringUtils.randomAlphabetic(5));
        newRegistration.clickRegistrationButton();
        newRegistration.getUnsuccessfulRegistration().shouldHave(Condition.exactText("Некорректный пароль"));
    }
}
