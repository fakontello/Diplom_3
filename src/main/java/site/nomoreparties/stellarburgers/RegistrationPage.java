package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    // поле "Имя"
    private final SelenideElement nameField =
            $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input"));

    // метод заполнения поля "Имя"
    public void setNameField(String name) {
        nameField.setValue(name);
    }

    // поле "Email"
    private final SelenideElement emailField =
            $(byXpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input"));

    // метод заполнения поля "Имя"
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    // поле "Пароль"
    private final SelenideElement passwordField =
            $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input"));

    // метод заполнения поля "Пароль"
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    // кнопка "Зарегистрироваться"
    private final SelenideElement registrationButton =
            $(byCssSelector("button[class='button_button__33qZ0 "
                    + "button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']"));

    // метод клика по полю "Зарегистрироваться"
    public void clickRegistrationButton() {
        registrationButton.click();
    }

    private final SelenideElement unsuccessfulRegistration = $(byText("Некорректный пароль"));

    public SelenideElement getUnsuccessfulRegistration() {
        return unsuccessfulRegistration;
    }

    // метод заполнения страницы регистрации нового пользователя
    public void RegisterOrderPageFiller(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }
}
