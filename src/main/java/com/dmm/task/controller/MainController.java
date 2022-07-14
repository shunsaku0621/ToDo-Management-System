package com.dmm.task.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TasksRepository;

@Controller
public class MainController {
	
	
	@Autowired
	private TasksRepository tasksRepository;
	
	
	@GetMapping("/main")
	public String mainIndex(Model model, @RequestParam(name = "date", defaultValue = "") String date) {
		
		MultiValueMap<LocalDate, Tasks> toDoList = new LinkedMultiValueMap<LocalDate, Tasks>();
		List<Tasks> task = tasksRepository.findAll();
		List<List<LocalDate>> matrix = new ArrayList<>();
		List<LocalDate> week = new ArrayList<>();
		
		LocalDate local_date;
		
		
		LocalDate d1 = LocalDate.now().withDayOfMonth(1);
		
		if(date.isEmpty()) {
			local_date = d1;
		} else {
			local_date = LocalDate.parse(date);
		}
		
		if(date.isEmpty()) {
			model.addAttribute("prev", d1.minusMonths(1));
			model.addAttribute("next", d1.plusMonths(1));
		}else {
			model.addAttribute("prev", local_date.minusMonths(1));
			model.addAttribute("next", local_date.plusMonths(1));
		}
		
		if(date != "") {
			d1 = local_date;
		}
		
		DateTimeFormatter date2 = DateTimeFormatter.ofPattern("yyyy年M月");
		String stringDate2 = d1.format(date2);
	
		model.addAttribute("month", stringDate2);
		
		DayOfWeek w = d1.getDayOfWeek(); 

		LocalDate startDate = d1.minusDays(w.getValue());
		
		d1 = startDate;
		
		for(int i = 0; i < 7; i++) {
			week.add(d1);
			
			for(int t = 0; t < task.size(); t++) {
				if(task.get(t).getDate().isEqual(d1)) {
					toDoList.add(d1,task.get(t));
				}
			}
			d1 = d1.plusDays(1);
		}
		matrix.add(week);
		
		week  = new ArrayList<>();
		for(int l = 0; l < d1.lengthOfMonth(); l++) {
			DayOfWeek dw = d1.getDayOfWeek();
			week.add(d1);
			
			for(int t = 0; t < task.size(); t++) {
				if(task.get(t).getDate().isEqual(d1)) {
					toDoList.add(d1,task.get(t));
				}
			}
			
			d1 = d1.plusDays(1);
			
			if (dw == DayOfWeek.SATURDAY) {
				matrix.add(week);
				week = new ArrayList<>();
			}
		}
		model.addAttribute("matrix", matrix);
		model.addAttribute("tasks", toDoList);
		
		return "main";
	}
	
}
