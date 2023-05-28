package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_url")
    private String categoryUrl;

    @Column(name = "category_parent_id")
    private Integer categoryParentId;
}
