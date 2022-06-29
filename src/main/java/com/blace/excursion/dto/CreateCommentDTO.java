package com.blace.excursion.dto;

public class CreateCommentDTO {
	private String text;
	private Integer rate;
	private Long ExcursionId;

	public CreateCommentDTO(String text, Integer rate, Long excursionId) {
		super();
		this.text = text;
		this.rate = rate;
		ExcursionId = excursionId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Long getExcursionId() {
		return ExcursionId;
	}

	public void setExcursionId(Long excursionId) {
		ExcursionId = excursionId;
	}
}
