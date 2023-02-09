package com.blace.excursion.dto.excursion;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ExcursionFilter {

    private Integer minimumTicketPrice;

    private Integer maximumTicketPrice;

    private Integer minimumTickets;

    private Date fromDate;

    private Date toDate;

    @NotNull
    private Integer pageSize;

    @NotNull
    private Integer pageIndex;

}
