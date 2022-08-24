package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class LoginPage {

    // кнопка "Зарегистрироваться" на странице логина
    private final SelenideElement registrationButtonOnLoginPage =
            $(byXpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a"));

    // метод клика по кнопке "Зарегистрироваться" на странице логина
    public void clickRegistrationButtonOnLoginPage() {
        registrationButtonOnLoginPage.click();
    }

    // метод ожидания загрузки страницы: проверили видимость кнопки "Зарегистрироваться"
    public void waitForLoadLoginHomePage() {
        $(byCssSelector("button[class='button_button__33qZ0 button_button_type_primary__1O7Bx " +
                "button_button_size_medium__3zxIa']")).shouldBe(visible,ofSeconds(3));
    }
}
