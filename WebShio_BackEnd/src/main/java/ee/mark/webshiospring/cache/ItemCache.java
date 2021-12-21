package ee.mark.webshiospring.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ee.mark.webshiospring.model.Item;
import ee.mark.webshiospring.repository.ItemRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

//TODO finish cache implementation

@Component
@Log4j2
@RequiredArgsConstructor
public class ItemCache {

    final private ItemRepository itemRepository;

    private final LoadingCache<Long, Item> itemCache = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .build(new CacheLoader<>() {
                @Override
                public Item load(@NonNull Long id) {
                    log.info("Loading cache {}", id);
                    return itemRepository.findById(id).get();
                }
            });

    private void updateCacheIfEmpty() {
        if (itemCache.asMap().isEmpty()) {
            itemRepository.findAll().forEach(item -> itemCache.put(item.getId(), item));
            log.info("Update cache");
        }
    }

    public Item getItem(Long id) throws ExecutionException {
        updateCacheIfEmpty();
        log.info("Got item {} from cache", id);
        return this.itemCache.get(id);
    }

    public List<Item> getItems() {
        updateCacheIfEmpty();
        this.itemCache.asMap().values().forEach(item -> System.out.println(item.getTitle()));
        log.info("Got items from cache");
        return new ArrayList<>(this.itemCache.asMap().values());
    }

    public void updateCache(Item item) {
        updateCacheIfEmpty();
        itemCache.put(item.getId(), item);
        log.info("Insert to cache {}", item.getId());
    }

    public void deleteFromCache(Long id) {
        updateCacheIfEmpty();
        itemCache.invalidate(id);
        log.info("Delete from cache {}", id);
    }
}
