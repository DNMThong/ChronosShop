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
public class Image {
    @Id
    @Column(name = "product_sku")
    private String productSku;

    @Column(name = "image1")
    private String image1;

    @Column(name = "image2")
    private String image2;

    @Column(name = "image3")
    private String image3;

    @Column(name = "image4")
    private String image4;

    @Column(name = "image5")
    private String image5;

    @Column(name = "image6")
    private String image6;
}
