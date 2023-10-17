package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    SelenideElement mainPageSubtitleText = $x("//h1");
    ElementsCollection exploreButton = $$("#nav-link dropdown-toggle");




    public MainPage openPage() {
        open("");
        mainPageSubtitleText.shouldHave(text("What is your learning goal?"));
        return this;
    }

    public MainPage clickTargetBar(String value) {
        SelenideElement target = $("[click-event-target=" + value + "]");
        target.click();
        return this;
    }
}
