package com.leeej.inventory_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDto {
    @NotBlank(message = "창고 이름을 입력하세요.")
    private String name;

    @Size(max = 200, message = "위치 정보는 최대 200자까지 허용됩니다")
    private String location;
}
