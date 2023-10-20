package ca.sheridancollege.falzonm.beans;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
	private Long id;
	private String firstName;
	private String email;
	
	//DateTimeFormat used to properly bind with Thymeleaf as a model object
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate apptDate;
	
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime apptTime;

}
