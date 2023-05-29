package com.chronos.chronosshop.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "category_id")
    String id;

    @Column(name = "category_name")
    String name;

    @Column(name = "category_url")
    String url;


    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Category> subCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_parent_id")
    @JsonBackReference
    private Category parentCategory;

}
