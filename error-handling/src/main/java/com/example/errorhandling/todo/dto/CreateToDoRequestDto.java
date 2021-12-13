package com.example.errorhandling.todo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateToDoRequestDto {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String title;

}
