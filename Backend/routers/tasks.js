const { v4: uuidv4 } = require("uuid");
const express = require("express");
const router = express.Router();
const mysql = require('mysql2');
const bodyParser = require('body-parser');


// Create a task (POST request)


// Create a MySQL database connection
const db = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'root',
  database: 'todolist'
});

// Connect to MySQL
db.connect((err) => {
  if (err) {
    throw err;
  }
  console.log('Connected to MySQL database');
});

//här börjar vi skapa funktioner ("controllers") som anropas beroende på "route"
//(req, res) är "request" och "response". De är faktiskt parametrar för funktion. "Request" är allt som en användare skickar till servern
//"Response" är tvärtom, dvs alltning som skickas/svaras från servern till klienten (till webbläsaren)
const postTask = (req, res) => {
  const idTasks =  Date.now().toString();
  const whatToDo = req.body.whatToDo;  
  /*
  const Done_idDone = 2;
  const Category_idCategory = 6;
  const Priority_idPriority = 4;*/
  const Done_idDone = req.body.Done_idDone;
  const Category_idCategory = req.body.Category_idCategory;
  const Priority_idPriority = req.body.Priority_idPriority;
  //const idTasks = req.body.idTasks;
  //const insertTaskQuery = `INSERT INTO tasks (idTasks, whatToDo, Done_idDone, Category_idCategory, Priority_idPriority) VALUES (?, ?, ?, ?, ?)`;
  const insertTaskQuery = `INSERT INTO tasks (idTasks, whatToDo, Done_idDone, Category_idCategory, Priority_idPriority) VALUES (?, ?, ?, ?, ?)`;
  db.query(
    insertTaskQuery,
    [idTasks, whatToDo, Done_idDone, Category_idCategory, Priority_idPriority],
    (err, result) => {
      if (err) {
        res.status(500).send(err);
      } else {
        res.status(200).json({
          idTasks,
          whatToDo,
          Done_idDone,
          Category_idCategory,
          Priority_idPriority,
        });
      }
    }
  );
};

const getTasks = (req, res) => {
  const getTasksQuery = 'SELECT * FROM tasks';
  db.query(getTasksQuery, (err, results) => {
    if (err) {
      res.status(500).send(err);
    } else {
      res.status(200).json(results);
    }
  });
};

// Get task by ID (GET request)
const getTaskById = (req, res) => {
  const taskId = req.params.id;
  const getTaskQuery = `SELECT * FROM tasks WHERE idTasks=?`;
  db.query(getTaskQuery, [taskId], (err, result) => {
    if (err) {
      res.status(500).send(err);
    } else {
      if (result.length === 0) {
        res.status(404).send(`Task with ID ${taskId} not found`);
      } else {
        res.status(200).json(result[0]);
      }
    }
  });
};

// Update task by ID (PUT request)
const putTaskById = (req, res) => {
  const idTasks = req.params.id;
  const { whatToDo, Done_idDone, Category_idCategory, Priority_idPriority } = req.body;
  
  const updateTaskQuery = `UPDATE tasks SET whatToDo=?, Done_idDone=?, Category_idCategory=?, Priority_idPriority=? WHERE idTasks=?`;
  
  db.query(
    updateTaskQuery,
    [whatToDo, Done_idDone, Category_idCategory, Priority_idPriority, idTasks],
    (err, result) => {
      if (err) {
        res.status(500).send(err);
      } else {
        res.status(200).json({
          idTasks,
          whatToDo,
          Done_idDone,
          Category_idCategory,
          Priority_idPriority,
        });
      }
    }
  );
};

// Delete task by ID (DELETE request)
const deleteTaskById = (req, res) => {
  const idTasks = req.params.id;
  const deleteTaskQuery = `DELETE FROM tasks WHERE idTasks=?`;

  db.query(deleteTaskQuery, [idTasks], (err, result) => {
    if (err) {
      res.status(500).send(err);
    } else {
      res.json({message: `Task with ID ${idTasks} has been deleted` })
    }
  });
};




////här behandlar vi en "route" "/" (dvs, http://localhost:3000/tasks/
//URL basadress http://localhost:3000/tasks definierades i app.js när vi tillagt "tasks routers" till vår app. (app.use('/tasks', tasks) )
router.route("/")
.post(postTask)
.get(getTasks);

//här behandlar vi en "route" med "/:id" "endpoint" (dvs,  http://localhost:3000/tasks/:id)
router.route('/:id')
.get(getTaskById)
.put(putTaskById)
.delete(deleteTaskById);

module.exports = router;