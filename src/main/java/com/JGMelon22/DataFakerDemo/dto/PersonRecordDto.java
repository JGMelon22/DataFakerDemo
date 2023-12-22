package com.JGMelon22.DataFakerDemo.dto;

import jakarta.validation.constraints.NotEmpty;

public record PersonRecordDto(@NotEmpty(message = "Informe o primeiro nome") String firstName, @NotEmpty String lastName, @NotEmpty String address) {
}
