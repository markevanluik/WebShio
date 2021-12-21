package ee.mark.webshiospring.service;

import ee.mark.webshiospring.model.Item;
import ee.mark.webshiospring.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item addItem(Item item) {
        //itemCache.updateCache(item);
        return itemRepository.save(item);
    }

    public Item editItem(Item item) {
        //itemCache.updateCache(item);
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        //itemCache.updateCache(item);
        itemRepository.deleteById(id);
    }

    public Item getItem(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
