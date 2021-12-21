package ee.mark.webshiospring.controller;

import ee.mark.webshiospring.model.output.ItemResponse;
import ee.mark.webshiospring.model.Item;
import ee.mark.webshiospring.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "Get all items")
    @GetMapping("items")
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @Operation(summary = "Get one item")
    @GetMapping("items/{itemId}")
    public Item getItem(@PathVariable Long itemId) {
        return itemService.getItem(itemId);
    }

    @Operation(summary = "Add one item")
    @PostMapping("items")
    public ResponseEntity<ItemResponse> addItem(@RequestBody Item item) {
        Item itemSaved = itemService.addItem(item);
        return new ResponseEntity<>(new ItemResponse("Item '" + itemSaved.getTitle()
                + "' with ID-" + itemSaved.getId() + " and category '" + itemSaved.getCategory().getCategoryName()
                + "' was added"), HttpStatus.CREATED);
    }

    @Operation(summary = "Update one item")
    @PutMapping("items")
    public ResponseEntity<ItemResponse> editItem(@RequestBody Item item) {
        Item itemEdited = itemService.editItem(item);
        return new ResponseEntity<>(new ItemResponse("Item with ID-" + itemEdited.getId()
                + " has been edited. " + itemEdited.getTitle() +  "-"
                +  itemEdited.getCategory().getCategoryName()), HttpStatus.OK) ;
    }

    @Operation(summary = "Delete one item")
    @DeleteMapping("items/{itemId}")
    public ResponseEntity<ItemResponse> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(new ItemResponse("Item deleted ID-" + itemId), HttpStatus.OK) ;
    }

}
