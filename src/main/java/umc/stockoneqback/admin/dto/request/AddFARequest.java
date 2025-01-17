package umc.stockoneqback.admin.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddFARequest(
        @NotNull @Valid
        List<AddFAKeyValue> addFAKeyValueList
) {
    public record AddFAKeyValue(
            @NotBlank
            String question,

            @NotBlank
            String answer
    ){}
}
