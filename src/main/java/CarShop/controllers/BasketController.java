package CarShop.controllers;

import CarShop.services.Interf.BasketService;
import CarShop.services.Interf.ModelService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;
    private final ModelService modelService;
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Autowired
    public BasketController(BasketService basketService, ModelService modelService) {
        this.basketService = basketService;
        this.modelService = modelService;
    }

    @GetMapping
    public String basket(Model model, Principal principal) {
        model.addAttribute("models", basketService.modelsInBasket(principal.getName()));
        LOG.log(Logger.Level.INFO, "A user with a login: '" + principal.getName() + "' going to place an order!");
        return "basket";
    }

    @GetMapping("/buy")
    public String buy(Principal principal) {
        basketService.buy(principal.getName());
        return "redirect:/model";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Principal principal) {
        modelService.modelActive(id, principal.getName());
        return "redirect:/basket";
    }
}
