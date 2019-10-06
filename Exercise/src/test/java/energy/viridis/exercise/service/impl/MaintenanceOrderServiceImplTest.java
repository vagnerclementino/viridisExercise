package energy.viridis.exercise.service.impl;

import energy.viridis.exercise.dto.EquipmentDto;
import energy.viridis.exercise.dto.MaintenanceOrderDto;
import energy.viridis.exercise.error.ExerciseException;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.repository.MaintenanceOrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MaintenanceOrderServiceImplTest {

    private MaintenanceOrderServiceImpl maintenanceOrderService;
    @Mock
    private MaintenanceOrderRepository mockMaintenanceOrderRepoitory;
    @Mock
    private MaintenanceOrderDto mockBody;
    @Mock
    private EquipmentServiceImpl mockEquipmentServide;
    @Mock
    private Equipment mockEquipment;
    @Mock
    private EquipmentDto mockEquipmentDto;


    @BeforeEach
    void setUp() {

        mockMaintenanceOrderRepoitory = mock(MaintenanceOrderRepository.class);
        mockEquipmentServide = mock(EquipmentServiceImpl.class);
        maintenanceOrderService = new MaintenanceOrderServiceImpl(mockMaintenanceOrderRepoitory, mockEquipmentServide);
        mockBody = mock(MaintenanceOrderDto.class);
        mockEquipment = mock(Equipment.class);
        mockEquipmentDto = mock(EquipmentDto.class);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetMaintenanceOrderByIdThowsConstraintViolationExceptionWhenIdIsLessThanOne() {
        Long invalidId = Long.valueOf(0);

        when(mockMaintenanceOrderRepoitory.findById(invalidId)).thenThrow(new NoSuchElementException("MaintenanceOrder not found."));
        assertThrows(ExerciseException.class, () ->
                maintenanceOrderService.getMaintenanceOrderById(invalidId));
    }

    @Test
    void shouldGetAllMaintenanceOrdersCallfindAllMethod() {
        maintenanceOrderService.getMaintenanceOrderList();
        verify(mockMaintenanceOrderRepoitory, times(1)).findAll();
    }

    @Test
    void shouldSaveMaintenanceOrderCallRepositorySave() {
        Long equipmentId = Long.valueOf(99);

        when(mockBody.getEquipmentDto()).thenReturn(mockEquipmentDto);
        when(mockEquipmentDto.getId()).thenReturn(equipmentId);
        when(mockEquipmentServide.getEquipmentById(equipmentId)).thenReturn(mockEquipment);

        maintenanceOrderService.saveMaintenanceOrder(mockBody);
        verify(mockMaintenanceOrderRepoitory, times(1)).save(any(MaintenanceOrder.class));
    }

    @Test
    void shouldUpdateMaintenanceOrderThrowsNoSuchElementExceptionWhenMaintenanceOrderNotExits() {
        Long invalidId = Long.valueOf(-1);

        when(mockMaintenanceOrderRepoitory.findById(invalidId)).thenThrow(new NoSuchElementException("MaintenanceOrder not found."));
        assertThrows(ExerciseException.class, () ->
                maintenanceOrderService.updateMaintenanceOrder(invalidId, mockBody));
    }

    @Test
    void shouldRemoveMaintenanceOrderThrowsNoSuchElementExceptionWhenMaintenanceOrderNotExits() {
        Long invalidId = Long.valueOf(-1);

        when(mockMaintenanceOrderRepoitory.findById(invalidId)).thenThrow(new NoSuchElementException("MaintenanceOrder not found."));
        assertThrows(ExerciseException.class, () ->
                maintenanceOrderService.removeMaintenanceOrder(invalidId));
    }

}