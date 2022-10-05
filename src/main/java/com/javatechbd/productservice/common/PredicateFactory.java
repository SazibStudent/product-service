package com.javatechbd.productservice.common;

import com.javatechbd.productservice.dto.search.ProductSearchDTO;
import com.javatechbd.productservice.entity
        .QBrandEntity;
import com.javatechbd.productservice.entity.QProductEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

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
        Optional.ofNullable(searchDto.getProductIds()).ifPresent(itm-> {
            builder.and(product.id.in(itm)); // where id in (1,2,3)
        });
        return builder;
    }
}
