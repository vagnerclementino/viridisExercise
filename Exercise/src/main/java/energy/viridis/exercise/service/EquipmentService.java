package energy.viridis.exercise.service;

import java.util.List;

import energy.viridis.exercise.dto.EquipmentDto;
import energy.viridis.exercise.model.Equipment;

public interface EquipmentService {

	Equipment getEquipmentById(Long id);

	List<Equipment> getAllEquipments();

    Equipment saveEquipment(EquipmentDto body);

	Equipment updateEquipment(Long id, EquipmentDto body);

	void removeEquipment(Long id);

    Equipment getEquipmentByName(String name);
}
