package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private DataSize dataSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.dataSize = DataSize.parse(constraintAnnotation.max());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value.getSize() <= this.dataSize.toBytes()) {
            return true;
        } else {
            return false;
        }
    }
}
