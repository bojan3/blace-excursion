package com.blace.excursion.dto;

import com.blace.excursion.model.PastExcursion;
import com.blace.excursion.model.PastExcursionKey;

public class PastExcursionDTO {

	private PastExcursionKey id;
	private ExcursionDTO excursionDTO;
	private Integer commentsCount;
	private Integer numberOfPersons;
	
	public PastExcursionDTO(PastExcursion pastExcursion) {
		super();
		this.id = pastExcursion.getId();
		this.excursionDTO = new ExcursionDTO(pastExcursion.getExcursion());
		this.commentsCount = pastExcursion.getComments().size();
		this.numberOfPersons = pastExcursion.getNumberOfPersons();
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}



	public PastExcursionKey getId() {
		return id;
	}

	public void setId(PastExcursionKey id) {
		this.id = id;
	}

	public ExcursionDTO getExcursionDTO() {
		return excursionDTO;
	}

	public void setExcursionDTO(ExcursionDTO excursionDTO) {
		this.excursionDTO = excursionDTO;
	}

	public Integer getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	@Override
	public String toString() {
		return "PastExcursionDTO [id=" + id + ", excursionDTO=" + excursionDTO + ", commentsCount=" + commentsCount
				+ "]";
	}

}
