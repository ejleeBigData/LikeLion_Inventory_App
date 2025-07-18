package com.leeej.inventory_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    @NotBlank(message = "제품명을 입력해주세요.")
    private String name;

    @NotNull(message = "가격을 입력해주세요.")
    @PositiveOrZero(message = "가격은 0보다 커야 합니다.")
    private BigDecimal price;

}
