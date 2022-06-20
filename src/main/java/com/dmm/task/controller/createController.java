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
	
//	@GetMapping("/main/create")
//	public String createIndex(Model model) {
//		List<Tasks> list = tasksRepository.findAll();
////	    Collections.reverse(list); //普通に取得してこちらの処理でもOK
//			model.addAttribute("create", list);
////			TasksCreateForm tasksCreateForm = new TasksCreateForm();
////			model.addAttribute("TasksCreateForm", tasksCreateForm);
//		return "create";
//	}
//	@PostMapping("/main/create/{yyyy-MM-dd}")
	@GetMapping("/main/create/{yyyy-MM-dd}")
	public String getNewTask(Model model) {
		// Modelに空のUserFormを追加
		TasksCreateForm tasksCreateForm = new TasksCreateForm();
		model.addAttribute("tasksCreateForm", tasksCreateForm);
		// テンプレートは src/main/resources/templates/newuser.html とします。
		return "create";
	}
	
	
	@PostMapping("/main/create")
	public String registerTasks(TasksCreateForm tasksCreateForm, BindingResult bindingResult, @AuthenticationPrincipal AccountUserDetails user) {
		// バリデーションの結果、エラーがあるかどうかチェック
//		if (bindingResult.hasErrors()) {
//			// エラーがある場合は投稿登録画面を返す
//			List<Tasks> list = tasksRepository.findAll();
//			model.addAttribute("create", list);
//			model.addAttribute("TasksCreateForm", tasksCreateForm);
//			return "/create";
//		}
		System.out.println("-----------------------TEST2-------------------------");
		System.out.println(tasksCreateForm);
		
		
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
