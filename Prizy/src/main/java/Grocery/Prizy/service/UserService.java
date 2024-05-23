package Grocery.Prizy.service;

import Grocery.Prizy.entity.User;
import Grocery.Prizy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Check if the email is already in use
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email address is already in use");
        }
        // Save the user
        return userRepository.save(user);
    }

    public void deleteUserByEmail(String email) {
        // Check if the user exists
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            throw new RuntimeException("User not found with email: " + email);
        }
        // Delete the user
        userRepository.deleteByEmail(email);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}