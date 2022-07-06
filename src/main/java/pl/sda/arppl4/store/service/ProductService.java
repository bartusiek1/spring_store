package pl.sda.arppl4.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.store.model.Product;
import pl.sda.arppl4.store.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor        // potrzebny jest konstruktor, ale jako że mamy final - stosujemy @Required....
                                // dzięki temu nie musimy tworzyć tego konstruktora

public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long identyfikator) {
        productRepository.deleteById(identyfikator);
    }

    public void updateProduct(Product daneAktualizujące) {
        Long identifier = daneAktualizujące.getId(); // identyfikator obiektu edytowanego

        Optional<Product> productOptional = productRepository.findById(identifier);
        if (productOptional.isPresent()) {
            Product editedProduct = productOptional.get();

            if (daneAktualizujące.getName() != null) {
                // zastąp name jeśli dane aktualizujące zawierają 'name'
                editedProduct.setName(daneAktualizujące.getName());
            }
            if (daneAktualizujące.getProducent() != null) {
                // zastąp producent jeśli dane aktualizujące zawierają 'producent'
                editedProduct.setProducent(daneAktualizujące.getProducent());
            }
            if (daneAktualizujące.getExpiryDate() != null) {
                editedProduct.setExpiryDate(daneAktualizujące.getExpiryDate());
            }
            if (daneAktualizujące.getPrice() != null) {
                editedProduct.setPrice(daneAktualizujące.getPrice());
            }
            if (daneAktualizujące.getUnit() != null) {
                editedProduct.setUnit(daneAktualizujące.getUnit());
            }
            if (daneAktualizujące.getQuantity() != null) {
                editedProduct.setQuantity(daneAktualizujące.getQuantity());
            }
            productRepository.save(editedProduct);
            log.info("Produkt został zapisany.");
            return;
        }
        throw new EntityNotFoundException("Nie znaleziono produktu o id: " + daneAktualizujące.getId());
    }
}
