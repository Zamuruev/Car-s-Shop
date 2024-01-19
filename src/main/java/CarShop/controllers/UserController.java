package CarShop.controllers;


import CarShop.services.Interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String user(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "/user/users-all";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        userService.deleteAllUsers();
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return "redirect:/user";
    }
}
