package ee.mark.webshiospring.service;

import ee.mark.webshiospring.model.Item;
import ee.mark.webshiospring.model.Order;
import ee.mark.webshiospring.model.Person;
import ee.mark.webshiospring.repository.ItemRepository;
import ee.mark.webshiospring.repository.OrderRepository;
import ee.mark.webshiospring.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;

    private final PersonRepository personRepository;

    private final OrderRepository orderRepository;

    public List<Item> getDatabaseItems(List<Item> items) {
        List<Item> itemsFromDb = new ArrayList<>();
        for (Item i: items) {
            Item itemFound = itemRepository.findById(i.getId()).get();
            itemsFromDb.add(itemFound);
        }
        return itemsFromDb;
    }

    public double getOrderSum(List<Item> items) {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    public Long saveOrder(double totalAmount, List<Item> items, String personCode) {
        Order order = new Order();
        Person person = personRepository.findById(personCode).get();
        order.setPerson(person);
        order.setOrderItems(items);
        order.setAmount(totalAmount);
        Order submittedOrder = orderRepository.save(order);
        return submittedOrder.getId();
    }
}
