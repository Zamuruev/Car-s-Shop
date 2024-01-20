package CarShop.controllers;


import CarShop.models.dtos.UserDTO;
import CarShop.services.Interf.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @ModelAttribute("addUser")
    public UserDTO init(){
        return new UserDTO();
    }

    @GetMapping("/add")
    public String add() {
        return "/user/user-add";
    }

    @PostMapping("/add")
    public String add(@Valid UserDTO addUser, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam String role) {
        if (bindingResult.hasErrors() || role.equals("Role")) {
            redirectAttributes.addFlashAttribute("addUser", addUser);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addUser", bindingResult);
            return "redirect:/user/add";
        }
        addUser.setRole(role);
        userService.addUser(addUser);
        return "redirect:/user";
    }
}