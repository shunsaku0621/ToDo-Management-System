package com.dmm.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.entity.Users;
import com.dmm.task.data.repository.TasksRepository;
import com.dmm.task.data.repository.UsersRepository;
import com.dmm.task.form.TasksEditForm;
import com.dmm.task.service.AccountUserDetails;

@Controller
public class EditController {
	
	@Autowired
	private TasksRepository tasksRepository;
	private UsersRepository usersRepository;
	
	@GetMapping("/main/edit/{id}")
	public String getEditTask(Model model, @PathVariable int id, HttpServletRequest httpServletRequest, @AuthenticationPrincipal AccountUserDetails user) {
		Users login_user = user.getUser();
		List<Tasks> tasks = tasksRepository.findByDateBetween(id, login_user.getName());
		model.addAttribute("task", tasks.get(0));
		return "edit";
	}
	
	@PostMapping("/main/edit/{id}")
	public String editToDo(TasksEditForm tasksEditForm, BindingResult bindingResult, @AuthenticationPrincipal AccountUserDetails user, @PathVariable int id) {

		Tasks task = tasksRepository.getById(id);
		task.setName(user.getName());
		task.setTitle(tasksEditForm.getTitle());
		task.setText(tasksEditForm.getText());
		task.setDate(tasksEditForm.getDate());
		task.setDone(tasksEditForm.isDone());
		tasksRepository.save(task);
		
		return "redirect:/main";
	}
	
	

}
