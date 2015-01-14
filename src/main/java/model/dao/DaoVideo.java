package model.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.bean.Video;

/**
 *
 * @author jeferson
 */
public class DaoVideo {

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public List<Video> getVideos() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Video> videos = new ArrayList<Video>();

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement("SELECT id, name, description FROM video");
            resultSet = ps.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    Video data = new Video();
                    data.setId(resultSet.getInt("id"));
                    data.setName(resultSet.getString("name"));
                    data.setDescription(resultSet.getString("description"));
                    videos.add(data);
                }
                return videos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
                resultSet = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
                ps = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
                connection = null;
            }
        }
        return null;
    }

    public void setVideo(String name, String description) {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.getConnection();

            stmt = connection.createStatement();
            String sql = "INSERT INTO video (NAME, DESCRIPTION) " + "VALUES (\'" + name + "\', \'" + description + "\');";
            stmt.executeUpdate(sql);

            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    public void createTable() {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS video(id SERIAL NOT NULL PRIMARY KEY, name varchar(40) NOT NULL UNIQUE,description varchar(225)");
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeVideo(String name) {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = this.getConnection();

            stmt = connection.createStatement();
            String sql = "DELETE FROM video WHERE name=" + name + ";";
            stmt.executeUpdate(sql);

            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
