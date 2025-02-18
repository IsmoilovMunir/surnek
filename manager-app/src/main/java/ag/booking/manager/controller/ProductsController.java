package ag.booking.manager.controller;

import ag.booking.manager.client.BadRequestException;
import ag.booking.manager.client.ProductsRestClient;
import ag.booking.manager.entity.Product;
import ag.booking.manager.payload.NewProductPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

@RequestMapping("catalogue/products")

public class ProductsController {
    private final ProductsRestClient productsRestClient;

    public ProductsController(ProductsRestClient productsRestClient) {
        this.productsRestClient = productsRestClient;
    }


    @GetMapping("list")
    public String getProductList(Model model) {
        model.addAttribute("products", this.productsRestClient.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload productPayload,
                                Model model) {
        try {
            Product product = this.productsRestClient.createProduct(productPayload.title(), productPayload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", productPayload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        }
    }


}
