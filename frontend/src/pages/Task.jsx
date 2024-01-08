import React, { useEffect, useState } from 'react';
import {
  Paper,
  List,
  ListItem,
  ListItemText,
  ListItemSecondaryAction,
  IconButton,
  TextField,
  Button,
  Typography,
  Divider,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Select,
  MenuItem
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

const Task = () => {
  const [tasks, setTasks] = useState([]);
  console.log(tasks)
  const [newTaskTitle, setNewTaskTitle] = useState('');
  const [editTaskId, setEditTaskId] = useState(null);
  const [editTaskTitle, setEditTaskTitle] = useState('');
  const [deleteTaskId, setDeleteTaskId] = useState(null);
  const [deleteConfirmDialogOpen, setDeleteConfirmDialogOpen] = useState(false);
  const [selectedOption, setSelectedOption] = useState(null);
  const [editOption, setEditOption] = useState('');
  const [editStatus, setEditStatus] = useState('');

  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
  };

  const handleEditOptionChange = (e) => {
    setEditOption(e.target.value);
    setEditStatus(e.target.value)
  };

  const handleAddTask = async () => {
    const newTask = {
      id: tasks.length + 1,
      title: newTaskTitle,
    };
    // setTasks([...tasks, newTask]);
    // setNewTaskTitle('');
    try {
      // let user_id = localStorage.getItem('user_id');
      let postData = {
        taskName : newTaskTitle,
        id : localStorage.getItem('user_id'),
        status : selectedOption
      }
      const response = await fetch(`http://192.168.94.192:8080/input`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      const newTask = {
        task_id: data.data.task_id,
        taskName: newTaskTitle,
        status : selectedOption
      };
      setTasks([...tasks, newTask]);
    } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
    }
  };

  const handleDeleteTask = async () => {
    // const updatedTasks = tasks.filter((task) => task.task_id !== deleteTaskId);
    // setTasks(updatedTasks);
    // setDeleteConfirmDialogOpen(false);
    try {
      // let user_id = localStorage.getItem('user_id');
      const response = await fetch(`http://192.168.94.192:8080/delete?task_id=${deleteTaskId}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        }
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      const updatedTasks = tasks.filter((task) => task.task_id !== deleteTaskId);
      setTasks(updatedTasks);
      setDeleteConfirmDialogOpen(false);
    } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
    }
  };

  const handleEditTask = async () => {
    const updatedTasks = tasks.map((task) =>
      task.task_id === editTaskId ? { ...task, title: editTaskTitle } : task
    );
    // useEffect(() => {
      // const fetchTasks = async () => {
        try {
          // let user_id = localStorage.getItem('user_id');
          let postData = {
            task_id : editTaskId,
            newTask : editTaskTitle,
            status : editStatus
          }
          const response = await fetch(`http://192.168.94.192:8080/update-task`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(postData),
          });
    
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
    
          const data = await response.json();
          const updatedTasks = tasks.map((task) =>
            task.task_id === editTaskId ? { ...task, taskName: editTaskTitle, status : data.data.status } : task
          );
          // const newTask = {
          //   task_id: data.data.task_id,
          //   taskName: newTaskTitle,
          //   status : selectedOption
          // };
          setTasks(updatedTasks);
          setEditTaskId(null);
        } catch (error) {
          console.error('There was a problem with the fetch operation:', error);
        }
      // };
    
      // fetchTasks();
    // }, []);  
  };

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        let user_id = localStorage.getItem('user_id');
        const response = await fetch(`http://192.168.94.192:8080/all-task?id=${user_id}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });
  
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
  
        const data = await response.json();
        console.log(59, data);
        setTasks(data.data);
      } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
      }
    };
  
    fetchTasks();
  }, []);  

  
  return (
    <Paper elevation={3} style={{ padding: 20, margin: '20px auto', maxWidth: 400, backgroundColor: 'white', color: 'black' }}>
      <Typography variant="h5" gutterBottom>
        Task Manager
      </Typography>
      <Divider style={{ marginBottom: 20 }} />
      <TextField
        label="New Task"
        variant="outlined"
        fullWidth
        value={newTaskTitle}
        onChange={(e) => setNewTaskTitle(e.target.value)}
        style={{ marginBottom: 10 }}
        InputProps={{ style: { color: 'black' } }}
        required
      />
      <Select
        value={selectedOption}
        onChange={handleOptionChange}
        style={{ width: '100%' }}
        required
      >
        <MenuItem value="To Do">To Do</MenuItem>
        <MenuItem value="In Progress">In Progress</MenuItem>
        <MenuItem value="Testing">Testing </MenuItem>
        <MenuItem value="Done">Done </MenuItem>
      </Select>
      <br /><br />
      <Button
        variant="contained"
        color="primary"
        fullWidth
        onClick={handleAddTask}
        disabled={!newTaskTitle || !selectedOption}
        style={{ marginBottom: 20 }}
      >
        Add Task
      </Button>
      <List>
        {tasks.map((task) => (
          <ListItem key={task.task_id} divider>
            <ListItemText primary={task.taskName} />
            <ListItemText primary={task.status} />
            <ListItemSecondaryAction>
              <IconButton
                edge="end"
                aria-label="edit"
                onClick={() => {
                  setEditTaskId(task.task_id);
                  setEditTaskTitle(task.taskName);
                  setEditStatus(task.status);
                }}
              >
                <EditIcon />
              </IconButton>
              <IconButton
                edge="end"
                aria-label="delete"
                onClick={() => {
                  setDeleteTaskId(task.task_id);
                  setDeleteConfirmDialogOpen(true);
                }}
              >
                <DeleteIcon />
              </IconButton>
            </ListItemSecondaryAction>
          </ListItem>
        ))}
      </List>

      {/* Edit Task Dialog */}
      <Dialog open={editTaskId !== null} onClose={() => setEditTaskId(null)}>
        <DialogTitle>Edit Task</DialogTitle>
        <DialogContent style={{ backgroundColor: 'white', color: 'black' }}>
          <TextField
            variant="outlined"
            fullWidth
            value={editTaskTitle}
            onChange={(e) => setEditTaskTitle(e.target.value)}
          />
          <br /><br />
          <Select
          value={editStatus}
          onChange={handleEditOptionChange}
          style={{ width: '100%' }}
          required
        >
          <MenuItem value="To Do">To Do</MenuItem>
          <MenuItem value="In Progress">In Progress</MenuItem>
          <MenuItem value="Testing">Testing </MenuItem>
          <MenuItem value="Done">Done </MenuItem>
        </Select>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setEditTaskId(null)} style={{ color: 'black' }}>Cancel</Button>
          <Button onClick={handleEditTask} disabled={!editTaskTitle} variant="contained" color="primary">
            Save
          </Button>
        </DialogActions>
      </Dialog>

      {/* Delete Task Confirmation Dialog */}
      <Dialog open={deleteConfirmDialogOpen} onClose={() => setDeleteConfirmDialogOpen(false)}>
        <DialogTitle>Confirm Delete</DialogTitle>
        <DialogContent style={{ backgroundColor: 'white', color: 'black' }}>
          <Typography>Are you sure you want to delete this task?</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setDeleteConfirmDialogOpen(false)} style={{ color: 'black' }}>Cancel</Button>
          <Button onClick={handleDeleteTask} color="error" variant="contained">
            Delete
          </Button>
        </DialogActions>
      </Dialog>

    </Paper>
  );
};

export default Task;
