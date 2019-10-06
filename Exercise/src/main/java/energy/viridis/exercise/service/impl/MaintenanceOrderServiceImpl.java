package energy.viridis.exercise.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import energy.viridis.exercise.dto.MaintenanceOrderDto;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.repository.EquipmentRepository;
import energy.viridis.exercise.service.EquipmentService;
import energy.viridis.exercise.util.DateUtils;
import energy.viridis.exercise.error.ExerciseException;
import energy.viridis.exercise.model.MaintenanceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import energy.viridis.exercise.repository.MaintenanceOrderRepository;
import energy.viridis.exercise.service.MaintenanceOrderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MaintenanceOrderServiceImpl implements MaintenanceOrderService {

	private MaintenanceOrderRepository maintenanceOrderRepository;
	private EquipmentService equipmentService;

	@Autowired
	public MaintenanceOrderServiceImpl(MaintenanceOrderRepository maintenanceOrderRepository, EquipmentService equipmentService) {
		this.maintenanceOrderRepository = maintenanceOrderRepository;
		this.equipmentService = equipmentService;
	}

	@Override
	public MaintenanceOrder getMaintenanceOrderById(Long id) {

		log.info("Retrieving Maintenance Order - id: {}", id);
		return findMaintenanceOrderByIdOrElseThrowNotFound(id);

	}

	@Override
	public List<MaintenanceOrder> getMaintenanceOrderList() {

		log.info("Listing all Maintenance Orders...");
		return maintenanceOrderRepository.findAll();

	}


	@Override
	public MaintenanceOrder saveMaintenanceOrder(MaintenanceOrderDto body) {
		MaintenanceOrder maintenanceOrderToSave = new MaintenanceOrder();
		Equipment equipmentSaved = findEquipmentById(body.getEquipmentDto().getId());
		return maintenanceOrderRepository.save(prepareMaintenanceOrderToSave(maintenanceOrderToSave,
																			 equipmentSaved,
																			 body.getScheduledDate())
																			 );
	}

	@Override
	public MaintenanceOrder updateMaintenanceOrder(Long id, MaintenanceOrderDto body) {

		MaintenanceOrder maintenanceOrderToSave = findMaintenanceOrderByIdOrElseThrowNotFound(id);
		Equipment equipmentToUpdate;

		if(body.getEquipmentDto().getId() != maintenanceOrderToSave.getEquipment().getId()){
			equipmentToUpdate = findEquipmentById(body.getEquipmentDto().getId());
			maintenanceOrderToSave.setEquipment(equipmentToUpdate);
		}else{
			 equipmentToUpdate = maintenanceOrderToSave.getEquipment();
		}
		return maintenanceOrderRepository.save(prepareMaintenanceOrderToSave(maintenanceOrderToSave,
																			 equipmentToUpdate,
																			 body.getScheduledDate()));
	}

	@Override
	public void removeMaintenanceOrder(Long id) {

		MaintenanceOrder MaintenanceOrderToRemove = findMaintenanceOrderByIdOrElseThrowNotFound(id);
		maintenanceOrderRepository.deleteById(MaintenanceOrderToRemove.getId());
	}

	private MaintenanceOrder findMaintenanceOrderByIdOrElseThrowNotFound(Long id) {
		if (id <= Long.valueOf(0)) throw new ExerciseException("MaintenanceOrder id is not valid");
		return  maintenanceOrderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Maintenance Order not found."));
	}

	private Equipment findEquipmentById(Long id) {
		return equipmentService.getEquipmentById(id);
	}

	private MaintenanceOrder prepareMaintenanceOrderToSave(MaintenanceOrder maintenanceOrderToSave, Equipment equipment, String scheduledData){
		maintenanceOrderToSave.setEquipment(equipment);
		maintenanceOrderToSave.setScheduledDate(DateUtils.stringToDefaultDate(scheduledData));
		return maintenanceOrderToSave;
	}
}
