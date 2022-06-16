package com.dmm.task.form;


import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TasksCreateForm {
	// titleへのバリデーション設定を追加
		@Size(min = 1, max = 200)
		private String title;
		private LocalDateTime date;
		private String text;
}
