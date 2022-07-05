package com.dmm.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.repository.TasksRepository;

@Controller
public class DeleteController {
	@Autowired
	private TasksRepository tasksRepository;
	
	
	@PostMapping("/main/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		tasksRepository.deleteById(id);
		return "redirect:/main";
	}
}
