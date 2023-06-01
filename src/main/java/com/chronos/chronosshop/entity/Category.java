package com.chronos.chronosshop.entity;

import  lombok.*;

import jakarta.persistence.*;

import java.util.List;
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

    @OneToMany(mappedBy = "categoryParent", fetch = FetchType.LAZY)
    private List<Category> subcategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_parent_id")
    private Category categoryParent;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
