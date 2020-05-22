package com.dq.zoo.controller.options;

import com.dq.zoo.controller.SelectOption;
import com.dq.zoo.model.Entity;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Employee;
import com.dq.zoo.model.entities.Schedule;
import com.dq.zoo.model.entities.Trip;
import com.dq.zoo.model.entities.Zone;
import com.dq.zoo.model.persistence.RethinkDBPersistenceManager;

import java.util.Arrays;
import java.util.Collections;

public class RunWithRethinkDBPersistence extends SelectOption<PersistenceManager> {

    @Override
    public String getOptionLabel() {
        return "Rethink DB";
    }

    @Override
    public PersistenceManager execute() {
        return new RethinkDBPersistenceManager(
                Arrays.asList(Employee.class, Schedule.class, Trip.class, Zone.class));
    }
}
