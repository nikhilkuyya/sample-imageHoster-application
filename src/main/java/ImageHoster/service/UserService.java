package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.model.UserProfile;
import ImageHoster.repository.UserRepository;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Call the registerUser() method in the UserRepository class to persist the
    // user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    // Since we did not have any user in the database, therefore the user with
    // username 'upgrad' and password 'password' was hard-coded
    // This method returned true if the username was 'upgrad' and password is
    // 'password'
    // But now let us change the implementation of this method
    // This method receives the User type object
    // Calls the checkUser() method in the Repository passing the username and
    // password which checks the username and password in the database
    // The Repository returns User type object if user with entered username and
    // password exists in the database
    // Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    public boolean isValidUserProfile(UserProfile userProfile) {
        return (userProfile.getFullName() != null && userProfile.getFullName().length() > 0
                && userProfile.getEmailAddress() != null && userProfile.getEmailAddress().length() > 0
                && userProfile.getMobileNumber() != null && userProfile.getMobileNumber().length() > 0);

    }

    public boolean isValidUser(User user) {
        return user.getUsername() != null && user.getUsername().length() > 0 && user.getPassword() != null
                && user.getPassword().length() > 0 && isValidUserProfile(user.getProfile());
    }

    public boolean isValidPassword(String password) {
        Pattern numberPattern = Pattern.compile(".*\\d.*");
        Pattern alphaPattern = Pattern.compile(".*[a-zA-Z].*");
        Pattern symbolPattern = Pattern.compile(".*\\W.*");

        // Pattern pattern = Pattern.compile("((?=.*\\d)((?=.*[a-zA-Z]))(?=.*\\W))");
        return password != null && password.length() >= 3 && numberPattern.matcher(password).matches()
                && alphaPattern.matcher(password).matches() && symbolPattern.matcher(password).matches();
    }

    public boolean isLogginUser(User imageUser, HttpSession session) {
        User logginUser = (User) session.getAttribute("loggeduser");
        if (imageUser != null && logginUser != null) {
            return logginUser.getId() == imageUser.getId();
        } else {
            return false;
        }

    }

}
