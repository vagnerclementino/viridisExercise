package energy.viridis.exercise.controller;

import energy.viridis.exercise.dto.EquipmentDto;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.service.EquipmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

	private final EquipmentService equipmentService;

	@Autowired
	public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@GetMapping
	public ResponseEntity<List<Equipment>> getAllEquipments() {
		return ResponseEntity.ok().body(equipmentService.getAllEquipments());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Equipment> get(@PathVariable(value = "id", required = true) Long id) {
		return ResponseEntity.ok().body(equipmentService.getEquipmentById(id));
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
	public ResponseEntity<Equipment> saveEquipment(@Valid @RequestBody EquipmentDto body) {

	    Equipment savedEquipment = equipmentService.saveEquipment(body);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedEquipment.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Equipment> updateEquipment(@PathVariable(value = "id",
                                                                   required = true) Long id,
													 @Valid @RequestBody EquipmentDto body) {
	    equipmentService.updateEquipment(id, body);
        return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Equipment> removeEquipment(@PathVariable("id") Long id) {
        equipmentService.removeEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
