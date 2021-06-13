package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VarificationPage {

    private SelenideElement codeField = $("[name=code]");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VarificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public PersonalAccountPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new PersonalAccountPage();
    }
}
