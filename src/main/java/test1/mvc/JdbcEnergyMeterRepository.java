package test1.mvc;

import test1.mvc.EnergyMeterRepository;
import test1.mvc.EnergyMeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by Vasiliy on 13.04.2015.
 */
@Repository
public class JdbcEnergyMeterRepository implements EnergyMeterRepository {

    private static final String SQL_INSERT_ENERGYMETER =
            "INSERT INTO ENERGYMETER (ID, ENERGYMETER_NAME, PHASE_NUMBER) VALUES (?, ?, ?)";
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcEnergyMeterRepository(JdbcOperations jdbcOperations) {
     this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void insert(EnergyMeter energyMeter) {
          jdbcOperations.update(SQL_INSERT_ENERGYMETER,energyMeter.getId(),
                  energyMeter.getEnergyMeterName(),energyMeter.getPhaseNumber());
    }

    @Override
    public EnergyMeter findByEnergyMeterId(int energyMeterId) {
        return null;
    }
}
