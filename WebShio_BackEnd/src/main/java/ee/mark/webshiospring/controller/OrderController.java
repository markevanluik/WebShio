package ee.mark.webshiospring.controller;

import ee.mark.webshiospring.model.Item;
import ee.mark.webshiospring.model.Person;
import ee.mark.webshiospring.model.output.EveryPayLink;
import ee.mark.webshiospring.repository.PersonRepository;
import ee.mark.webshiospring.service.OrderService;
import ee.mark.webshiospring.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final PaymentService paymentService;

    private final OrderService orderService;

    private final PersonRepository personRepository;

    @Operation(summary = "Start the payment")
    @PostMapping("payment")
    public EveryPayLink startPayment(@RequestBody List<Item> items) {
        log.info("Starting payment");
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = personRepository.findByEmail(email);
        EveryPayLink everyPayLink = new EveryPayLink();
        if(person != null) {
            String personCode = person.getPersonCode();
            List<Item> databaseItems = orderService.getDatabaseItems(items);
            double totalAmount = orderService.getOrderSum(databaseItems);
            Long orderId = orderService.saveOrder(totalAmount, databaseItems, personCode);
            log.info("Starting EveryPay payment {}", orderId);
            everyPayLink = paymentService.getPaymentLink(totalAmount, orderId);
            log.info("Fetched EveryPay link {}", everyPayLink);
        }

        return everyPayLink;
    }

    @Operation(summary = "Change payment status to 'paid'")
    @PostMapping("paid")
    public void changePaymentStatus() {
        //TODO
        //Find the order from DB and change status to 'paid'
    }

    //TODO
   /* @GetMapping("orders")
    public List<Order> getOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        String personCode = personRepository.findByEmail(email).getPersonCode();
        orderService.get
    }*/
}
