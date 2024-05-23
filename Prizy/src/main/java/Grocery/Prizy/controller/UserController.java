package Grocery.Prizy.controller;

import Grocery.Prizy.entity.User;
import Grocery.Prizy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register-user";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User registeredUser = userService.registerUser(user);
        model.addAttribute("user", registeredUser);
        return "registration-success";
    }

    @GetMapping("/delete")
    public String showDeleteForm() {
        return "delete-user";
    }

    @PostMapping("/delete")
    public String deleteUserByEmail(@RequestParam String email, Model model) {
        userService.deleteUserByEmail(email);
        model.addAttribute("message", "User with email " + email + " deleted successfully");
        return "delete-success";
    }

    @GetMapping("/find")
    public String showFindForm() {
        return "find-user";
    }

    @GetMapping("/findUser")
    public String getUserByUsername(@RequestParam String username, Model model) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
            return "user-details";
        } else {
            model.addAttribute("message", "User not found");
            return "user-not-found";
        }
    }
}
