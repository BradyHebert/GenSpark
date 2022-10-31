package com.genspark.dinnerbox.controller;

import com.genspark.dinnerbox.model.Item;
import com.genspark.dinnerbox.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ItemController {

    @Autowired
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public Item createItem(@RequestBody Item item)
                                throws Exception {
        return itemService.createItem(item);
    }

    @GetMapping("/item")
    public List<Item> getAllItems(){

        return itemService.getMenu();
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable Long id){
        boolean deleted = false;
        deleted = itemService.deleteItem(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        Item item = null;
        item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }


}
