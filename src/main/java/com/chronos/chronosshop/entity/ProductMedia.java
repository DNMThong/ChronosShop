package com.chronos.chronosshop.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product_media", schema = "dbo", catalog = "ChronosShoppingOnline")
public class ProductMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private int mediaId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "create_time")
    private Date createTime;
}
