package com.javatechbd.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "customer_name")
    String customerName;

    @NotNull
    @Column(name = "email")
    String email;

    @Column(name = "created_at")
    LocalDateTime created_at;

    @Column(name = "created_by")
    String  created_by;

}
