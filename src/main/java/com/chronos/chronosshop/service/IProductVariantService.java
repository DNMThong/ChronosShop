package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.ProductVariant;

import java.util.List;

public interface IProductVariantService extends IService<ProductVariant,Integer>{
    List<ProductVariant> getVariantBySku(String sku);
}
