package com.genspark.dinnerbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    private long id;
    private String name;
    private String emailId;
    private String cuisine;
    private String file;
    private String license;
}
