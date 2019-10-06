package energy.viridis.exercise.controller;

import energy.viridis.exercise.dto.MaintenanceOrderDto;
import energy.viridis.exercise.error.ExerciseException;
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.service.MaintenanceOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/maintenance-order")
public class MaintenanceOrderController {

	private MaintenanceOrderService maintenanceOrderService;

	@Autowired
	public MaintenanceOrderController(MaintenanceOrderService maintenanceOrderService) {
		this.maintenanceOrderService = maintenanceOrderService;
	}

	@GetMapping
	public ResponseEntity<List<MaintenanceOrder>> getMaintenanceOrderList() {
		return ResponseEntity.ok().body(maintenanceOrderService.getMaintenanceOrderList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MaintenanceOrder> getMaintenanceOrder(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(maintenanceOrderService.getMaintenanceOrderById(id));
	}

	//TODO Revisar
	@ApiOperation(value = "Adiciona um novo carro",
			nickname = "adicionaCarro",
			notes = "Inclui um novo carro retornando a URI de acesso ao recurso",
			response = ResponseEntity.class,
			tags={ "Carros", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Carro adicionado com sucesso", response = ResponseEntity.class),
			@ApiResponse(code = 405, message = "Entrada inválida"),
			@ApiResponse(code = 500, message = "Erro interno") })
	@PostMapping(produces = { "application/json" },
			consumes = { "application/json" }
	)
	public ResponseEntity<MaintenanceOrder> saveMaintenanceOrder(@Valid @RequestBody MaintenanceOrderDto body) {


		validateRequest(body);
		MaintenanceOrder savedMaintenanceOrder = maintenanceOrderService.saveMaintenanceOrder(body);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedMaintenanceOrder.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<MaintenanceOrder> updateMaintenanceOrder(@PathVariable(value = "id", required = true) Long id,
																   @Valid @RequestBody MaintenanceOrderDto body) {
		validateRequest(body);
		maintenanceOrderService.updateMaintenanceOrder(id, body);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MaintenanceOrder> removeMaintenanceOrder(@PathVariable("id") Long id) {
		maintenanceOrderService.removeMaintenanceOrder(id);
		return ResponseEntity.noContent().build();
	}

	private void validateRequest(MaintenanceOrderDto body){
		if(Objects.isNull(body.getEquipmentDto().getId())){
			throw new ExerciseException("Equipment id cannot be null");
		}
	}
}
