package com.blace.excursion.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReservationKey implements Serializable {

    @Column
    private Long clientId;
    @Column
    private Long excursionId;

    public ReservationKey(Long clientId, Long excursionId) {
        super();
        this.clientId = clientId;
        this.excursionId = excursionId;
    }

    public ReservationKey() {
        super();
    }

    public Long getClinetId() {
        return clientId;
    }

    public void setClinetId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }

    @Override
    public String toString() {
        return "ReservationKey [clientId=" + clientId + ", excursionId=" + excursionId + "]";
    }

}
