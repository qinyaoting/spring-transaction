package org.pavlov.springapp.mvc.javascriptex1.repository;

import org.pavlov.springapp.mvc.javascriptex1.model.EnergyMeter;

/**
 * Created by Vasiliy on 13.04.2015.
 */
public interface EnergyMeterRepository {

    void insert(EnergyMeter energyMeter);
    EnergyMeter findByEnergyMeterId(int energyMeterId);
}
