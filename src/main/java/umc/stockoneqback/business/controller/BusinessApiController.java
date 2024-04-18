package umc.stockoneqback.business.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.business.service.BusinessService;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.annotation.ExtractPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class BusinessApiController {
    private final BusinessService businessService;

    @PreAuthorize("hasRole('SUPERVISOR')")
    @PostMapping("/{managerId}")
    public ResponseEntity<Void> register(@Auth Authenticated authenticated, @PathVariable Long managerId) {
        businessService.register(authenticated.id(), managerId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('SUPERVISOR')")
    @DeleteMapping("/{managerId}")
    public ResponseEntity<Void> cancel(@Auth Authenticated authenticated, @PathVariable Long managerId) {
        businessService.cancel(authenticated.id(), managerId);
        return ResponseEntity.ok().build();
    }
}
