package Homework8;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;




    public abstract class DatabaseRepository {

        public abstract boolean saveWeatherResponse(WeatherResponse weatherResponse) throws SQLException;

        public abstract List<WeatherResponse> getAllSavedData() throws IOException;
    }

