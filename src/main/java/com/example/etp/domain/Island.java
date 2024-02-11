package com.example.etp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Classification of all beaches on an Island.
 * <p>
 * Created by Jeffrey Asante
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Island {

    @Id
    private String code;

    @Column
    private String name;
}
