package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PrivateOfficePage {

    // метод ожидания загрузки страницы Личного кабинета
    public void waitPrivateOfficePageLoad() {
        $(byXpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // кнопка Конструктор
    private final SelenideElement constructorButton = $(byXpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p"));

    // метод клика по кнопке Конструктор
    public void clickConstructorButton() {
        constructorButton.click();
    }

    // кнопка Выйти
    private final SelenideElement exitButton = $(byXpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button"));

    // метод клика по кнопке Выйти
    public void clickExitButton() {
        exitButton.click();
    }
}
