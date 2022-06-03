package database;

import model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements DAO<Country> {
    @Override
    public void create(Country country) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into countries values (?,?,?,?)")) {
            preparedStatement.setInt(1, country.getId());
            preparedStatement.setString(2, country.getName());
            preparedStatement.setString(3, country.getCode());
            preparedStatement.setInt(4, country.getContinent());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Country> getAll() {
        Connection connection = Database.getConnection();
        List<Country> countryList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from countries")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countryList.add(new Country(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;
    }

    @Override
    public Country getByName(String name) {
        Connection connection = Database.getConnection();
        Country country = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from countries where name like '" + name + "'")) {
            country = getCountry(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public Country getById(int id) {
        Connection connection = Database.getConnection();
        Country country = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from countries where id = " + id)) {
            country = getCountry(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    private Country getCountry(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String countryName = resultSet.getString("name");
            int countryId = resultSet.getInt("id");
            int countryContinent = resultSet.getInt("continent");
            String countryCode = resultSet.getString("code");
            return new Country(countryId, countryName, countryCode, countryContinent);
        }
        return null;
    }

    @Override
    public void deleteEntry(int id) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from countries where id = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntry(Country country) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "update countries set name = ?, code = ?, continent = ? where id = ?")) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(2, country.getCode());
            preparedStatement.setInt(3, country.getContinent());
            preparedStatement.setInt(4, country.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
