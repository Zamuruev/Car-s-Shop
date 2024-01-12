package CarShop.controllers;

import CarShop.models.dtos.BrandDTO;
import CarShop.services.Interf.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String brands(Model model) {
        model.addAttribute("brands", brandService.allBrands());
        return "brand/brands-all";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("infoBrand", brandService.getBrandById(id));
        return "brand/brand-info";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        brandService.deleteAllBrands();
        return "redirect:/brand";
    }

    @ModelAttribute("addBrand")
    public BrandDTO addBrand() {
        return new BrandDTO();
    }

    @GetMapping("/add")
    public String add() {
        return "brand/brand-add";
    }

    @PostMapping("/add")
    public String add(@Valid BrandDTO addBrand,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addBrand", addBrand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addBrand", bindingResult);
            return "redirect:/brand/add";
        }

        brandService.addBrand(addBrand);
        return "redirect:/brand";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable String id) {
        brandService.deleteBrand(id);
        return "redirect:/brand";
    }

}
