package database;

import model.Continent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ContinentDAO implements DAO<Continent> {
    @Override
    public void create(Continent continent) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into continents values (?,?,?,?)")) {
            preparedStatement.setInt(1, continent.getId());
            preparedStatement.setString(2, continent.getName());
            if (continent.getArea() > 0) {
                preparedStatement.setLong(3, continent.getArea());
            } else {
                preparedStatement.setNull(3, Types.NUMERIC);
            }
            if (continent.getPopulation() > 0) {
                preparedStatement.setLong(4, continent.getPopulation());
            } else {
                preparedStatement.setNull(4, Types.NUMERIC);
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Continent> getAll() {
        Connection connection = Database.getConnection();
        List<Continent> continentList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from continents")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                continentList.add(new Continent(resultSet.getInt(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getLong(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return continentList;
    }

    @Override
    public Continent getByName(String name) {
        Connection connection = Database.getConnection();
        Continent continent = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from continents where name like '" + name + "'")) {
            continent = getContinent(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return continent;
    }

    private Continent getContinent(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String continentName = resultSet.getString("name");
            int continentId = resultSet.getInt("id"); //rs.getInt(1)
            long continentArea = resultSet.getLong("area");
            long continentPopulation = resultSet.getLong("population");
            return new Continent(continentId, continentName, continentArea, continentPopulation);
        }
        return null;
    }

    @Override
    public Continent getById(int id) {
        Connection connection = Database.getConnection();
        Continent continent = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from continents where id =" + id)) {
            continent = getContinent(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return continent;
    }

    @Override
    public void deleteEntry(int id) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from continents where id =" + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntry(Continent continent) {
        Connection connection = Database.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "update continents set name = ?, area = ?, population = ? where id = ?")) {
            preparedStatement.setString(1, continent.getName());
            preparedStatement.setLong(2, continent.getArea());
            preparedStatement.setLong(3, continent.getPopulation());
            preparedStatement.setInt(4, continent.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}