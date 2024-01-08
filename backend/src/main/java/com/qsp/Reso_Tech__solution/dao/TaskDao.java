package com.qsp.Reso_Tech__solution.dao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qsp.Reso_Tech__solution.dto.Tasks;
import com.qsp.Reso_Tech__solution.ripository.TaskRipository;

import jakarta.transaction.Transactional;
@Transactional
@Repository
public class TaskDao {
	@Autowired
	private TaskRipository ripository;
	
	
	public Tasks save(Tasks task) {
		return ripository.save(task);
	}
	public Optional<Tasks> find(int id) {
		return ripository.findById(id);
	}
	public Optional<Tasks> findtask(int task_id) {
		return ripository.findById(task_id);
	}
	public Tasks inputtask(Tasks tasks)
	{
		return ripository.save(tasks);
	}
	  
	
     
     public List<Tasks> findUserId(int id) {
 		return ripository.getByUserId(id);
 	}
     public Optional<Tasks> findByTaskId(int task_id) {
 		return ripository.findById(task_id);
 	}
     
    
     public List<Tasks> findByTaskName(String taskname)
 	{
 		return ripository.getTasksByTaskName(taskname);
 	} 
     public void deleteTaskById(int id) {
    	 ripository.deleteByIdCustom(id);
    	}
     public void DeleteByTaskId(int task_id)
     {
    	 ripository.deleteById(task_id); 
     }  
     
     public int deleteByTaskName(String taskname)
  	{
    	 System.out.println("code run here");
  		return ripository.deleteTasksByTaskName(taskname);
  	}  
  public int  aboutStatus(String status,int task_id)
     {
    	  return ripository.updateTaskById(status ,task_id);
     }

	
	

}
