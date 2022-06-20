package com.dmm.task.form;


import lombok.Data;






@Data
public class TasksCreateForm {
	// titleへのバリデーション設定を追加
//		@Size(min = 1, max = 200)
		private String title;
		
//		@DateTimeFormat(pattern = "[yyyy-MM-dd]")
//		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		private String date;
		private String text;
		
		

}
