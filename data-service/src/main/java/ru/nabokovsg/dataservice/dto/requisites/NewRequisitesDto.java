package ru.nabokovsg.dataservice.dto.requisites;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные новых реквизитов")
public class NewRequisitesDto {

    @Schema(description = "Индентификатор адреса")
    @NotNull(message = "id organization's address must not be null")
    @Positive(message = "id organization's address must be negative")
    private Long addressId;
    @Schema(description = "Почтовый индекс")
    @NotNull(message = "index should not be blank")
    @Positive(message = "index must be positive")
    private Integer index;
    @Schema(description = "Номер телефона")
    @NotBlank(message = "phone should not be blank")
    private String phone;
    @Schema(description = "Факс")
    @NotBlank(message = "fax should not be blank")
    private String fax;
    @Schema(description = "электронная почта")
    @NotBlank(message = "email should not be blank")
    @Email(message = "email invalid")
    private String email;
}