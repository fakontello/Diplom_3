package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {

    // кнопка "Войти" на странице сброса пароля
    private final SelenideElement resetPassLoginButton = $(byXpath("//*[@id=\"root\"]/div/main/div/div/p/a"));

    // метод клика по кнопке "Войти" на странице сброса пароля
    public void clickResetPassLoginButton() {
        resetPassLoginButton.click();
    }
}
