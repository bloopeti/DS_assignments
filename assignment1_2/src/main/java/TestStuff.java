import dataAccess.dao.UserDAO;
import dataAccess.model.User;

public class TestStuff {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        User user1 = new User();
        user1.setUsername("dummy");
        user1.setPass("dummy");
        user1.setIsAdmin(0);
        User user = userDAO.createUser(user1);
//
////        User user = new User();
////        user.setUsername("admin");
////        user.setPass("admin");
////        user.setIsAdmin(1);
////        userDAO.createUser(user);
//
//        System.out.print(user.getUsername());
//        System.out.print(user.getPass());
//        System.out.print(user.getIsAdmin());
//        System.out.print(user.getId());
    }
}
