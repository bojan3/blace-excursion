package com.blace.excursion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PastExcursionKey implements Serializable {

	@Column
	private Long clientId;
	@Column
	private Long excursionId;

	public PastExcursionKey(Long clientId, Long excursionId) {
		super();
		this.clientId = clientId;
		this.excursionId = excursionId;
	}

	public PastExcursionKey() {
		super();
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
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
		return "PastExcursionKey [clientId=" + clientId + ", excursionId=" + excursionId + "]";
	}

}
