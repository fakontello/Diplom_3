package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PrivateOfficePage {
    // кнопка Конструктор
    private final SelenideElement constructorButton = $(byXpath("//p[text()='Конструктор']"));

    // кнопка Выйти
    private final SelenideElement exitButton = $(byXpath("//button[text()='Выйти']"));

    // метод ожидания загрузки страницы Личного кабинета
    public void waitPrivateOfficePageLoad() {
        $(byXpath("//a[text()='Профиль']")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // метод клика по кнопке Конструктор
    public void clickConstructorButton() {
        constructorButton.click();
    }

    // метод клика по кнопке Выйти
    public void clickExitButton() {
        exitButton.click();
    }
}
