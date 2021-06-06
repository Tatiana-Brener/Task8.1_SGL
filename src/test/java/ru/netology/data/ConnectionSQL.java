package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnectionSQL {

    @BeforeEach
    void setUp() throws SQLException {
        val faker = new Faker();
        val runner = new QueryRunner();
        val dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?, ?, ?);";

        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app-deadline", "user", "pass"
                );
        ){

//            runner.update(connection, dataSQL, 1, "vasya", "password", "active");
            runner.update(connection, dataSQL, 2, faker.name().username(),"pass", "active");
//            runner.update(connection, dataSQL, faker.name().username(), "pass");
        }
    }

    @Test
    void getDataUsers() throws SQLException {
        String countSQL = "SELECT COUNT(*) FROM users";
        String usersSQL = "SELECT * FROM users;";
        QueryRunner runner = new QueryRunner();

        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app-deadline", "user", "pass"
                );
                ) {
            long count = runner.query(connection, countSQL, new ScalarHandler<>());
            System.out.println(count);

            List<User> all = runner.query(connection, usersSQL, new BeanListHandler<>(User.class));
            System.out.println(all);

        }


    }
}
