package umc.stockoneqback.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.controller.dto.request.LoginRequest;
import umc.stockoneqback.auth.controller.dto.request.SaveFcmRequest;
import umc.stockoneqback.auth.service.dto.response.AuthMember;
import umc.stockoneqback.auth.service.jwt.AuthService;
import umc.stockoneqback.global.annotation.ExtractPayload;

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
    public ResponseEntity<Void> logout(@ExtractPayload Long userId) {
        authService.logout(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/fcm")
    public ResponseEntity<Void> saveFcm(@ExtractPayload Long userId, @RequestBody @Valid SaveFcmRequest saveFcmRequest) {
        authService.saveFcm(userId, saveFcmRequest.fcmToken());
        return ResponseEntity.ok().build();
    }
}
