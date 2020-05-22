package com.dq.zoo.model.entities;

import com.dq.zoo.helpers.SimpleDate;
import com.dq.zoo.model.Entity;
import lombok.*;

import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Trip extends Entity {

    @Getter
    @Setter
    private SimpleDate timestamp;

    @Getter
    @Setter
    private Integer guestsCount;

    @Getter
    @Setter
    private Integer toPay;

    @Getter
    @Setter
    private Integer paid;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;
}
