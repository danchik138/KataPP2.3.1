package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String showAllUsers(Model model) {
        System.out.println(userService.getAllUsers());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "users/allUsers";
    }
    @PostMapping("/createUser")
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/deleteAll")
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        System.out.println("deleted all users");
        return "redirect:/users";
    }
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        User temp = userService.getUserById(user.getId());
        if (temp == null) {
            return "redirect:/users/noSuchUserFound";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/noSuchUserFound")
    public String noSuchUserFound(){
        return "users/noSuchUserFound";
    }

    @GetMapping("/{id}")
    public String deleteAllUsers(@PathVariable("id")int id, Model model){
        model.addAttribute("users", userService.getUserById(id));
        return "users/showUser";
    }
}
