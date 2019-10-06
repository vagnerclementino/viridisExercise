package energy.viridis.exercise.repository;

import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.util.DateUtils;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class MaintenanceOrderRepositoryTest {

    @Autowired
    private MaintenanceOrderRepository subject;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @After
    public void tearDown() throws Exception {
        subject.deleteAll();
    }

    @Test
    public void shouldSaveAndFetchEquipment() throws Exception {
        MaintenanceOrder testMaintenanceOrder = builMaintenanceOrderToTest();
        MaintenanceOrder maybeSavedMaintenanceOrder =   subject.save(testMaintenanceOrder);

        Optional<MaintenanceOrder> fetchMaintenanceOrder = subject.findById(maybeSavedMaintenanceOrder.getId());

        assertThat(fetchMaintenanceOrder, is(Optional.of(testMaintenanceOrder)));
    }



    @Test
    public void shouldUpdateAndFetchEquipmentWithNewName() throws Exception {

        MaintenanceOrder testMaintenanceOrder = builMaintenanceOrderToTest();
        MaintenanceOrder maybeSavedMaintenanceOrder =  subject.save(testMaintenanceOrder);


        Date newScheduledDate = DateUtils.addHoursToUtilDate(maybeSavedMaintenanceOrder.getScheduledDate(), 1);


        maybeSavedMaintenanceOrder.setScheduledDate(newScheduledDate);
        subject.save(maybeSavedMaintenanceOrder);

        assertThat(maybeSavedMaintenanceOrder.getScheduledDate(), is(newScheduledDate));

    }

    @Test
    public void shouldDeleteAndNoFetchEquipment() throws Exception {

        MaintenanceOrder testMaintenanceOrder = builMaintenanceOrderToTest();
        MaintenanceOrder maybeSavedMaintenanceOrder =  subject.save(testMaintenanceOrder);

        subject.deleteById(maybeSavedMaintenanceOrder.getId());


        Optional<MaintenanceOrder> maybeDeleteEquipment = subject.findById(maybeSavedMaintenanceOrder.getId());


        assertThat(maybeDeleteEquipment.isPresent(), is(false));
    }

    private MaintenanceOrder builMaintenanceOrderToTest() {
        Equipment testEquipment = new Equipment()
                .withName("Test Equipment");
        equipmentRepository.save(testEquipment);
        MaintenanceOrder testMaintenanceOrder = new MaintenanceOrder();
        testMaintenanceOrder.setEquipment(testEquipment);
        testMaintenanceOrder.setScheduledDate(DateUtils.getCurrentTime());
        return testMaintenanceOrder;
    }



}