package at.kolleg.erplite.stockmanagement.db;

import at.kolleg.erplite.stockmanagement.business.Packing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Repository
@RepositoryRestResource(path = "packings") //SpringDataRest API http://localhost:8080/packings
public interface PackingRepository extends JpaRepository<Packing, Long> {
    Packing findByOrderId(String orderId);
}
