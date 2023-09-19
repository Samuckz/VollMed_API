package med.voll.api.controller;

import med.voll.api.models.usuarios.dtos.LoginDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity efeutarLogin(@RequestBody @Valid LoginDTO login){
        var token = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getSenha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
