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

    @RequestMapping(method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "users/allUsers";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id")int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "users/editUser";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String updateUser(@ModelAttribute("user") User user) {
        User temp = userService.getUserById(user.getId());
        if (temp == null) {
            return "redirect:/users";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        return "redirect:/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable("id")int id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}