package com.example.errorhandling.exception.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvalidParameterDto {

    private String parameter;
    private String message;

}
