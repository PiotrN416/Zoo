package com.dq.zoo.controller;

import com.dq.zoo.helpers.IdsService;
import lombok.Getter;

public abstract class SelectOption<T> {

    @Getter
    private long optionId;

    public SelectOption() {
        optionId = IdsService.getNext();
    }

    public abstract T execute();

    public abstract String getOptionLabel();

}
