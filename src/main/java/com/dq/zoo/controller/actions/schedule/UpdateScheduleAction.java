package com.dq.zoo.controller.actions.schedule;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Schedule;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateScheduleAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("schedule identifier");
        String idVal = view.getPropertyCancellable("identifier value");
        String field = view.getPropertyCancellable("update what");
        String fieldVal = view.getPropertyCancellable("update to");

        String result = persistence.update(id, idVal, field, fieldVal, Schedule.class);

        view.showMessage(result);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Update schedule";
    }
}
