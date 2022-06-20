package com.dmm.task.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TasksRepository;

@Controller
public class MainController {
	
	
	@Autowired
	private TasksRepository tasksRepository;
	
	
	@GetMapping("/main")
	public String mainIndex(Model model) {
//		return "main";
		
		
		
		
		System.out.println("-----------------------------------TEST----------------------------");
		System.out.println(tasksRepository);
		
		List<Tasks> tasks = tasksRepository.findAll();
		// 取得したリストをテンプレートに渡す
		model.addAttribute("tasks", tasks);
		System.out.println(tasks);
		return "main";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
