package ru.netology.tets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.PersonalAccountPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginUserTest {

    @BeforeEach
    void setUp()  {
        open("http://localhost:9999 ");
    }

    @AfterEach
    public void cleanUp() throws SQLException {
       DataHelper.cleanTables();
    }

    @Test
    void shouldLogin() throws SQLException {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var personalAccountPage = new PersonalAccountPage();
    }

    @Test
    void shouldNotLogin() {
        var loginPage = new LoginPage();
        var invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(invalidAuthInfo);

    }
}
