package umc.stockoneqback.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.auth.dto.request.LoginRequest;
import umc.stockoneqback.auth.dto.request.SaveFcmRequest;
import umc.stockoneqback.auth.dto.response.AuthMember;
import umc.stockoneqback.auth.service.jwt.AuthService;
import umc.stockoneqback.global.annotation.Auth;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthMember> login(@RequestBody @Valid LoginRequest request) {
        AuthMember authMember = authService.login(request.loginId(), request.password());
        return ResponseEntity.ok(authMember);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Auth Authenticated authenticated) {
        authService.logout(authenticated.id());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/fcm")
    public ResponseEntity<Void> saveFcm(@Auth Authenticated authenticated,
                                        @RequestBody @Valid SaveFcmRequest saveFcmRequest) {
        authService.saveFcm(authenticated.id(), saveFcmRequest.fcmToken());
        return ResponseEntity.ok().build();
    }
}
