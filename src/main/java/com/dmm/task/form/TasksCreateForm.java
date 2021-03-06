package com.dmm.task.form;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;



@Data
public class TasksCreateForm {
	// titleへのバリデーション設定を追加
//		@Size(min = 1, max = 200)
		private String title;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate date;
		private String text;
		
		

}
