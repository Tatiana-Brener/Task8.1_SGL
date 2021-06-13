package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PersonalAccountPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public PersonalAccountPage() {
        heading.shouldBe(Condition.visible);
    }
}
