package com.genspark.dinnerbox.repository;

import com.genspark.dinnerbox.entity.ItemEntity;
import com.genspark.dinnerbox.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
