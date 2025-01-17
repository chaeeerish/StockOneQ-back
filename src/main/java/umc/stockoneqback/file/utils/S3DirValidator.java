package umc.stockoneqback.file.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class S3DirValidator implements ConstraintValidator<ValidateDir, String> {
    private static final List<String> VALID_DIR = List.of("board", "share");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return VALID_DIR.contains(value);
    }
}
