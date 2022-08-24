package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class MainPage {

    // кнопка "Личный кабинет"
    private final SelenideElement privetOfficeButton =
            $(byXpath("//*[@id=\"root\"]/div/header/nav/a/p"));

    // метод клика по кнопке "Личный кабинет"
    public void clickPrivetOfficeButton() {
        privetOfficeButton.click();
    }

    // метод ожидания загрузки страницы: проверили видимость кнопки "Личный кабинет"
    public void waitForLoadHomePage() {
        $(byText("Соберите бургер")).shouldBe(visible,ofSeconds(3));
    }
}
