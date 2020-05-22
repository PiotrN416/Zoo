package com.dq.zoo.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class SimpleDate implements Serializable {

    @Getter
    @Setter
    int year;

    @Getter
    @Setter
    int month;

    @Getter
    @Setter
    int day;

    @Override
    public String toString() {
        return "{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
