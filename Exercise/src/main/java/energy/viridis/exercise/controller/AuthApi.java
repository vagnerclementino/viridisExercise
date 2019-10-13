package energy.viridis.exercise.controller;

import energy.viridis.exercise.security.AuthenticationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "equipments", description = "the Auth API")
@RequestMapping("api/auth")
public interface AuthApi {

    @ApiOperation(value = "Auth a user",
                  nickname = "signin",
                  response = ResponseEntity.class,
                  tags={ "Auth", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Auth", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "User Not Found"),
            @ApiResponse(code = 500, message = "Internal Error") })
    @PostMapping(value= "/signin", produces = { "application/json" }, consumes = { "application/json" }
    )
    default ResponseEntity signin(@RequestBody AuthenticationRequest data){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
