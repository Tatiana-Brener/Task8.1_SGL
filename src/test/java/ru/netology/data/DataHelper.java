package ru.netology.data;

import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataHelper {
    public DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class IncorrectAuthInfo {
        private String login;
        private String incorrectPassword;
    }
    public static IncorrectAuthInfo getInvalidAuthInfo() {

        return new IncorrectAuthInfo("vasya", "jhf4hj4n");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }
    public static VerificationCode getVerificationCode(AuthInfo authInfo) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String codeSQL = "SELECT code FROM auth_codes WHERE created=(SELECT MAX(created) FROM auth_codes);";
        String code;
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app-deadline", "user", "pass"
                )
        ) {
            code = runner.query(connection, codeSQL, new ScalarHandler<>("code"));
        }
        return new VerificationCode(code);
    }

}
