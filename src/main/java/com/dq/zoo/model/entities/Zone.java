package com.dq.zoo.model.entities;

import com.dq.zoo.model.Entity;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Zone extends Entity {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer animalsCount;

    @Getter
    @Setter
    private String species;

    @Getter
    @Setter
    private String foodType;
}
