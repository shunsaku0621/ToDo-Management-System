package com.dmm.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TasksRepository;

@Controller
public class EditController {
	
	@Autowired
	private TasksRepository tasksRepository;
	
	
	@GetMapping("/main/edit/{id}")
	public String getEditTask(Model model, @PathVariable int id) {
		
		
		List<Tasks> tasks = tasksRepository.findByDateBetween(id, "user-name");
		
		
		
		// Modelに空のUserFormを追加
//		List<Tasks> task = tasksRepository.findAll();
		model.addAttribute("task", tasks);
		System.out.println(tasks);
		System.out.println(model.addAttribute("task", tasks.get(0)));
		// テンプレートは src/main/resources/templates/newuser.html とします。
		return "edit";
	}
	
	
	
	
//	@GetMapping("/main/edit/{id}")
//	public String editToDo() {
//		return "redirect:/main";
//	}
}
