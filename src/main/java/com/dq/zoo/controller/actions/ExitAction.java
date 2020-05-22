package com.dq.zoo.controller.actions;


import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExitAction extends Action {

    private View view;

    @Override
    public String execute() {
        view.setAppRunning(false);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Exit";
    }
}
