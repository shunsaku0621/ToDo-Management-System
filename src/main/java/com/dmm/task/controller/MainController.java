package com.dmm.task.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TasksRepository;

@Controller
public class MainController {
	
	
	@Autowired
	private TasksRepository tasksRepository;
	
	
	@GetMapping("/main")
	public String mainIndex(Model model) {
		
		MultiValueMap<LocalDate, Tasks> params1 = new LinkedMultiValueMap<LocalDate, Tasks>();
		List<Tasks> task = tasksRepository.findAll();
		
		System.out.println("---------------------カレンダー表示機能------------------------");
		
		List<List<LocalDate>> matrix = new ArrayList<>();
		List<LocalDate> week = new ArrayList<>();
		
		LocalDate d1 = LocalDate.now().withDayOfMonth(1);
		
		DayOfWeek w = d1.getDayOfWeek(); 

		LocalDate startDate = d1.minusDays(w.getValue());
		
		d1 = startDate;
		
		for(int i = 0; i < 7; i++) {
			week.add(d1);
			
			for(int t = 0; t < task.size(); t++) {
				if(task.get(t).getDate().isEqual(d1)) {
					params1.add(d1,task.get(t));
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
					params1.add(d1,task.get(t));
				}
			}
			
			d1 = d1.plusDays(1);
			
			if (dw == DayOfWeek.SATURDAY) {
				matrix.add(week);
				week = new ArrayList<>();
			}
		}
		
		model.addAttribute("matrix", matrix);
		model.addAttribute("tasks", params1);
		
		return "main";
	}
}
