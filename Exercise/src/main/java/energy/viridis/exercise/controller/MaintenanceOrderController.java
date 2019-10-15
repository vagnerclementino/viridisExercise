package energy.viridis.exercise.controller;

import energy.viridis.exercise.dto.MaintenanceOrderDto;
import energy.viridis.exercise.error.ExerciseException;
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.service.MaintenanceOrderService;
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
public class MaintenanceOrderController implements MaintenanceOrderApi {

	private MaintenanceOrderService maintenanceOrderService;

	@Autowired
	public MaintenanceOrderController(MaintenanceOrderService maintenanceOrderService) {
		this.maintenanceOrderService = maintenanceOrderService;
	}

	@Override
	public ResponseEntity<List<MaintenanceOrder>> getMaintenanceOrderList() {
		return ResponseEntity.ok().body(maintenanceOrderService.getMaintenanceOrderList());
	}

	@Override
	public ResponseEntity<MaintenanceOrder> getMaintenanceOrderById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(maintenanceOrderService.getMaintenanceOrderById(id));
	}

	@Override
	public ResponseEntity<MaintenanceOrder> saveMaintenanceOrder(@Valid @RequestBody MaintenanceOrderDto body) {


		validateRequest(body);
		MaintenanceOrder savedMaintenanceOrder = maintenanceOrderService.saveMaintenanceOrder(body);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedMaintenanceOrder.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@Override
	public ResponseEntity<MaintenanceOrder> updateMaintenanceOrder(@PathVariable(value = "id", required = true) Long id,
																   @Valid @RequestBody MaintenanceOrderDto body) {
		validateRequest(body);
		maintenanceOrderService.updateMaintenanceOrder(id, body);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<MaintenanceOrder> removeMaintenanceOrder(@PathVariable("id") Long id) {
		maintenanceOrderService.removeMaintenanceOrder(id);
		return ResponseEntity.noContent().build();
	}

	private void validateRequest(MaintenanceOrderDto body){
		if(Objects.isNull(body.getEquipment().getId()) && Objects.isNull(body.getEquipment().getName())){
			throw new ExerciseException("Equipment name and id cannot be null");
		}
	}
}
