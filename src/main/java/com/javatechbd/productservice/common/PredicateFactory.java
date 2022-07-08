package com.javatechbd.productservice.common;

import com.javatechbd.productservice.dto.search.ProductSearchDTO;
import com.javatechbd.productservice.entity.QBrandEntity;
import com.javatechbd.productservice.entity.QProductEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;

public class PredicateFactory {

    private final static QBrandEntity brand = QBrandEntity.brandEntity;
    private final static QProductEntity product = QProductEntity.productEntity;

    public static Predicate productSearchPredicate (ProductSearchDTO searchDto) {
        BooleanBuilder builder = new BooleanBuilder();
        if(!StringUtils.isEmpty(searchDto.getProductName())) {
            builder.and(product.productName.containsIgnoreCase(searchDto.getProductName()));
        }
        if(!StringUtils.isEmpty(searchDto.getBrandName())) {
            builder.and(product.brand.brandName.containsIgnoreCase(searchDto.getBrandName()));
        }
        return builder;
    }
}
