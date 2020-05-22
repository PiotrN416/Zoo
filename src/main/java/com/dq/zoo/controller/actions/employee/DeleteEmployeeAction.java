package com.dq.zoo.controller.actions.employee;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.helpers.ViewHelper;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Employee;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DeleteEmployeeAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("employee identifier");
        String val = view.getPropertyCancellable("identifier value");

        String result = persistence.delete(id, val, Employee.class);

        view.showMessage(result);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Delete employee";
    }
}
