package com.genspark.dinnerbox.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

@Entity
@Data
@Table(name = "vendors")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;
    private String emailId;
    private String cuisine;

    @Lob
    private String file;
}
