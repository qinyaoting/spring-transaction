package test1.mvc;

import test1.mvc.EnergyMeter;

/**
 * Created by Vasiliy on 13.04.2015.
 */
public interface EnergyMeterRepository {

    void insert(EnergyMeter energyMeter);
    EnergyMeter findByEnergyMeterId(int energyMeterId);
}
