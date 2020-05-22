package com.dq.zoo.controller.actions.schedule;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.helpers.ViewHelper;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Schedule;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllSchedulesAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> schedules = persistence.findAllAsJson(Schedule.class);

        ViewHelper.displaySearchResults("schedules", schedules, view);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "All Schedules";
    }
}
