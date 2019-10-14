package energy.viridis.exercise.repository;

import energy.viridis.exercise.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import energy.viridis.exercise.model.MaintenanceOrder;

import java.util.Date;
import java.util.Optional;

public interface MaintenanceOrderRepository extends JpaRepository<MaintenanceOrder, Long> {

    Optional<MaintenanceOrder> findByScheduledDateAndEquipment(Date scheduledDate, Equipment equipment);
}
