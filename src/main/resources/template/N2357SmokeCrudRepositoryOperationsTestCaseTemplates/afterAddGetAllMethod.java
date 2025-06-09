import com.example.demo.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@GetMapping("/allOrders")
public PagedModel<Order> getAll(Pageable pageable) {
    Page<Order> orders = orderRepository.findAll(pageable);
    return new PagedModel<>(orders);
}