package energy.viridis.exercise.repository;

import energy.viridis.exercise.model.Equipment;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")

class EquipmentRepositoryTest {

    @Autowired
    private EquipmentRepository subject;

    @After
    public void tearDown() throws Exception {
        subject.deleteAll();
    }

    @Test
    public void shouldSaveAndFetchEquipment() throws Exception {
        Equipment testEquipment = new Equipment()
                                        .withName("Test Equipment");
        subject.save(testEquipment);

        Optional<Equipment> maybeSavedEquipment = subject.findByName(testEquipment.getName());

        assertThat(maybeSavedEquipment, is(Optional.of(testEquipment)));
    }

    @Test
    public void shouldUpdateAndFetchEquipmentWithNewName() throws Exception {
        Equipment testEquipment = new Equipment()
                .withName("Old Name");
        subject.save(testEquipment);

        Optional<Equipment> maybeSavedEquipment = subject.findByName(testEquipment.getName());

        maybeSavedEquipment.ifPresent(equipment -> equipment.setName("New Name"));
        subject.save(maybeSavedEquipment.get());

        assertThat(maybeSavedEquipment.get().getName(), is("New Name"));

    }

    @Test
    public void shouldDeleteAndNoFetchEquipment() throws Exception {
        Equipment testEquipment = new Equipment()
                .withName("Test Equipment");
        subject.save(testEquipment);

        Optional<Equipment> maybeSavedEquipment = subject.findByName(testEquipment.getName());
        maybeSavedEquipment.ifPresent(equipment -> subject.deleteById(equipment.getId()));


        Optional<Equipment> maybeDeleteEquipment = subject.findByName(testEquipment.getName());


        assertThat(maybeDeleteEquipment.isPresent(), is(false));
    }

    @Test
    public void shouldThrowWhenEquipmentIsNull() throws Exception {
        Equipment testEquipment = new Equipment()
                .withName(null);
        assertThrows(ConstraintViolationException.class, () -> subject.save(testEquipment));
    }

}