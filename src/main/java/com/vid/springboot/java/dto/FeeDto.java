package com.vid.springboot.java.dto;


import java.util.Date;

import lombok.Data;

@Data
public class FeeDto {

	private Long id;
	private String month;
	private String givenBy;
	private Long amount;
	private String description;
	private Date createdDate;
	private Long studentId;
}
