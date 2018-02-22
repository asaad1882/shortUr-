package net.visionvalley.iot.smac.atemanagement.reporsitry;

import org.springframework.data.jpa.repository.JpaRepository;

import net.visionvalley.iot.smac.atemanagement.domains.ATEHistory;
import net.visionvalley.iot.smac.atemanagement.domains.ProvisionTransactions;

public interface ProvisionTransactionRepo extends JpaRepository<ProvisionTransactions, Long>{
 public ProvisionTransactions findByTransactionId(long transactionId);
}
