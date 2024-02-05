package de.ithoc.springboot.pagination.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Value("${pagination.page-size}")
    private int pageSize;

    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{pageNumber}")
    public ResponseEntity<Page<Item>> getItems(@PathVariable int pageNumber) {

        Page<Item> pagedItems = itemService.getItems(pageNumber, pageSize);

        return ResponseEntity.ok(pagedItems);
    }
}
