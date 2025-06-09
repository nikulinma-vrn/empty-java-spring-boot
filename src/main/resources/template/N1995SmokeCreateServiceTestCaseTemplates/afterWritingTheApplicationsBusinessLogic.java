@Service
public class TestServiceImpl implements TestService {

    private final CustomerRepository customerRepository;

    public TestServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findAppropriateCustomer(Order order) {
        Customer customer = order.getCustomer();
        if (customer == null) {
            return customerRepository.findByEmail("email");
        }
        return customer;
    }
}
