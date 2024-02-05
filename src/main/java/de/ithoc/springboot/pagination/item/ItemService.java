package de.ithoc.springboot.pagination.item;

import jakarta.annotation.PostConstruct;
import org.jeasy.random.EasyRandom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostConstruct
    private void init() {

        EasyRandom generator = new EasyRandom();
        for (int i = 0; i < 1000; i++) {
            ItemEntity itemEntity = generator.nextObject(ItemEntity.class);
            itemEntity.setId(null);
            itemRepository.save(itemEntity);
        }
    }

    private ItemEntity createItemEntity(String name, String description) {

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(name);
        itemEntity.setDescription(description);

        return itemEntity;
    }

    public Page<Item> getItems(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "name"); // default sort ascending
        Page<ItemEntity> pagedItemEntities = itemRepository.findAll(pageable);
        Page<Item> pageItems = pagedItemEntities.map(this::mapToItem);

        return pageItems;
    }

    private Item mapToItem(ItemEntity itemEntity) {
        return new Item(itemEntity.getName(), itemEntity.getDescription());
    }

}
