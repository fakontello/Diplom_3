package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // кнопка "Зарегистрироваться" на странице логина
    private final SelenideElement registrationButtonOnLoginPage = $(byClassName("Auth_link__1fOlj"));

    // метод клика по кнопке "Зарегистрироваться" на странице логина
    public void clickRegistrationButtonOnLoginPage() {
        registrationButtonOnLoginPage.click();
    }
}
