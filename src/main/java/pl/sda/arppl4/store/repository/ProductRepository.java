package pl.sda.arppl4.store.repository;

// Do tworzenia, pobierania, edytowania, usuwania obiekt ow w bazie danych
//  C   R   U   D

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.store.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
