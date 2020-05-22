package com.dq.zoo.controller.actions;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Employee;
import com.dq.zoo.model.entities.Schedule;
import com.dq.zoo.model.entities.Trip;
import com.dq.zoo.model.entities.Zone;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClearDatabaseAction extends Action {

    private PersistenceManager persistence;

    @Override
    public String execute() {
        persistence.clearData(Employee.class);
        persistence.clearData(Schedule.class);
        persistence.clearData(Trip.class);
        persistence.clearData(Zone.class);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Clear Database";
    }
}
