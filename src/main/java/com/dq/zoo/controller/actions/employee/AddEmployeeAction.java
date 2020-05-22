package com.dq.zoo.controller.actions.employee;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Employee;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddEmployeeAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Employee emp = new Employee();

        emp.setFirstName(view.getPropertyCancellable("first name"));
        emp.setLastName(view.getPropertyCancellable("last name"));
        emp.setJob(view.getPropertyCancellable("job"));
        emp.setSalary((int) view.getValidNumberPropertyCancellable("salary"));

        persistence.create(emp);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add employee";
    }
}
