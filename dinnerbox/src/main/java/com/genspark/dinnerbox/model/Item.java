package com.genspark.dinnerbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private long id;

    private long parentId;
    private String name;
    private String desc;
    private String price;

}
