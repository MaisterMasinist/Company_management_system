package hu.progmatic.company_management_system.controllers;

import hu.progmatic.company_management_system.models.ProducedProduct;
import hu.progmatic.company_management_system.models.ProductCondition;
import hu.progmatic.company_management_system.searchform.ProducedProductSearchForm;
import hu.progmatic.company_management_system.services.ProducedProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProducedProductController {

    private final ProducedProductService producedProductService;

    public ProducedProductController(ProducedProductService producedProductService) {
        this.producedProductService = producedProductService;
    }


    @GetMapping(value = {"/producedproducts"})
    public String getProducedProductsPage(Model model) {
        List<ProducedProduct> producedProducts = producedProductService.getAllProduct();
        model.addAttribute("producedProducts", producedProducts);
        model.addAttribute("page", "Products");
        model.addAttribute("form", new ProducedProductSearchForm());
        return "producedproducts";
    }
    @PostMapping("/producedproducts")
    public String displayProducedProiductsSearchResults(ProducedProductSearchForm form, Model model) {
        List<ProducedProduct> producedProducts = producedProductService.getByForm(form);
        model.addAttribute("producedProducts", producedProducts);
        model.addAttribute("page", "Produced Products");
        model.addAttribute("form", form);
        return "producedproducts";
    }

    @GetMapping(value = {"/newproducedproduct"})
    public String getNewProducedProductForm(Model model) {
        ProducedProduct product = new ProducedProduct();

        model.addAttribute("producedProduct", product);
        model.addAttribute("productName", "Produced Product");

        return "new_producedproduct";
    }

    @PostMapping(value = {"/newproducedproduct"})
    public String addNewProducedProduct(ProducedProduct product) {
        producedProductService.saveProduct(product);

        return "redirect:/producedproducts";
    }

}