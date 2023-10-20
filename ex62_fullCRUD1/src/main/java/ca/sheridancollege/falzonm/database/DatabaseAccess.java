package ca.sheridancollege.falzonm.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.falzonm.beans.Appointment;

@Repository
public class DatabaseAccess {
	
	@Autowired 
	protected NamedParameterJdbcTemplate jdbc;
	

	
	//insertAppointment
	public void insertAppointment(Appointment appt) {
		//holds a given Map of parameters. intended for passing in a simple Map of Parameter values to the methods of the NamedParameter JDBC template
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		namedParameters.addValue("apptFirstName", appt.getFirstName());
		namedParameters.addValue("apptEmail", appt.getEmail());
		namedParameters.addValue("apptApptDate", appt.getApptDate());
		namedParameters.addValue("apptApptTime", appt.getApptTime());
		
		//This string is used to insert data into the database
		String query = "INSERT INTO appt(firstName, email, apptDate, apptTime) VALUES (:apptFirstName, :apptEmail, :apptApptDate, :apptApptTime)" ;
	
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if (rowsAffected > 0) System.out.println("Appointment inserted into database");
	}
	
	//getAllAppointment by using an Array
	public List<Appointment> getAllAppointment(){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM appt";
		
		//need a return statement for this as it uses row mapper
		//the row mapper converts the row into a new instance
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
	}
	
	//deleteAppointmentById
	public void deleteAppointmentById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "DELETE FROM appt WHERE id = :id";
		
		namedParameters.addValue("id", id);
		
		if(jdbc.update(query, namedParameters) > 0 ) {
			System.out.println("Deleted appointment " + id + " from the database");
		}
	}
	
	
	//getAppointmentById
	public List<Appointment> getAppointmentById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM appt WHERE id = :id";
		namedParameters.addValue("id", id);
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
		
	}
	


}
