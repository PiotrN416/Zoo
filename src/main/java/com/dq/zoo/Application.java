package com.dq.zoo;


import com.dq.zoo.controller.SelectOption;
import com.dq.zoo.controller.actions.ClearDatabaseAction;
import com.dq.zoo.controller.actions.CreatePresentationDataAction;
import com.dq.zoo.controller.actions.ExitAction;
import com.dq.zoo.controller.actions.employee.*;
import com.dq.zoo.controller.actions.schedule.*;
import com.dq.zoo.controller.actions.trip.*;
import com.dq.zoo.controller.actions.zone.*;
import com.dq.zoo.controller.options.RunWithMongoDBPersistence;
import com.dq.zoo.controller.options.RunWithRethinkDBPersistence;
import com.dq.zoo.helpers.CancellingOperationException;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.view.ConsoleView;
import com.dq.zoo.view.View;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static List<SelectOption> registeredActions;
    private static PersistenceManager persistence;
    private static View view;

    public static void main(String[] args) {

        view = new ConsoleView();
        view.showMessage("Starting application");

        persistence = initializePersistence();
        registeredActions = initializeActions();

        while (view.isAppRunning()) {
            SelectOption action = view.selectFromOptions("action", registeredActions);
            String result = executeCancellableAction(action);
            view.showMessage(result);
        }

        view.showMessage("Good bye!");

    }

    private static String executeCancellableAction(SelectOption action) {
        try {
            return (String) action.execute();
        } catch (CancellingOperationException e) {
            return ConstraintsUtil.OPERATION_CANCELLED_MESSAGE;
        } catch (Exception e) {
            return e.getClass().getSimpleName() + " " + e.getMessage();
        }
    }

    private static PersistenceManager initializePersistence() {
        List<SelectOption> options = new ArrayList<>();

        options.add(new RunWithMongoDBPersistence());
        options.add(new RunWithRethinkDBPersistence());

        SelectOption option = view.selectFromOptions("persistence handlers", options);

        return (PersistenceManager) option.execute();
    }

    private static List<SelectOption> initializeActions() {
        ArrayList<SelectOption> actions = new ArrayList<>();

        actions.add(new FindAllEmployeesAction(persistence, view));
        actions.add(new FindEmployeesAction(persistence, view));
        actions.add(new AddEmployeeAction(persistence, view));
        actions.add(new UpdateEmployeeAction(persistence,view));
        actions.add(new DeleteEmployeeAction(persistence,view));

        actions.add(new FindAllSchedulesAction(persistence, view));
        actions.add(new FindSchedulesAction(persistence, view));
        actions.add(new AddScheduleAction(persistence, view));
        actions.add(new UpdateScheduleAction(persistence,view));
        actions.add(new DeleteScheduleAction(persistence,view));

        actions.add(new FindAllTripsAction(persistence, view));
        actions.add(new FindTripsAction(persistence, view));
        actions.add(new AddTripAction(persistence, view));
        actions.add(new UpdateTripAction(persistence, view));
        actions.add(new DeleteTripAction(persistence,view));

        actions.add(new FindAllZonesAction(persistence, view));
        actions.add(new FindZonesAction(persistence, view));
        actions.add(new AddZoneAction(persistence, view));
        actions.add(new UpdateZoneAction(persistence,view));
        actions.add(new DeleteZoneAction(persistence,view));

        actions.add(new CreatePresentationDataAction(persistence));
        actions.add(new ClearDatabaseAction(persistence));
        actions.add(new ExitAction(view));

        return actions;
    }
}
