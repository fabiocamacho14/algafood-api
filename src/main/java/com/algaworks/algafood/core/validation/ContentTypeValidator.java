package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;


public class ContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private String[] allowed;

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        String contentType = value.getContentType();

        for (String allow : allowed) {
            if (contentType.equals(allow)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowed =  constraintAnnotation.allowed();
    }

}
