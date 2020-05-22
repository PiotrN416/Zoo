package com.dq.zoo.controller.actions.schedule;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Schedule;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddScheduleAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Schedule sched = new Schedule();

        sched.setEmployeeFullName(view.getPropertyCancellable("Employee full name"));
        sched.setDescription(view.getPropertyCancellable("description"));
        sched.setTimestamp(view.getTimestampCancellable("timestamp"));

        persistence.create(sched);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Schedule";
    }
}
