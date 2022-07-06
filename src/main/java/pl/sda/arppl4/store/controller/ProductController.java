package pl.sda.arppl4.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.store.model.Product;
import pl.sda.arppl4.store.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;

    // READ
    @GetMapping("/list")
    public List<Product> getAllProducts() {
        log.info("Wywołano listę produktów.");

        List<Product> list = productService.getAllProducts();
        return list;
    }

    // CREATE
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {
        log.info("Wywołano dodanie produktu: " + product);

        productService.addProduct(product);
    }

    // DELETE
    @DeleteMapping("/delete/{identifier}")
    public void deleteProduct(@PathVariable(name = "identifier") Long identyfikator) {
        log.info("Wywołano usunięcie produktu: " + identyfikator);

        productService.deleteById(identyfikator);
    }

    // UPDATE - właście to samo co post, ale zrobimy patcha.... tak by wiedzieć jak działą
    @PatchMapping("/update")
    public void updateProduct(@RequestBody Product product) {
        log.info("Wywiołano aktualizację produktu: " + product);

        productService.updateProduct(product);
    }

    // @PatchMapping("/update/{identifier}")
    //    public void updateProduct(@PathVariable Long identifier, @RequestBody Product product) {
    //        log.info("Wywiołano aktualizację produktu: " + product);
    //
    //        productService.updateProduct(product);
    //    }



}
