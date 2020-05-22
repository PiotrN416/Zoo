package com.dq.zoo.model.entities;

import com.dq.zoo.helpers.SimpleDate;
import com.dq.zoo.model.Entity;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends Entity {

    @Getter
    @Setter
    private String employeeFullName;

    @Getter
    @Setter
    private SimpleDate timestamp;

    @Getter
    @Setter
    private String description;
}
