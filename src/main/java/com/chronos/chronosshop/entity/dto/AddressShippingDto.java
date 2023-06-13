package com.chronos.chronosshop.entity.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressShippingDto {
    private String recipientName;

    private String recipientPhone;

    private String country;

    private String province;

    private String district;

    private String ward;

    private String number;

    private String status;

}
