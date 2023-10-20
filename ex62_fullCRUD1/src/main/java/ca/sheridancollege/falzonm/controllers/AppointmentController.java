package ca.sheridancollege.falzonm.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.falzonm.beans.Appointment;
import ca.sheridancollege.falzonm.database.DatabaseAccess;

@Controller
public class AppointmentController {
	
	@Autowired
	private DatabaseAccess da;
	
	List<Appointment>apptList = new CopyOnWriteArrayList<>();
	
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("appt", new Appointment());
		model.addAttribute("apptList", da.getAllAppointment());
		da.insertAppointment(new Appointment());
		return "index";
	}
	
	
	@GetMapping("/deleteAppointmentById/{id}")
	public String deleteAppointmentById(Model model, @PathVariable Long id) {
		model.addAttribute("appt", new Appointment());
		model.addAttribute("apptList", da.getAllAppointment());
		da.deleteAppointmentById(id);
		return "index";
	}
	
	
	@GetMapping("getAppointmentById/{id}")
	public String getAppointmentById(Model model, @PathVariable Long id) {
	Appointment appt = da.getAppointmentById(id).get(0);
		
		model.addAttribute("appt", appt);
		
		// Delete the existing student record
		da.deleteAppointmentById(id);
		// Refresh the student list
		model.addAttribute("apptList", da.getAllAppointment());

		
		return "index";
	}
	
	@PostMapping("/insertAppointment")
	public String insertAppointment(Model model, @ModelAttribute Appointment appt) {
		
		List<Appointment> existingAppointments = da.getAppointmentById(appt.getId());
		
		if(existingAppointments.isEmpty()) {
			da.insertAppointment(appt);
		}
		
		model.addAttribute("apptList", da.getAllAppointment());
		model.addAttribute("appt", new Appointment());
		
		
		return "index";
	}
	
	
	@GetMapping("/editAppointmentById/{id}")
	public String editAppointmentById(Model model, @PathVariable Long id) {
		Appointment appt = da.getAppointmentById(id).get(0);
			
			model.addAttribute("appt", appt);
			
			// Delete the existing student record
			da.deleteAppointmentById(id);
			// Refresh the student list
			model.addAttribute("studentList", da.getAllAppointment());

			
			return "index";
		}
		

}
