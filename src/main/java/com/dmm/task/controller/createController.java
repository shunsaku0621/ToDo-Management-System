package com.dmm.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TasksRepository;
import com.dmm.task.form.TasksCreateForm;
import com.dmm.task.service.AccountUserDetails;



@Controller
public class CreateController {
	

	@Autowired
	private TasksRepository tasksRepository;
	
	@GetMapping("/main/create/{yyyy-MM-dd}")
	public String getNewTask(Model model) {
		// Modelに空のUserFormを追加
		TasksCreateForm tasksCreateForm = new TasksCreateForm();
		model.addAttribute("tasksCreateForm", tasksCreateForm);
		// テンプレートは src/main/resources/templates/newuser.html とします。
		System.out.println("日付入のcreate");
		return "create";
	}
	
	
	@PostMapping("/main/create")
	public String registerTasks(TasksCreateForm tasksCreateForm, BindingResult bindingResult, @AuthenticationPrincipal AccountUserDetails user) {
		System.out.println("普通のcreate");
		
		Tasks task = new Tasks();
		task.setName(user.getName());
		task.setTitle(tasksCreateForm.getTitle());
		task.setText(tasksCreateForm.getText());
		task.setDate(tasksCreateForm.getDate());
		task.setDone(false);

		tasksRepository.save(task);

		return "redirect:/main";
}
}
