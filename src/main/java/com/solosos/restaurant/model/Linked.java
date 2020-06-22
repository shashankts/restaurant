package com.solosos.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Linked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long linkedId;
    private String linkedTo;

}
