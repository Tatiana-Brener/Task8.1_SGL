package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[name=login]");
    private SelenideElement passwordField = $("[name=password]");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement mistakeField = $("[data-test-id=error-notification]");

    public VarificationPage validLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VarificationPage();
    }
    public void invalidLogin(DataHelper.IncorrectAuthInfo incorrectAuthInfo) {
        loginField.setValue(incorrectAuthInfo.getLogin());
        passwordField.setValue(incorrectAuthInfo.getIncorrectPassword());
        loginButton.click();
        mistakeField.shouldBe(Condition.visible);
    }
}
