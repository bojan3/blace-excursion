package com.blace.excursion.dto.excursion;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateExcursionDTO {

    @NotNull
    private Date date;

    @NotNull
    private Integer maxNumberOfPersons;

    @NotNull
    private Integer price;

    @NotNull
    private List<Long> locationIds;

    @NotNull
    private Integer minNumberOfPersons;

    private Long mealId;

    @NotNull
    private List<Long> vehicleIds;

}
