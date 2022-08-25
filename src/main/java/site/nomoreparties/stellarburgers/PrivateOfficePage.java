package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PrivateOfficePage {

    // метод ожидания загрузки страницы Личного кабинета
    public void waitPrivateOfficePageLoad() {
        $(byXpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }
}
