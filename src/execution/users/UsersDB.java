package execution.users;

import java.util.ArrayList;

public class UsersDB {
    private ArrayList<User> users;

    public UsersDB() {
        users = new ArrayList<>();
    }

    public void add(final User user) {
        // Maybe add some checks before?
        users.add(user);
    }

    public User search(final String name, final String password) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkName(final String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
