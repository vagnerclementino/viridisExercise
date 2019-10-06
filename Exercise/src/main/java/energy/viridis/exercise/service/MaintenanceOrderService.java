package energy.viridis.exercise.service;

import java.util.List;

import energy.viridis.exercise.dto.MaintenanceOrderDto;
import energy.viridis.exercise.model.MaintenanceOrder;

public interface MaintenanceOrderService {

	MaintenanceOrder getMaintenanceOrderById(Long id);

	List<MaintenanceOrder> getMaintenanceOrderList();

	MaintenanceOrder saveMaintenanceOrder(MaintenanceOrderDto body);

	MaintenanceOrder updateMaintenanceOrder(Long id, MaintenanceOrderDto body);

	void removeMaintenanceOrder(Long id);

	}
