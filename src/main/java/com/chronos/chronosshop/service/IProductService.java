package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Product;

import java.util.List;

public interface IProductService extends IService<Product,Integer>{

    List<Product> getListNewestProduct(Integer limit);

    List<Product> getListProductContainName(String name);
}
