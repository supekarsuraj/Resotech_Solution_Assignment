package com.qsp.Reso_Tech__solution.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.Reso_Tech__solution.dao.TaskDao;
import com.qsp.Reso_Tech__solution.dto.Tasks;
import com.qsp.Reso_Tech__solution.exception.IdNotFound;
import com.qsp.Reso_Tech__solution.exception.NameNotFound;
import com.qsp.Reso_Tech__solution.exception.TaskMarkIsWrond;
import com.qsp.Reso_Tech__solution.exception.TaskNameNotFound;
import com.qsp.Reso_Tech__solution.ripository.TaskRipository;
import com.qsp.Reso_Tech__solution.util.ResponseStructure;

@Service
public class TaskService {
	@Autowired
	private TaskDao dao;
	@Autowired
	private TaskRipository taskRipository;

    

	public Tasks input(Tasks task) {
		// TODO Auto-generated method stub
//		task.setStatus(pending);
		return dao.save(task);
	}
	public ResponseEntity<ResponseStructure<List<Tasks>>> find(int id)
	{
        ResponseStructure<Tasks> structure = new ResponseStructure<>();

		Optional<Tasks> task=dao.find(id);
		if(task!=null)
		{
			structure.setMessage("Tasks Found With Given Name");
			structure.setStatus(HttpStatus.OK.value()); // structure.setStatus(302);
			structure.setData(task.get());
//			return structure;
			return new ResponseEntity<ResponseStructure<List<Tasks>>>(HttpStatus.OK);
					}
		throw new IdNotFound("Tasks Not Found");
	}
	public ResponseEntity<ResponseStructure<Tasks>> inputTask(Tasks tasks) {
//		tasks.setCreatedBy(tasks.getName());
//		tasks.setModifiedBy(tasks.getName());
//	    tasks.setCreatedAt(LocalDateTime.now());
//		ResponseStructure<Tasks> structure = new ResponseStructure<Tasks>();
//		structure.setMessage("User Sign Up Successful");
//		structure.setStatus(HttpStatus.CREATED.value()); // structure.setStatus(201);
//		structure.setData(dao.inputtask(tasks));
//		return new ResponseEntity<ResponseStructure<Tasks>>(HttpStatus.OK);
		tasks.setCreatedBy(tasks.getName());
		tasks.setModifiedBy(tasks.getName());
		tasks.setCreatedAt(LocalDateTime.now());

		// Assuming dao.inputtask(tasks) returns the created task
		Tasks createdTask = dao.inputtask(tasks);

		if (createdTask != null) {
		    ResponseStructure<Tasks> structure = new ResponseStructure<>();
		    structure.setMessage("Tasks input Successful");
		    structure.setStatus(HttpStatus.CREATED.value());
		    structure.setData(createdTask);

		    return new ResponseEntity<>(structure, HttpStatus.CREATED);
		} else {
		    
		    ResponseStructure<Tasks> structure = new ResponseStructure<>();
		    structure.setMessage("User Sign Up Failed");
		    structure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		    return new ResponseEntity<>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		}

		
		public ResponseEntity<ResponseStructure<Tasks>> updateTask(Tasks task) {
			
			Tasks t;
			int task_id=task.getTask_id();
			System.out.println(task_id);
			String new_task=task.getNewTask();
			String Status=task.getStatus();
			System.out.println(new_task);
		    Optional<Tasks> optionalTask = dao.findtask(task_id);
		    System.out.println(optionalTask);

		    if (optionalTask.isPresent()) {
		        Tasks existingTask = optionalTask.get();
		        existingTask.setModifiedAt(LocalDateTime.now());
		        existingTask.setTaskName(new_task);
		        existingTask.setStatus(Status);
		        ResponseStructure<Tasks> structure = new ResponseStructure<>();
		        structure.setMessage("Task found and updated");
		        structure.setStatus(HttpStatus.OK.value());
		        structure.setData(existingTask);

		        taskRipository.save(existingTask);

		        return new ResponseEntity<>(structure, HttpStatus.OK);
		    } else {
		        ResponseStructure<Tasks> structure = new ResponseStructure<>();
		        structure.setMessage("Task not found");
		        structure.setStatus(HttpStatus.NOT_FOUND.value());

		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
		    }
		}
		
		
	public ResponseEntity<ResponseStructure<List<Tasks>>> findByUserId(int id) {
			
			ResponseStructure<List<Tasks>> structure = new ResponseStructure<>();
			List<Tasks> list = dao.findUserId(id);
			
			if (list.isEmpty()) {
				
				throw new IdNotFound("Id Not Found");
			} else {
				structure.setMessage("Tasks Found With Given Name");
				structure.setStatus(HttpStatus.OK.value()); // structure.setStatus(302);
				structure.setData(list);
//				return structure;
				return new ResponseEntity<ResponseStructure<List<Tasks>>>(structure,HttpStatus.OK);
				
			}
		}

	public ResponseEntity<ResponseStructure<Tasks>> findByTaskId(int task_id) {
	    Optional<Tasks> tasks = dao.findByTaskId(task_id);

	    if (tasks==null) {
	        throw new IdNotFound("Tasks Not Found with the Given TaskId");

	       
	    } else {
	    	 ResponseStructure<Tasks> structure = new ResponseStructure<>();
	         structure.setMessage("Tasks Found With Given TaskId");
	         structure.setStatus(HttpStatus.OK.value());
	         structure.setData(tasks.get());
	         return ResponseEntity.ok(structure);
	       
	    }
	}
	 
	public ResponseEntity<ResponseStructure<List<Tasks>>> findByTaskName(String taskName) {
		
		ResponseStructure<List<Tasks>> structure = new ResponseStructure<>();

	    List<Tasks> tasks = dao.findByTaskName(taskName);
	    System.out.println("here come cursor");

	    if (tasks.isEmpty()) {
	    	System.out.println("cusrsor came here");
	        throw new TaskNameNotFound("Tasks Name Not");

	      
	    } else {
	    	structure.setMessage("Tasks Found With Given Name");
			structure.setStatus(HttpStatus.FOUND.value()); 
			structure.setData(tasks);
//			return structure;
			return new ResponseEntity<ResponseStructure<List<Tasks>>>(structure,HttpStatus.FOUND);
			
	          }
	    }
public ResponseEntity<ResponseStructure<List<Tasks>>> DeleteById(int id) {
		
		ResponseStructure<List<Tasks>> structure = new ResponseStructure<>();

		List<Tasks> list = dao.findUserId(id);
		
		if (list.isEmpty()) {
			
			throw new IdNotFound("Id Not Found");
		} else {
			dao.deleteTaskById(id);
			structure.setMessage("Tasks Found With Given Name");
			structure.setStatus(HttpStatus.FOUND.value()); 
			structure.setData(list);
//			return structure;
			return new ResponseEntity<ResponseStructure<List<Tasks>>>(structure,HttpStatus.FOUND);
			
		
	    } 
	    }


public ResponseEntity<ResponseStructure<Tasks>> DeleteByTaskId(int task_id) {
    Optional<Tasks> tasks = dao.findByTaskId(task_id);

    if (tasks==null) {
        throw new IdNotFound("Tasks Not Found with the Given TaskId");

       
    } else {
    	dao.DeleteByTaskId(task_id);
    	ResponseStructure<Tasks> structure = new ResponseStructure<>();
         structure.setMessage("Delete task  With The Help Of TaskId");
         structure.setStatus(HttpStatus.OK.value());
      structure.setData(tasks.get());
         return ResponseEntity.ok(structure);
       
    }
}
public ResponseEntity<ResponseStructure<List<Tasks>>> deleteByTaskName(String taskName) {
	
	ResponseStructure<List<Tasks>> structure = new ResponseStructure<>();

    List<Tasks> tasks = dao.findByTaskName(taskName);
    System.out.println("here come code");

    if (tasks.isEmpty()) {
    	System.out.println("came here");
        throw new TaskNameNotFound("Tasks Name Not Found");

      
    } else {
    	  dao.deleteByTaskName(taskName);
    	  System.out.println("delete all tasks");

    	structure.setMessage("Delete Task With Given Name");
		structure.setStatus(HttpStatus.FOUND.value()); // structure.setStatus(302);
		structure.setData(tasks);
// 	return structure;
		return new ResponseEntity<ResponseStructure<List<Tasks>>>(structure,HttpStatus.FOUND);
		
          }
    }
public ResponseEntity<ResponseStructure<List<Tasks>>> aboutStatus(Tasks task)

{
	int task_id=task.getTask_id();
	String status=task.getStatus();
	 Optional<Tasks> tasks = dao.findByTaskId(task_id);
System.out.println(tasks);
System.out.println("run");
	    if (tasks==null) {
	        throw new IdNotFound("Tasks Not Found with the Given TaskId");

	       
	    } else {
//	    	if(status.equals("completed")||status.equals("executing")||status.equals("pending")) {
	    		System.out.println("control come here");
	    		dao.aboutStatus(status,task_id);
	    	
	    	ResponseStructure<Tasks> structure = new ResponseStructure<>();
	         structure.setMessage("Delete task  With The Help Of TaskId");
	         structure.setStatus(HttpStatus.OK.value());
            structure.setData(task);
         return new ResponseEntity<ResponseStructure<List<Tasks>>>(HttpStatus.FOUND);
	    	
	       
	    }
	
}



 


}
