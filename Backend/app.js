const express = require('express') //här anslutar vi express till filen app.js
const app = express()  //här skapar vi en ny express "application" genom att anropa expressfunktionen "express()" (ungefär som att skapa ett objekt i en klass) 
const tasks = require('./routers/tasks') //här anslutar (importerar) vi skapande "routers/products" till filen app.js (/registrerar dem)
const port = 3000 //port är nätverksanslutning för utbyte av information mellan en webbserver och en webbklient (webbläsare)
const { v4: uuidv4 } = require('uuid');
const mysql = require('mysql2');
const bodyParser = require('body-parser');


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

// med metod "use" läggs olika "plugins" eller "routes" till objektet app. 
//express.json() function is a middleware function in Express. It parses incoming requests (data som står i "body") with JSON payloads (needs for POST or PUT methods).
//Middleware function is those methods/functions/operations that are called BETWEEN processing the Request and sending the Response in your application method.
app.use(express.json());

// här lägger vi "routers" som svarar för "products" till vår app. 
// Parametern "/tasks" i början betyder att den här URL basadress sammanlänkas med URL från "products routers". Dvs, hela addresen http://localhost:3000/ + "/" eller + "/:id"
app.use('/tasks', tasks);

app.use(bodyParser.json());

  //Här startar vi servern genom port som nämndes ovan
app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
  })