package ru.netology.data;

import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
    public static class VerificationCode {
        private String code;
    }
    public static VerificationCode getVerificationCode(AuthInfo authInfo) throws SQLException {
        QueryRunner runner = new QueryRunner();
        val codeSQL = "SELECT code FROM auth_codes;";
//        val codeSQL = "SELECT code FROM auth_codes WHERE max(auth_codes.created);";
//        String codeSQL = "SELECT code FROM auth_codes WHERE created=LAST_DAY(created)";
//        String codeSQL = "SELECT code FROM auth_codes WHERE max(auth_codes.created) and user_id in(SELECT id FROM users WHERE login='vasya');";

        try (
                Connection connection = DriverManager.getConnection (
                        "jdbc:mysql://localhost:3306/app-deadline", "user", "pass"
                )
        ) {
             runner.query(connection, codeSQL, new ScalarHandler<>());
             }
        return new VerificationCode(codeSQL);
             }

}
