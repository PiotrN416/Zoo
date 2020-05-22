package com.dq.zoo.controller.actions.zone;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Zone;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteZoneAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("zone identifier");
        String val = view.getPropertyCancellable("identifier value");

        String result = persistence.delete(id, val, Zone.class);

        view.showMessage(result);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Delete zone";
    }
}
