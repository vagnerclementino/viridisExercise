package energy.viridis.exercise.controller;

import energy.viridis.exercise.dto.EquipmentDto;
import energy.viridis.exercise.model.Equipment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Api(value = "equipments", description = "the equipments API")
@RequestMapping("/api/equipment")
public interface EquipmentApi {


    @ApiOperation(value = "List all equipments",
            nickname = "getAllEquipments",
            response = ResponseEntity.class,
            tags={ "Equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List all equipments", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class)})
    @GetMapping(produces = { "application/json" })
    default ResponseEntity<List<Equipment>> getAllEquipments() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }


    @ApiOperation(value = "Get a equipment by id",
                  nickname = "getEquipmentById",
                  response = ResponseEntity.class,
                  tags={ "Equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List all equipments", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class)})
    @GetMapping(value="/{id}", produces = { "application/json" })
    default ResponseEntity<Equipment> getEquipmentById(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ApiOperation(value = "Adds a new equipment",
                  nickname = "saveEquipment",
                  notes = "Adds a new equipment and return the URI",
                  response = ResponseEntity.class,
                  tags={ "Equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Equipment created with success", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @PostMapping(produces = { "application/json" }, consumes = { "application/json" }
    )
    default ResponseEntity<Equipment> saveEquipment(@Valid @RequestBody EquipmentDto body) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ApiOperation(value = "Update a existent equipment",
            nickname = "updateEquipment",
            notes = "No content response",
            response = ResponseEntity.class,
            tags={ "Equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Equipment created with success", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @PutMapping(value="/{id}", produces = { "application/json" },
            consumes = { "application/json" }
    )
    default ResponseEntity<Equipment> updateEquipment(@PathVariable(value = "id", required = true) Long id,
                                                            @Valid @RequestBody EquipmentDto body) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }


    @ApiOperation(value = "Remove a existent Equipment",
            nickname = "removeEquipment",
            notes = "No content response",
            response = ResponseEntity.class,
            tags={ "Equipment", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Equipment created with success", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @DeleteMapping(value="/{id}", produces = { "application/json" },
            consumes = { "application/json" }
    )
    default  ResponseEntity<Equipment> removeEquipment(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
