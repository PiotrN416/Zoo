package com.dq.zoo.controller.actions;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.helpers.TimeUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Employee;
import com.dq.zoo.model.entities.Schedule;
import com.dq.zoo.model.entities.Trip;
import com.dq.zoo.model.entities.Zone;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePresentationDataAction extends Action {

    private PersistenceManager persistence;

    @Override
    public String execute() {
        Employee e1 = new Employee("Adam", "Adamczyk", "Karmiciel", 2100);
        Employee e2 = new Employee("Emil", "Walaszek", "Ochroniarz", 2200);
        Employee e3 = new Employee("Tadeusz", "Grucha", "Bramka", 1900);

        persistence.create(e1);
        persistence.create(e2);
        persistence.create(e3);

        persistence.create(new Schedule(e1.getFullName(), TimeUtil.getCurrent(), "Regularne Karmienie"));
        persistence.create(new Schedule(e2.getFullName(), TimeUtil.getCurrent(), "Ochrona"));
        persistence.create(new Schedule(e3.getFullName(), TimeUtil.getCurrent(), "Sprzedaz biletow"));

        persistence.create(new Trip(TimeUtil.getCurrent(), 10, 50, 50, "Szkolna wycieczka", ""));
        persistence.create(new Trip(TimeUtil.getCurrentPlusDays(1), 13, 65, 65, "Urodziny", "Piknik przy sadzawce"));
        persistence.create(new Trip(TimeUtil.getCurrentPlusDays(2), 20, 100, 0, "Impreza firmowa", "Zachodni wybieg nr4"));

        persistence.create(new Zone("Malpy", 24, "Hungarian Moutain Ape", "owoce, ryby"));
        persistence.create(new Zone("Malpy", 12, "African Forest Ape", "owoce, ryby"));
        persistence.create(new Zone("Lwy", 5, "African wild cat", "mieso"));
        persistence.create(new Zone("Ryby", 451, "Medusa, Steel fish, Blue sailor", "Karma w proszku"));

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Create presentation data";
    }
}
