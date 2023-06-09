package com.chronos.chronosshop.entity;

import com.chronos.chronosshop.util.CurrencyUtil;
import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price_old")
    private Integer priceOld;

    @Column(name = "price_new")
    private Integer priceNew;

    @Column(name = "material")
    private String material;

    @Column(name = "description")
    private String description;

    @Column(name = "sold_record")
    private Integer soldRecord;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "status")
    private String status;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "product")
    private List<ProductMedia> productMediaList;

    @OneToMany(mappedBy = "product")
    private List<ProductVariant> productVariants;

    @Column(name = "deleted")
    private Boolean deleted;

    public Product( String productName, Integer priceOld, Integer priceNew, String material, String description, Category category, String status) {
        this.productName = productName;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
        this.material = material;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    public String getPriceNewFormat() {
        return CurrencyUtil.format(this.priceNew);
    }

    public List<ProductVariant> getDistinctVariants() {
        List<ProductVariant> variants = null;
        HashMap<String, ProductVariant> map = new HashMap<>();
        productVariants.forEach(vari -> map.put(vari.getProductColorName(), vari));
        return new ArrayList<>(map.values());
    }

    public Integer getNumberOfProductsSold() {
        return productVariants.stream().mapToInt(i -> i.getOrderDetailList().size()).sum();
    }

}
