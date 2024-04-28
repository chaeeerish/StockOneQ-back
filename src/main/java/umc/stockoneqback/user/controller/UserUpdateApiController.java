package umc.stockoneqback.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.user.dto.request.UpdatePasswordRequest;
import umc.stockoneqback.user.dto.request.UserInfoRequest;
import umc.stockoneqback.user.dto.request.ValidateUpdatePasswordRequest;
import umc.stockoneqback.user.service.UserUpdateService;
import umc.stockoneqback.user.dto.response.UpdatePasswordResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserUpdateApiController {
    private final UserUpdateService userUpdateService;

    @PutMapping("/update")
    public ResponseEntity<Void> updateInformation(@Auth Authenticated authenticated, @RequestBody @Valid UserInfoRequest request) {
        userUpdateService.updateInformation(authenticated.id(), request.name(), request.birth(), request.email(), request.loginId(), request.password(), request.phoneNumber());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/update/password")
    public ResponseEntity<UpdatePasswordResponse> validateUpdatePassword(@RequestBody @Valid ValidateUpdatePasswordRequest request) {
        UpdatePasswordResponse response = userUpdateService.validateUpdatePassword(request.name(), request.birth(), request.loginId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        userUpdateService.updatePassword(request.loginId(), request.newPassword(), request.validate());
        return ResponseEntity.ok().build();
    }
}
