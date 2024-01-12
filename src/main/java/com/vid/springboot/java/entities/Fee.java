package com.vid.springboot.java.entities;



import java.util.Date;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Fee_Table")
public class Fee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String month;
	private String givenBy;
	private Long amount;
	private String description;
	private Date createdDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
}
