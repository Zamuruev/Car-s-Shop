package CarShop.controllers;

import CarShop.models.dtos.UserDTO;
import CarShop.services.Interf.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegister")
    public UserDTO init() {
        return new UserDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDTO userRegister,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegister", userRegister);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister", bindingResult);

            return "redirect:/register";
        }

        userService.registerUser(userRegister);
        return "redirect:/login";
    }
}
