package energy.viridis.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import energy.viridis.exercise.model.Equipment;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findByName(String name);
}
