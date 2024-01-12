package CarShop.controllers;

import CarShop.services.Interf.ModelService;
import CarShop.services.Interf.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public String offers(Model model, Principal principal) {
        model.addAttribute("offers", offerService.offers(principal.getName()));
        return "/offer/offers-all";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model, Principal principal) {
        model.addAttribute("models", offerService.modelsInOffer(id));
        model.addAttribute("idOffer", id);
        model.addAttribute("user", principal.getName());
        return "/offer/offer-info";
    }
}
