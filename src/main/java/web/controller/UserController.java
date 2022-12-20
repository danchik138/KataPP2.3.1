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
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        System.out.println(userService.getAllUsers());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "users/allUsers";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("user") User user) {
        System.out.println("post method " + user.getName());
        userService.addUser(user);
        return "redirect:/users";
    }

    @PatchMapping
    public String updateUser(@ModelAttribute("user") User user) {
        User temp = userService.getUserById(user.getId());
        if (temp == null) {
            return "/users/noSuchUserFound";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String deleteAllUsers(@PathVariable("id")int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "users/editUser";
    }

    @DeleteMapping("/{id}")
    public String deleteAllUsers(@PathVariable("id")int id) {
        System.out.println("delete by id");
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
