package ru.netology.tets;

import com.github.javafaker.Faker;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.PersonalAccountPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginUserTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

//    @AfterEach
//    void cleanUp() throws SQLException {
//        QueryRunner runner = new QueryRunner();
//        String cleanCardsTable = "DELETE FROM cards;";
//        String cleanAuth_CodesTable = "DELETE FROM auth_codes;";
//        try (Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/app-deadline", "user", "pass"
//        );
//        ) {
//            runner.update(connection, cleanCardsTable);
//            runner.update(connection, cleanAuth_CodesTable);
//        }
//    }

    @Test
    void shouldLogin() throws SQLException {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var personalAccountPage = new PersonalAccountPage();


    }


}
