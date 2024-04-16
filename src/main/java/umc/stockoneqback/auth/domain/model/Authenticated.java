package umc.stockoneqback.auth.domain.model;

import java.util.List;

public record Authenticated(
        Long id,
        String name,
        List<String> roles
) {
}
