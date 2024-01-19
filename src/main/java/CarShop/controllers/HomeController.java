package CarShop.controllers;

import CarShop.services.Interf.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BrandService brandService;

    @Autowired
    public HomeController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("topBrand", brandService.findTop3ByOOrderByTopTopDesc());
        return "home";
    }
}
