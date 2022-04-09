package Homework8;

import java.io.IOException;
import java.sql.*;
import java.util.List;




public class DatabaseRepositorySQLiteImpl extends DatabaseRepository {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


   String DB_PATH = null;
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\n" +
           /* "cityId INTEGER PRIMARY KEY AUTOINCREMENT,\n" +*/
            "cityName TEXT NOT NULL,\n" +
            "ptime TEXT NOT NULL,\n" +
            "minTemp REAL NOT NULL,\n" +
            "maxTemp REAL NOT NULL,\n" +
            ");";
    String insertWeatherQuery = "INSERT INTO weather (cityName, ptime, minTemp, maxTemp) VALUES (?,?,?,?)";

   public DatabaseRepositorySQLiteImpl() {


    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection (DB_PATH);
        return connection;
    }

    private void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean saveWeatherResponse (WeatherResponse weatherResponse) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            /*saveWeather.setString(1, weatherResponse.getCityid());*/
            saveWeather.setString(1, weatherResponse.getCityName());
            saveWeather.setString(2, weatherResponse.getPtime());
            saveWeather.setString(3, weatherResponse.getMinTemp());
            saveWeather.setDouble(4, Double.parseDouble(weatherResponse.getMaxTemp()));
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Failure on saving weather object");
    }

    @Override
    public List<WeatherResponse> getAllSavedData() throws IOException {
        throw new IOException("Not implemented exception");
    }
}
