package com.programmingtech.inventoryservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="t_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inventory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String skuCode;
    private Integer quantity;
}
