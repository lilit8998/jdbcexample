package jdbcexample.managers;

import jdbcexample.db.DBConnectionProvider;
import jdbcexample.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection connection = DBConnectionProvider.getProvider().getConnection();
    public void addUser(User user){

        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO USER(name,surname,email,password)" + " VALUES('%s','%s','%s','%s');",
                    user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
            System.out.println(query);
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
               int id = generatedKeys.getInt(1);
               user.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public List<User> getUsers(){
        String sql = "SELECT * from user";
        List<User> result = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .build();
                result.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public void deleteUser(int id){
        String sql = "DELETE from user where id= " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
