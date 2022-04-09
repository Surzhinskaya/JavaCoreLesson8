package Homework8;
import org.sqlite.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Lesson8 {


        public static void main(String[] args) throws IOException {

            try {
                DatabaseRepositorySQLiteImpl DatabaseRepositorySQLiteImpl = new DatabaseRepositorySQLiteImpl();

            } catch (SQLException e) {
                e.printStackTrace();


            }


            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название города");
            String cityName = scanner.nextLine();
            System.out.println("Вы ввели город " + cityName);


            String cityId = null;
            try {
                cityId = RequestSender.getCityId(cityName);
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println("Сity: " + cityName + RequestSender.getMinAndMaxTemp(cityId));


        }

        public static class DatabaseRepositorySQLiteImpl {
            private static final String DB_PATH = "jdbc:sqlite:Weather.db";
            private Connection connection;


            public DatabaseRepositorySQLiteImpl() throws SQLException {
                DriverManager.registerDriver(new JDBC());
                this.connection = DriverManager.getConnection(DB_PATH);
            }
        }
    }


