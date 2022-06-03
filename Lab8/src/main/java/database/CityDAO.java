package database;

import com.opencsv.CSVReader;
import model.City;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements DAO<City> {
    @Override
    public void create(City city) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into cities values (?,?,?,?,?,?)")) {
            preparedStatement.setInt(1, city.getId());
            preparedStatement.setString(2, city.getName());
            preparedStatement.setInt(3, city.getCountry());
            preparedStatement.setInt(4, city.getCapital());
            preparedStatement.setDouble(5, city.getLatitude());
            preparedStatement.setDouble(6, city.getLongitude());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<City> getAll() {
        Connection connection = Database.getConnection();
        List<City> cityList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from cities")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cityList.add(new City(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getDouble(5),
                        resultSet.getDouble(6)));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public City getByName(String name) {
        Connection connection = Database.getConnection();
        City city = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from cities where name like '" + name + "'")) {
            city = getCity(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    private City getCity(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int cityId = resultSet.getInt("id");
            String cityName = resultSet.getString("name");
            int cityCountry = resultSet.getInt("country");
            int cityCapital = resultSet.getInt("capital");
            double cityLatitude = resultSet.getDouble("latitude");
            double cityLongitude = resultSet.getDouble("longitude");
            return new City(cityId, cityName, cityCountry, cityCapital, cityLatitude, cityLongitude);
        }
        resultSet.close();
        return null;
    }

    @Override
    public City getById(int id) {
        Connection connection = Database.getConnection();
        City city = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from cities where id = " + id)) {
            city = getCity(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }


    @Override
    public void deleteEntry(int id) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from cities where id = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntry(City city) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "update cities set name = ?, country = ?, capital = ?, latitude= ?, longitude= ? where id = ?")) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getCountry());
            preparedStatement.setInt(3, city.getCapital());
            preparedStatement.setDouble(4, city.getLatitude());
            preparedStatement.setDouble(5, city.getLongitude());
            preparedStatement.setInt(6, city.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
