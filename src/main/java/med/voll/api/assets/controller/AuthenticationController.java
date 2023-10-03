package med.voll.api.assets.controller;

import jakarta.validation.Valid;
import med.voll.api.assets.service.TokenService;
import med.voll.api.assets.dto.security.DataTokenJWT;
import med.voll.api.assets.dto.user.AuthenticationData;
import med.voll.api.assets.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AuthenticationData data) {
        var tokenAuthenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(tokenAuthenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

}
