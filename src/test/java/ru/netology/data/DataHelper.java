package ru.netology.data;

import com.mysql.cj.jdbc.ConnectionImpl;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;

import static com.codeborne.selenide.Selenide.open;


public class DataHelper {
    public DataHelper() {
    }

    @Value
    public static class CleaningTables {
    }

    public static void cleanTables() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String cleanCardsTable = "DELETE FROM cards;";
        String cleanAuth_CodesTable = "DELETE FROM auth_codes;";
        String cleanUsersTable = "DELETE FROM users;";
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app-deadline", "user", "pass"
                )
        ) {
            runner.update(connection, cleanCardsTable);
            runner.update(connection, cleanAuth_CodesTable);
            runner.update(connection, cleanUsersTable);
        }
    }

    @Value
    public static class RestartSUT {}

    public static void restartSUT() throws  SQLException {
        try {
            open("http://localhost:9999");
        } catch (SQLException e) {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/app-deadline", "user", "pass");
        }

    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

//    public static AuthInfo getAuthInfo() throws SQLException {
//        try {
//            return new AuthInfo("vasya", "qwerty123");
//        } catch (SQLException e) {
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/app-deadline", "user", "pass");
//        }
//    }

    public static AuthInfo getAuthInfo() throws SQLException {
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
