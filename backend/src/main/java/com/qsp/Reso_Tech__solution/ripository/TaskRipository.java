package com.qsp.Reso_Tech__solution.ripository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import com.qsp.GalaxyOrder.dto.SignUp;
import com.qsp.Reso_Tech__solution.dto.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qsp.Reso_Tech__solution.dto.Tasks;


public interface TaskRipository extends JpaRepository<Tasks, Integer>{
	@Query("SELECT e FROM Tasks e WHERE e.name = :name")
	Tasks finByName(@Param("name") String name);
	@Query("SELECT e FROM Tasks e WHERE e.id= :id")
	List<Tasks> getByUserId(int id);
	List<Tasks>   getTasksById(int task_id);
//	List<Tasks>   getTasksByTaskName(String taskName);
	@Query("SELECT e FROM Tasks e WHERE e.taskName = :taskName")
	List<Tasks>   getTasksByTaskName(@Param("taskName") String taskName);
	@Modifying
	@Query("DELETE FROM Tasks t WHERE t.id = :id")
	void deleteByIdCustom(@Param("id") int id);
	@Modifying
	@Query("DELETE FROM Tasks t WHERE t.taskName = :taskName")
	int deleteTasksByTaskName(@Param("taskName") String taskName);
	@Modifying
	@Query("UPDATE Tasks t SET t.status = :status WHERE t.task_id = :task_id")
	int  updateTaskById(@Param("status") String status, @Param("task_id") int task_id);
}
