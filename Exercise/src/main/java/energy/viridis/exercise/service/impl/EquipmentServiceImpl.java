package energy.viridis.exercise.service.impl;

import energy.viridis.exercise.dto.EquipmentDto;
import energy.viridis.exercise.error.ExerciseException;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.repository.EquipmentRepository;
import energy.viridis.exercise.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service
public class EquipmentServiceImpl implements EquipmentService {

	private final EquipmentRepository equipmentRepository;

	@Autowired
	public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}

	@Override
	@Cacheable("equipments")
	public Equipment getEquipmentById(Long id) {

		log.info("Retrieving Equipment - id: {}", id);
		return findEquipmentByIdOrElseThrowNotFound(id);

	}

	@Override
	@Cacheable("equipments")
	public List<Equipment> getAllEquipments() {

		log.info("Listing all Equipment");
		return equipmentRepository.findAll();

	}

	@Override
	@CacheEvict(value = "equipments", allEntries = true)
	public Equipment saveEquipment(EquipmentDto body) {
		Equipment equipmentToSave = new Equipment().withName(body.getName());
		return equipmentRepository.save(equipmentToSave);
	}

	@Override
	@CacheEvict(value = "equipments", allEntries = true)
	public Equipment updateEquipment(Long id, EquipmentDto body) {

		Equipment equipmentToSave = findEquipmentByIdOrElseThrowNotFound(id);
		equipmentToSave.setName(body.getName());
        return equipmentRepository.save(equipmentToSave);
	}

	@Override
	@CacheEvict(value = "equipments", allEntries = true)
	public void removeEquipment(Long id) {

		Equipment equipmentToRemove = findEquipmentByIdOrElseThrowNotFound(id);
		equipmentRepository.deleteById(equipmentToRemove.getId());
	}

	@Override
	public Equipment getEquipmentByName(String name) {
		return findEquipmentByNameElseThrowNotFound(name);
	}

	private Equipment findEquipmentByIdOrElseThrowNotFound(Long id) {
		if ( Objects.isNull(id) || id <= Long.valueOf(0)){
			throw new ExerciseException("Equipment id is not valid");
		}
		return equipmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Equipment not found."));
	}

	private Equipment findEquipmentByNameElseThrowNotFound(String name) {
		if ( Objects.isNull(name)){
			throw new ExerciseException("Equipment name cannot be null");
		}
		return equipmentRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Equipment not found."));
	}
}
