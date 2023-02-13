package com.blace.excursion.dto.excursion;

import com.blace.excursion.dto.LocationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExcursionDTO {

    private Long id;

    @JsonFormat(pattern = "dd.MM.yyyy.")

    private Date date;

    private Integer maxNumberOfPersons;

    private Integer numberOfPerosns;

    private Integer price;

    private String tourGuideName;

    private List<LocationDTO> locations;

    private String mealName;

    private String restaurantName;
}
