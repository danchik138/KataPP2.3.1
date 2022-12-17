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
        return "users/allUsers";
    }
    @PostMapping
    public String addNewUser(@RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "surname", required = false) String surname,
                             @RequestParam(name = "email", required = false) String email,
                             @RequestParam(name = "gender", required = false) String gender,
                             @RequestParam(name = "age", required = false) int age) {
        System.out.println("adding new user");
        userService.addUser(new User(name, surname, email, gender, age));
        return "redirect:users";
    }

    @PostMapping("/deleteAll")
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        System.out.println("deleted all users");
        return "redirect:/users";
    }
    @PostMapping("/updateUser")
    public String updateUser(@RequestParam(value = "id") long id,
                             @RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "surname", required = false) String surname,
                             @RequestParam(name = "email", required = false) String email,
                             @RequestParam(name = "gender", required = false) String gender,
                             @RequestParam(name = "age", required = false) int age) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users/noSuchUserFound";
        }
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        user.setEmail(email);
        user.setGender(gender);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/noSuchUserFound")
    public String noSuchUserFound(){
        return "users/noSuchUserFound";
    }

    @GetMapping("/{id}")
    public String deleteAllUsers(@PathVariable("id")int id, Model model){
        System.out.println(userService.getUserById(id));
        model.addAttribute("users", userService.getUserById(id));
        return "users/showUser";
    }
}
