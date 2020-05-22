package com.dq.zoo.model.entities;

import com.dq.zoo.model.Entity;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Entity {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String job;

    @Getter
    @Setter
    private Integer salary;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
