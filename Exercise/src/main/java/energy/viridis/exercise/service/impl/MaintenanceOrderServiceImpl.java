package energy.viridis.exercise.service.impl;

import energy.viridis.exercise.dto.EquipmentDto;
import energy.viridis.exercise.dto.MaintenanceOrderDto;
import energy.viridis.exercise.error.ExerciseException;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.repository.MaintenanceOrderRepository;
import energy.viridis.exercise.service.EquipmentService;
import energy.viridis.exercise.service.MaintenanceOrderService;
import energy.viridis.exercise.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class MaintenanceOrderServiceImpl implements MaintenanceOrderService {

	private final MaintenanceOrderRepository maintenanceOrderRepository;
	private final EquipmentService equipmentService;

	@Autowired
	public MaintenanceOrderServiceImpl(MaintenanceOrderRepository maintenanceOrderRepository, EquipmentService equipmentService) {
		this.maintenanceOrderRepository = maintenanceOrderRepository;
		this.equipmentService = equipmentService;
	}

	@Override
	@Cacheable("maintenanceOrder")
	public MaintenanceOrder getMaintenanceOrderById(Long id) {

		log.info("Retrieving Maintenance Order - id: {}", id);
		return findMaintenanceOrderByIdOrElseThrowNotFound(id);

	}

	@Override
	@CachePut("maintenanceOrder")
	public List<MaintenanceOrder> getMaintenanceOrderList() {

		log.info("Listing all Maintenance Orders...");
		return maintenanceOrderRepository.findAll();

	}


	@Override
	@CachePut("maintenanceOrder")
	public MaintenanceOrder saveMaintenanceOrder(MaintenanceOrderDto body) {
		MaintenanceOrder maintenanceOrderToSave = new MaintenanceOrder();
		Optional<Equipment> equipmentSaved = findEquipment(body.getEquipment());
		return maintenanceOrderRepository.save(prepareMaintenanceOrderToSave(maintenanceOrderToSave,
																			 equipmentSaved.get(),
																			 body.getScheduledDate())
																			 );
	}

	@Override
	@CachePut("maintenanceOrder")
	public MaintenanceOrder updateMaintenanceOrder(Long id, MaintenanceOrderDto body) {

		MaintenanceOrder maintenanceOrderToSave = findMaintenanceOrderByIdOrElseThrowNotFound(id);

		Optional<Equipment> equipmentOptionalToUpdate = findEquipment(body.getEquipment());

		return maintenanceOrderRepository.save(prepareMaintenanceOrderToSave(maintenanceOrderToSave,
																			 equipmentOptionalToUpdate.get(),
																			 body.getScheduledDate()));
	}

	@Override
	@CachePut("maintenanceOrder")
	public void removeMaintenanceOrder(Long id) {

		MaintenanceOrder MaintenanceOrderToRemove = findMaintenanceOrderByIdOrElseThrowNotFound(id);
		maintenanceOrderRepository.deleteById(MaintenanceOrderToRemove.getId());
	}

	private MaintenanceOrder findMaintenanceOrderByIdOrElseThrowNotFound(Long id) {
		if (id <= Long.valueOf(0)) throw new ExerciseException("MaintenanceOrder id is not valid");
		return  maintenanceOrderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Maintenance Order not found."));
	}

	private Optional<Equipment> findEquipment(EquipmentDto dto) {
		if(!Objects.isNull(dto.getId())){
			return Optional.ofNullable(equipmentService.getEquipmentById(dto.getId()));
		}
		if(!Objects.isNull(dto.getName())){
			return Optional.ofNullable(equipmentService.getEquipmentByName(dto.getName()));
		}
		return Optional.empty();
	}

	private MaintenanceOrder prepareMaintenanceOrderToSave(MaintenanceOrder maintenanceOrderToSave, Equipment equipment, String scheduledData){
		maintenanceOrderToSave.setEquipment(equipment);
		maintenanceOrderToSave.setScheduledDate(DateUtils.stringToDefaultDate(scheduledData));
		return maintenanceOrderToSave;
	}
}
