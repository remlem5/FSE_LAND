package at.kolleg.erplite.customermanagement.ports.out;

import at.kolleg.erplite.customermanagement.domain.Customer;
import at.kolleg.erplite.sharedkernel.marker.OutputPortMarker;
import at.kolleg.erplite.ordermanagement.ports.out.BaseRepository;
import at.kolleg.erplite.customermanagement.domain.CustomerID;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@OutputPortMarker
public interface CustomerRepository extends BaseRepository<Customer, CustomerID> {

}
