package umc.stockoneqback.admin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umc.stockoneqback.admin.dto.request.AddFARequest;
import umc.stockoneqback.admin.service.AdminStaticService;
import umc.stockoneqback.auth.domain.model.jwt.Authenticated;
import umc.stockoneqback.global.annotation.Auth;
import umc.stockoneqback.global.annotation.ExtractPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminStaticApiController {
    private final AdminStaticService AdminStaticService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/fa")
    public ResponseEntity<Void> addFA(@Auth Authenticated authenticated,
                                      @Valid @RequestBody AddFARequest addFARequest) {
        AdminStaticService.addFA(addFARequest);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/fa")
    public ResponseEntity<Void> deleteFA(@Auth Authenticated authenticated,
                                         @RequestParam(value = "question") String question) {
        AdminStaticService.deleteFA(question);
        return ResponseEntity.ok().build();
    }
}
