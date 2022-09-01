package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ResetPasswordPage {

    // кнопка "Войти" на странице сброса пароля
    private final SelenideElement resetPassLoginButton = $(byXpath("//*[@id=\"root\"]/div/main/div/div/p/a"));

    // метод ожидания страницы сброса пароля
    public void waitResetPasswordPage() {
        $(byXpath("//*[@id=\"root\"]/div/main/div/form/button")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // метод клика по кнопке "Войти" на странице сброса пароля
    public void clickResetPassLoginButton() {
        resetPassLoginButton.click();
    }
}
