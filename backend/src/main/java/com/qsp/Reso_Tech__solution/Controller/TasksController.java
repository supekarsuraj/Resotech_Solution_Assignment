package com.qsp.Reso_Tech__solution.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.Reso_Tech__solution.Service.TaskService;
import com.qsp.Reso_Tech__solution.dto.Tasks;
import com.qsp.Reso_Tech__solution.util.ResponseStructure;

@Controller
@RestController
public class TasksController {
	@Autowired
	private TaskService taskService;
	
	
	
	@PostMapping("/input")
	public ResponseEntity<ResponseStructure<Tasks>> inputTask(@RequestBody Tasks task) {
			
    System.out.println("tasks login");
    
	return taskService.inputTask(task);
   
    }

@PutMapping("/update-task")
  public ResponseEntity<ResponseStructure<Tasks>> updateTask(@RequestBody Tasks task)
  {
	System.out.println("run");
	int task_id=task.getTask_id();
	String newTask=task.getNewTask();
	System.out.println(task_id);
	System.out.println(newTask);
	return taskService.updateTask(task);

  }
@GetMapping("/all-task")//tasks
public ResponseEntity<ResponseStructure<List<Tasks>>> findByUserId(int id)
{
	return taskService.findByUserId(id);

}
@GetMapping("/Task-task")
public ResponseEntity<ResponseStructure<Tasks>> findTaskByTaskId(int task_id)
{
	return taskService.findByTaskId(task_id);

}
@GetMapping("/find-by-task-name")
public ResponseEntity<ResponseStructure<List<Tasks>>> findTaskByName(String taskName)
{
	return taskService.findByTaskName(taskName);

}
@DeleteMapping("/delete-by-id")
public ResponseEntity<ResponseStructure<List<Tasks>>> DeleteById(int id)
{
	System.out.println("here is run");
	return taskService.DeleteById(id);

}
@DeleteMapping("/delete")
public ResponseEntity<ResponseStructure<Tasks>> DeleteByTaskId(int task_id)
{
	System.out.println("here is run");
	return taskService.DeleteByTaskId(task_id);

}
@DeleteMapping("/delete-by-task-name")
public ResponseEntity<ResponseStructure<List<Tasks>>> deleteTaskByName(String taskName)
{
	return taskService.deleteByTaskName(taskName);

}
@GetMapping("/status")
public ResponseEntity<ResponseStructure<List<Tasks>>> aboutStatus(@RequestBody Tasks task)
{
	System.out.println(task.getStatus());
	return taskService.aboutStatus(task);
}

//	
}
