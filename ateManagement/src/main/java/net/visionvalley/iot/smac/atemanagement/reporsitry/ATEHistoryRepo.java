package net.visionvalley.iot.smac.atemanagement.reporsitry;

import org.springframework.data.jpa.repository.JpaRepository;

import net.visionvalley.iot.smac.atemanagement.domains.ATEHistory;

public interface ATEHistoryRepo extends JpaRepository<ATEHistory, Long>{

}
