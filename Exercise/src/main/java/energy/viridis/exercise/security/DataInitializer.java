package energy.viridis.exercise.security;

import energy.viridis.exercise.model.User;
import energy.viridis.exercise.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Value("${security.jwt.default-user}")
    private String defaultUser;

    @Value("${security.jwt.default-user-password}")
    private String defaultUserPassword;

    @Autowired
    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {


        Optional <User> userOptional = userRepository
                                            .findByUsername(defaultUser);

        if(!userOptional.isPresent()){

            userRepository.save(User.builder()
                    .username(defaultUser)
                    .password(passwordEncoder.encode(defaultUserPassword))
                    .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                    .build()
            );
        }
        log.debug("printing all userRepository...");
        this.userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
