package jdbcexample;

import jdbcexample.db.DBConnectionProvider;
import jdbcexample.managers.UserManager;
import jdbcexample.model.User;

import java.util.List;

public class JdbcExample {

        public static void main(String[] args) {
            UserManager userManager = new UserManager();

            User user = User.builder()
                    .name("Poxos")
                    .surname("Poxosyan")
                    .email("poxos@gmail.com")
                    .password("poxos")
                    .build();
            System.out.println("Before:" + user);
            userManager.addUser(user);
            System.out.println("After" + user);

//           List<User> users =  userManager.getUsers();
//            for (User user: users) {
//                System.out.println(user);
//            }
//
//            userManager.deleteUser(1);
//            users = userManager.getUsers();
//            for (User user:users) {
//
//            }
        }




    }

