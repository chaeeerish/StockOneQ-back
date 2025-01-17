package umc.stockoneqback.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.user.domain.Email;
import umc.stockoneqback.user.dto.request.FindLoginIdRequest;
import umc.stockoneqback.user.dto.response.LoginIdResponse;
import umc.stockoneqback.user.dto.response.UserInformationResponse;
import umc.stockoneqback.user.service.UserInformationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserInformationApiController {
    private final UserInformationService userInformationService;

    @GetMapping("/find-id")
    public ResponseEntity<LoginIdResponse> findLoginId(@RequestBody @Valid FindLoginIdRequest request) {
        LoginIdResponse response = userInformationService.findLoginId(request.name(), request.birth(), Email.from(request.email()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/information")
    public ResponseEntity<UserInformationResponse> getInformation(@Auth Authenticated authenticated) {
        UserInformationResponse response = userInformationService.getInformation(authenticated.id());
        return ResponseEntity.ok(response);
    }
}
