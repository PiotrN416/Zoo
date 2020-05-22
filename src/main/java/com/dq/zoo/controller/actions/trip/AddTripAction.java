package com.dq.zoo.controller.actions.trip;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Trip;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddTripAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Trip trip = new Trip();

        trip.setName(view.getPropertyCancellable("Name"));
        trip.setDescription(view.getPropertyCancellable("Description"));
        int guests = (int) view.getValidNumberPropertyCancellable("Guests");
        trip.setGuestsCount(guests);
        trip.setToPay(guests * 5);
        trip.setTimestamp(view.getTimestampCancellable("When"));
        trip.setPaid((int) view.getValidNumberPropertyCancellable("to pay: " + trip.getToPay() + " already paid"));

        persistence.create(trip);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Trip";
    }
}
