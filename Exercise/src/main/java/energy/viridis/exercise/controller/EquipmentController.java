package energy.viridis.exercise.controller;

import energy.viridis.exercise.dto.EquipmentDto;
import energy.viridis.exercise.error.ExerciseException;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class EquipmentController implements EquipmentApi {

	private final EquipmentService equipmentService;

	@Autowired
	public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@Override
	public ResponseEntity<List<Equipment>> getAllEquipments() {
		return ResponseEntity.ok().body(equipmentService.getAllEquipments());
	}

	@Override
	public ResponseEntity<Equipment> getEquipmentById(@PathVariable(value = "id", required = true) Long id) {
		return ResponseEntity.ok().body(equipmentService.getEquipmentById(id));
	}

	@Override
	public ResponseEntity<Equipment> saveEquipment(@Valid @RequestBody EquipmentDto body) {

		validate(body);
	    Equipment savedEquipment = equipmentService.saveEquipment(body);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedEquipment.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
	}

	@Override
	public ResponseEntity<Equipment> updateEquipment(@PathVariable(value = "id", required = true) Long id,
													 @Valid @RequestBody EquipmentDto body) {
		validate(body);
	    equipmentService.updateEquipment(id, body);
        return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Equipment> removeEquipment(@PathVariable("id") Long id) {
        equipmentService.removeEquipment(id);
        return ResponseEntity.noContent().build();
    }

	private void validate(EquipmentDto body) throws ExerciseException {
		if (Objects.isNull(body.getName())){
			throw new ExerciseException("Equipment name cannot be null");
		}
	}
}
