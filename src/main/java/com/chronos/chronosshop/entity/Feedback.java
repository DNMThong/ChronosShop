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
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedbackId;

    @Column(name = "feedback_source_media")
    private String feedbackSourceMedia;

    @Column(name = "feedback_file_type")
    private String feedbackFileType;

    @Column(name = "feedback_text")
    private String feedbackText;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
