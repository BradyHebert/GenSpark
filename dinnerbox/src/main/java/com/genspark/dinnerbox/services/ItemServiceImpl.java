package com.genspark.dinnerbox.services;

import com.genspark.dinnerbox.entity.ItemEntity;

import com.genspark.dinnerbox.model.Item;

import com.genspark.dinnerbox.repository.ItemRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(Item item) throws Exception {
        try {
            ItemEntity itemEntity = new ItemEntity();
            BeanUtils.copyProperties(item, itemEntity);


            itemEntity = itemRepository.save(itemEntity);

            item.setId(itemEntity.getId());
        } catch (Exception e) {
            throw new Exception("Could not save item: " + e);
        }
            return item;


    }
    @Override
    public List<Item> getMenu(){
        List<ItemEntity> itemEntities = itemRepository.findAll();

        List<Item> items = itemEntities.stream().map(it ->
                Item.builder()
                        .id(it.getId())
                        .desc(it.getDesc())
                        .name(it.getName())
                        .price(it.getPrice())
                        .parentId(it.getParentId())
                        .build()
        ).collect(Collectors.toList());
        return items;
    }

    @Override
    public boolean deleteItem(Long id) {
        ItemEntity item = itemRepository.findById(id).get();
        itemRepository.delete(item);
        return true;
    }

    @Override
    public Item getItemById(Long id) {
        ItemEntity itemEntity = itemRepository.findById(id).get();
        Item item = new Item();
        BeanUtils.copyProperties(itemEntity, item);
        return item;
    }


}
