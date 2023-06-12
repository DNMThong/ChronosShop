package com.chronos.chronosshop.entity.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
