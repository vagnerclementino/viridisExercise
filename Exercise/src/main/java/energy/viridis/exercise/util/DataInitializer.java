package energy.viridis.exercise.util;

import energy.viridis.exercise.model.Equipment;
import energy.viridis.exercise.model.MaintenanceOrder;
import energy.viridis.exercise.model.User;
import energy.viridis.exercise.repository.EquipmentRepository;
import energy.viridis.exercise.repository.MaintenanceOrderRepository;
import energy.viridis.exercise.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EquipmentRepository equipmentRepository;

    private final MaintenanceOrderRepository maintenanceOrderRepository;

    @Value("${security.jwt.default-user}")
    private String defaultUser;

    @Value("${security.jwt.default-user-password}")
    private String defaultUserPassword;

    @Autowired
    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, EquipmentRepository equipmentRepository, MaintenanceOrderRepository maintenanceOrderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.equipmentRepository = equipmentRepository;
        this.maintenanceOrderRepository = maintenanceOrderRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        createOrRetriveUser(defaultUser, defaultUserPassword);

        Optional<Equipment> equipmentOptional = createOrRetrieveEquipment("Teste Equipmente");



        equipmentOptional.ifPresent(equipment -> {

            Date scheduledDate = DateUtils.getCurrentTime();
            createOrRetrieveMaintenanceOrder(scheduledDate, equipment);
        });


        log.debug("printing all userRepository...");
        this.userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }

    private Optional<User> createOrRetriveUser(String userName, String userPassword){
        Optional <User> userOptional = userRepository.findByUsername(userName);

        if(!userOptional.isPresent()){

            User createdUser = userRepository.save(User.builder()
                                            .username(userName)
                                            .password(passwordEncoder.encode(userPassword))
                                            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                                            .build()
                                            );
            return Optional.ofNullable(createdUser);
        }else {
            return userOptional;
        }

    }

    private Optional<Equipment> createOrRetrieveEquipment(String equipmentName){

        Equipment equipmentSaved = null;

        Optional <Equipment> equipmentOptional = equipmentRepository.findByName(equipmentName);

        if(!equipmentOptional.isPresent()){
            equipmentSaved = equipmentRepository.save(Equipment.builder().name(equipmentName).build());
        }

        return Optional.ofNullable(equipmentSaved);

    }

    private Optional<MaintenanceOrder> createOrRetrieveMaintenanceOrder(Date scheduledDate, Equipment equipment){

        MaintenanceOrder maintenanceOrderSaved = null;

        Optional <MaintenanceOrder> equipmentOptional = maintenanceOrderRepository.findByScheduledDateAndEquipment(scheduledDate, equipment);

        if(!equipmentOptional.isPresent()){
            maintenanceOrderSaved = maintenanceOrderRepository
                                        .save(MaintenanceOrder
                                                .builder()
                                                .scheduledDate(scheduledDate)
                                                .equipment(equipment)
                                                .build());
        }

        return Optional.ofNullable(maintenanceOrderSaved);

    }
}
