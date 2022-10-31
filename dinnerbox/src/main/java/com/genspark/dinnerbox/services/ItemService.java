package com.genspark.dinnerbox.services;

import com.genspark.dinnerbox.model.Item;
import com.genspark.dinnerbox.model.Vendor;

import java.util.List;

public interface ItemService {
    Item createItem(Item item) throws Exception;

    List<Item> getMenu();

    boolean deleteItem(Long id);

    Item getItemById(Long id);

}
