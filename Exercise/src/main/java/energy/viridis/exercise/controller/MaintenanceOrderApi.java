package energy.viridis.exercise.controller;

import energy.viridis.exercise.dto.MaintenanceOrderDto;
import energy.viridis.exercise.model.MaintenanceOrder;
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

@Api(value = "maintenance-order", description = "the maintenance order API")
@RequestMapping("/api/maintenance-order")
public interface MaintenanceOrderApi {


    @ApiOperation(value = "List all maintenance orders",
            nickname = "getMaintenanceOrderList",
            response = ResponseEntity.class,
            tags={ "Maintenance Order", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List all maintenance orders", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class)})
    @GetMapping(produces = { "application/json" })
    default ResponseEntity<List<MaintenanceOrder>> getMaintenanceOrderList() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }


    @ApiOperation(value = "Get a maintenance order by id",
                  nickname = "getMaintenanceOrderById",
                  response = ResponseEntity.class,
                  tags={ "Maintenance Order", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List all maintenance orders", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class)})
    @GetMapping(value="/{id}", produces = { "application/json" })
    default ResponseEntity<MaintenanceOrder> getMaintenanceOrderById(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ApiOperation(value = "Adds a new  maintenance order ",
                  nickname = "saveMaintenanceOrder",
                  notes = "Adds a new maintenance order and return the URI",
                  response = ResponseEntity.class,
                  tags={ "Maintenance Order", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Maintenance order created with success", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @PostMapping(produces = { "application/json" }, consumes = { "application/json" }
    )
    default ResponseEntity<MaintenanceOrder> saveMaintenanceOrder(@Valid @RequestBody MaintenanceOrderDto body) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ApiOperation(value = "Update a existent maintenance order",
            nickname = "updateMaintenanceOrder",
            notes = "No content response",
            response = ResponseEntity.class,
            tags={ "Maintenance Order", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Maintenance order updated  with success", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @PutMapping(value="/{id}", produces = { "application/json" },
            consumes = { "application/json" }
    )
    default ResponseEntity<MaintenanceOrder> updateMaintenanceOrder(@PathVariable(value = "id", required = true) Long id,
                                                      @Valid @RequestBody MaintenanceOrderDto body) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }


    @ApiOperation(value = "Remove a existent maintenance order",
            nickname = "removeMaintenanceOrder",
            notes = "No content response",
            response = ResponseEntity.class,
            tags={ "Maintenance Order", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Maintenance order created with success", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @DeleteMapping(value="/{id}", produces = { "application/json" })
    default  ResponseEntity<MaintenanceOrder> removeMaintenanceOrder(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
