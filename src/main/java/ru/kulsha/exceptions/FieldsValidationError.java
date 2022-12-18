package ru.kulsha.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class FieldsValidationError {
    private List<String> errorFieldMessages;

    public FieldsValidationError(List<String> errorFieldMessages) {
        this.errorFieldMessages = errorFieldMessages;
    }
}
