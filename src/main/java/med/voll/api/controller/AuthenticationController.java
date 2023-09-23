package med.voll.api.controller;

import med.voll.api.models.usuarios.UsuarioModel;
import med.voll.api.models.usuarios.dtos.LoginDTO;
import med.voll.api.utils.security.TokenJWT;
import med.voll.api.utils.security.TokenService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    // Classe para disparar o servico de autenticacao

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efeutarLogin(@RequestBody @Valid LoginDTO login){
        try{
            // DTO do Spring Security para dados de authenticacao
            var authenticationToken = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getSenha());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.generateToken((UsuarioModel) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenJWT(tokenJWT));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
