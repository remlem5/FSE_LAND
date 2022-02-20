package dataaccess;

import domain.Site;
import exceptions.DataBaseException;
import exceptions.InvalidValueException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlSiteRepo implements SiteRepo{

    private Connection con;

    public SqlSiteRepo() throws SQLException, ClassNotFoundException {
        this.con = DatabaseConnection.getConnection("jdbc:mysql://localhost:3306/jdbc_meins01", "root", "");
    }

    @Override
    public Optional<Site> insert(Site entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Site> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Site> getAll() {
        String sql = "SELECT * FROM `sites`";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Site> siteList = new ArrayList<>();
            while (resultSet.next()){
                siteList.add(new Site(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("ort"),
                        resultSet.getBoolean("aktiv")
                ));
            }
            return siteList;
        } catch (SQLException e) {
            throw new DataBaseException("Datenbank-Fehler bei getAll()"+ e.getMessage());
        }
    }

    @Override
    public Optional<Site> update(Site entity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Site> findSitesByOrt(String ort) {
        return null;
    }

    @Override
    public List<Site> findSitesByName(String ort) {
        return null;
    }

    @Override
    public List<Site> findAcitveSites() {
        return null;
    }
}
