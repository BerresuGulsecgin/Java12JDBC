package com.berre.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Information {

    private String name;
    private String surname;
    private String middleName;

}
