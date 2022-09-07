package com.javatechbd.productservice.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "brand")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "brand_name")
    private String brandName;
}
