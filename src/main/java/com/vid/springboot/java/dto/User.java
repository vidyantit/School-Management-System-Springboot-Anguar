package com.vid.springboot.java.dto;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class User {
    @Id
	private String userId;
	private String name;
	private String email;
}
