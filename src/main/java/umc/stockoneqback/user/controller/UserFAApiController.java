package umc.stockoneqback.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.base.BaseResponse;
import umc.stockoneqback.user.service.UserFAService;
import umc.stockoneqback.user.service.dto.response.GetFAResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserFAApiController {
    private final UserFAService userFAService;

    @PreAuthorize("hasAnyRole('MANAGER', 'PART_TIMER', 'SUPERVISOR')")
    @GetMapping("/fa")
    public BaseResponse<List<GetFAResponse>> getFA(@Auth Authenticated authenticated) {
        return new BaseResponse<>(userFAService.getFA());
    }
}
