package sample;

import org.mano.jdbc.examples.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller implements DAO {

    private static Recipes createRecipe(ResultSet rs) throws SQLException {
        Recipes r = new Recipes();
        try {
            if(rs.isBeforeFirst()){
                rs.next();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
            }else{
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
            }

        } catch (SQLException ex) {
        }
        return r;
    }

    private static Foods createFood(ResultSet rs) throws SQLException {
        Foods r = new Foods();
        try {

            if(rs.isBeforeFirst()){
                rs.next();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setNumber(rs.getInt("number"));
            }else{
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setNumber(rs.getInt("number"));
            }

        } catch (SQLException ex) {
        }
        return r;
    }

    private static User createUser(ResultSet rs) throws SQLException {
        User r = new User();
        try {

            if(rs.isBeforeFirst()){
                rs.next();
                r.setId(rs.getInt("id"));
                r.setEmail(rs.getString("email"));
                r.setPassword(rs.getString("password"));
            }else{
                r.setId(rs.getInt("id"));
                r.setEmail(rs.getString("email"));
                r.setPassword(rs.getString("password"));
            }

        } catch (SQLException ex) {
        }
        return r;
    }

    public static Foods getFood(int idFood){
        String sql = "Select * from foods where id like '%" +
                idFood + "%'";

        Foods f = new Foods();

        try {

            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            f = createFood(rs);

            rs.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
        }
        return f;
    }

    public static Recipes getRecipe(int idRecipe){
        String sql = "Select * from recipes where id like '%" +
                idRecipe + "%'";

        Recipes rec = new Recipes();

        try {

            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rec = createRecipe(rs);

            rs.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
        }
        return rec;
    }

    public static User getUser(String userEmail){
        String sql = "Select * from persons where email like '%" +
                userEmail + "%'";

        User user = new User();

        try {

            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            user = createUser(rs);

            rs.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
        }
        return user;
    }

    public static List<Recipes> getRecipes() {
        String sql = "Select * from recipes order by name";
        List<Recipes> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Recipes p = createRecipe(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return list;
    }

    public static List<Foods> getFoodsForRecipe(int idRecipe) {
        String sql = "Select * from recipes_foods where recipes_id like '%" +
                idRecipe + "%'";
        List<Foods> list = new ArrayList<>();

        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Foods f = getFood(rs.getInt("foods_id"));
                list.add(f);
            }

            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return list;
    }
}
