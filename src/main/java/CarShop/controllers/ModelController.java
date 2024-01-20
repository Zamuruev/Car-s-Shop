package CarShop.controllers;

import CarShop.models.dtos.BrandDTO;
import CarShop.models.dtos.ModelDTO;
import CarShop.models.enums.Category;
import CarShop.models.enums.Engine;
import CarShop.models.enums.Transmission;
import CarShop.services.Interf.BrandService;
import CarShop.services.Interf.ModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/model")
public class ModelController {

    private final ModelService modelService;
    private final BrandService brandService;

    @Autowired
    public ModelController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping
    public String models(Model model) {
        model.addAttribute("models", modelService.allModelsNotBasket());
        model.addAttribute("brands", brandService.allBrands());
        return "/model/models-all";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable String id) {
        modelService.deleteModel(id);
        return "redirect:/model";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("detailsModel", modelService.getModelById(id));
        System.out.println(modelService.getModelById(id));
        return "/model/model-info";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        modelService.deleteAllModels();
        return "redirect:/model";
    }

    @GetMapping("/deleteAllByActive")
    public String deleteAllByActive() {
        modelService.deleteAllModelsByActive();
        return "redirect:/model";
    }

    @ModelAttribute("addModel")
    public ModelDTO init() {
        return new ModelDTO();
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("brands", brandService.allBrands());
        return "model/model-add";
    }

    @PostMapping("/add")
    public String add(@Valid ModelDTO addModel,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      @RequestParam String nameBrand, @RequestParam String transmission,
                      @RequestParam String category, @RequestParam String engine) {
        if(bindingResult.hasErrors() || transmission == "Transmission" || engine == "Engine" || category == "Category" || nameBrand == "Brand") {
            redirectAttributes.addFlashAttribute("addModel", addModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addModel", bindingResult);
            return "redirect:/model/add";
        }
        System.out.println(nameBrand);
        addModel.setBrand(brandService.getBrandByName(nameBrand));
        addModel.setCategory(Category.valueOf(category));
        addModel.setEngine(Engine.valueOf(engine));
        addModel.setTransmission(Transmission.valueOf(transmission));
        System.out.println(addModel);
        modelService.addModel(addModel);
        return "redirect:/model";
    }
    @GetMapping("/modelActive/{id}")
    public String modelActive(@PathVariable String id, Principal principal) {
        modelService.modelActive(id, principal.getName());
        return "redirect:/basket";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable String id) {
        model.addAttribute("updateModel", modelService.getModelById(id));
        model.addAttribute("brands", brandService.allBrands());
        return "/model/model-update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid ModelDTO updateModel, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, @RequestParam String nameBrand, @RequestParam String transmission,
                         @RequestParam String category, @RequestParam String engine) {
        if(bindingResult.hasErrors() || transmission == "Transmission" || engine == "Engine" || category == "Category" || nameBrand == "Brand" || nameBrand.isEmpty()) {
            redirectAttributes.addFlashAttribute("updateModel", updateModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateModel", bindingResult);
            return "redirect:/model/update/{id}";
        }
        BrandDTO brandDTO = brandService.getBrandByName(nameBrand);
        updateModel.setBrand(brandDTO);
        updateModel.setTransmission(Transmission.valueOf(transmission));
        updateModel.setCategory(Category.valueOf(category));
        updateModel.setEngine(Engine.valueOf(engine));
        System.out.println(updateModel);
        modelService.updateModel(updateModel);
        return "redirect:/model/details/{id}";
    }

}
