package energy.viridis.exercise.service.impl;

import energy.viridis.exercise.dto.EquipmentDto;
import energy.viridis.exercise.error.ExerciseException;
import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.repository.EquipmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EquipmentServiceImplTest {

    private EquipmentServiceImpl equipmentService;
    @Mock
    private EquipmentRepository mockEquipmentRepoitory;
    @Mock
    private EquipmentDto mockBody;
    @Mock
    private Equipment mockEquipment;


    @BeforeEach
    void setUp() {

        mockEquipmentRepoitory = mock(EquipmentRepository.class);
        equipmentService = new EquipmentServiceImpl(mockEquipmentRepoitory);
        mockBody = mock(EquipmentDto.class);
        mockEquipment = mock(Equipment.class);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetEquipmentByIdThowsConstraintViolationExceptionWhenIdIsLessThanOne() {
        Long invalidId = Long.valueOf(0);

        when(mockEquipmentRepoitory.findById(invalidId)).thenThrow(new NoSuchElementException("Equipment not found."));
        assertThrows(ExerciseException.class, () ->
                     equipmentService.getEquipmentById(invalidId));
    }

    @Test
    void shouldGetAllEquipmentsCallfindAllMethod() {
        equipmentService.getAllEquipments();
        verify(mockEquipmentRepoitory, times(1)).findAll();
    }

    @Test
    void shouldSaveEquipmentCallRepositorySave() {
        String equipmentName = "Equipment Name";
        when(mockBody.getName()).thenReturn(equipmentName);
        equipmentService.saveEquipment(mockBody);
        verify(mockEquipmentRepoitory, times(1)).save(any(Equipment.class));
    }

    @Test
    void shouldUpdateEquipmentThrowsNoSuchElementExceptionWhenEquipmentNotExits() {
        Long invalidId = Long.valueOf(-1);

        when(mockEquipmentRepoitory.findById(invalidId)).thenThrow(new NoSuchElementException("Equipment not found."));
        assertThrows(ExerciseException.class, () ->
                equipmentService.updateEquipment(invalidId, mockBody));
    }

    @Test
    void shouldRemoveEquipmentThrowsNoSuchElementExceptionWhenEquipmentNotExits() {
        Long invalidId = Long.valueOf(-1);

        when(mockEquipmentRepoitory.findById(invalidId)).thenThrow(new NoSuchElementException("Equipment not found."));
        assertThrows(ExerciseException.class, () ->
                equipmentService.removeEquipment(invalidId));
    }

}