package com.dq.zoo.controller.options;

import com.dq.zoo.controller.SelectOption;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.persistence.MongoDBPersistenceManager;

public class RunWithMongoDBPersistence extends SelectOption<PersistenceManager> {

    @Override
    public String getOptionLabel() {
        return "Mongo DB";
    }

    @Override
    public PersistenceManager execute() {
        return new MongoDBPersistenceManager();
    }
}
