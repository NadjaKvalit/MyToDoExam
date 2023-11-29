function createTaskElement(taskText) {
  const taskToDoBlock = document.createElement("li");
  taskToDoBlock.id = Date.now().toString();
  taskToDoBlock.classList.add("taskToDoBlock");
  const priorityBlock = createPriorityBlock();
  const categoryBlock = createCategoryBlock();
  const checkbox = createCheckbox();
  const toDoTextBlock = createToDoTextBlock(taskText);
  const editButton = createButton("editButton");
  const deleteButton = createButton("deleteButton");
  priorityBlock.id = 4;
  categoryBlock.id = 6;
  checkbox.id = 2;
  taskToDoBlock.appendChild(priorityBlock);
  taskToDoBlock.appendChild(categoryBlock);
  taskToDoBlock.appendChild(checkbox);
  taskToDoBlock.appendChild(toDoTextBlock);
  taskToDoBlock.appendChild(editButton);
  taskToDoBlock.appendChild(deleteButton);
  return taskToDoBlock;
}

function createTaskElementFromDB(task) {
  const taskToDoBlock = document.createElement("li");
  taskToDoBlock.classList.add("taskToDoBlock");
  taskToDoBlock.id = task.idTasks;
  
  //categoryBlock.id = task.Category_idCategory;
 // checkbox.id = task.Done_idDone;
  
  const idPriority = task.Priority_idPriority;
  const priorityBlock = createPriorityBlockFromDB(idPriority);

  const idCategory = task.Category_idCategory;
  const categoryBlock = createCategoryBlockFromDB(idCategory);

  const idDone = task.Done_idDone;
  const checkbox = createCheckboxFromDB(idDone);
  
const whatToDo = task.whatToDo;
const toDoTextBlock = createToDoTextBlock(whatToDo);

  
  const editButton = createButton("editButton");
  const deleteButton = createButton("deleteButton");
  taskToDoBlock.appendChild(priorityBlock);
  taskToDoBlock.appendChild(categoryBlock);
  taskToDoBlock.appendChild(checkbox);
  taskToDoBlock.appendChild(toDoTextBlock);
  taskToDoBlock.appendChild(editButton);
  taskToDoBlock.appendChild(deleteButton);
  return taskToDoBlock;
}


function createPriorityBlock() {
  const priorityBlock = document.createElement("div");
  priorityBlock.classList.add("priorityBlock");
  const priorityImage = document.createElement("img");
  priorityImage.classList.add("priorityImage");
  priorityImage.src = "images/priority.png";
  priorityBlock.appendChild(priorityImage);
  //priorityBlock.addEventListener("click", function () {
  //  handlePriorityBlockClick(priorityBlock);
  //});
  return priorityBlock;
}

function createPriorityBlockFromDB(id) {
  const priorityBlock = document.createElement("div");
  priorityBlock.classList.add("priorityBlock");
  priorityBlock.id = id;
  const priorityImage = document.createElement("img");
  priorityImage.classList.add("priorityImage");
switch(id){
  case 1:
  priorityImage.src = "images/priority1.png";
  break;
  case 2:
  priorityImage.src = "images/priority2.png";
  break;
  case 3:
  priorityImage.src = "images/priority3.png";
  break;
  case 4:
  priorityImage.src = "images/priority.png";
  break;
}
  priorityBlock.appendChild(priorityImage);
  //priorityBlock.addEventListener("click", function () {
  //  handlePriorityBlockClick(priorityBlock);
  //});
  return priorityBlock;
}

function createCategoryBlock() {
  const categoryBlock = document.createElement("div");
  categoryBlock.classList.add("categoryBlock");

  const categoryImage = document.createElement("img");
  categoryImage.classList.add("categoryImage");
  
  categoryImage.src = "images/category.png";
  categoryBlock.appendChild(categoryImage);
  //categoryBlock.addEventListener("click", function () {
   // handleCategoryBlockClick(categoryBlock);
  //});
  return categoryBlock;
}

function createCategoryBlockFromDB(id) {
  const categoryBlock = document.createElement("div");
  categoryBlock.classList.add("categoryBlock");
  categoryBlock.id = id;
  const categoryImage = document.createElement("img");
  categoryImage.classList.add("categoryImage");
  switch(id){
    case 1:
      categoryImage.src = "images/work.png";
    break;
    case 2:
      categoryImage.src = "images/study.png";
    break;
    case 3:
      categoryImage.src = "images/grocery.png";
    break;
    case 4:
      categoryImage.src = "images/sport.png";
    break;
    case 5:
      categoryImage.src = "images/other.png";
    break;
    case 6:
      categoryImage.src = "images/category.png";
    break;
  }
  //categoryImage.src = "images/category.png";
  categoryBlock.appendChild(categoryImage);
  //categoryBlock.addEventListener("click", function () {
  //  handleCategoryBlockClick(categoryBlock);
  //});
  return categoryBlock;
}

function createCheckbox() {
  const checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  
  checkbox.classList.add("checkbox");
  checkbox.addEventListener("change", function (event) {
    if (this.checked) {
      checkbox.id = 1;
    } else {
      checkbox.id = 2;
    }
    const clickedElement = event.target;
    const listItem = clickedElement.closest(".taskToDoBlock");
    const idTasks = listItem.id;
    const taskData = {
      whatToDo: listItem.querySelector(".toDoTextBlock").textContent,
      Done_idDone: checkbox.id,
      Category_idCategory: listItem.querySelector(".categoryBlock").id,
      Priority_idPriority: listItem.querySelector(".priorityBlock").id,
    };

    fetch(`http://localhost:3000/tasks/${idTasks}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(taskData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        // Handle the response from the server, if needed
        console.log("Task added successfully:", data);
      })
      .catch((error) => {
        // Handle errors here
        console.error("There was a problem adding the task:", error);
      });
  });

  return checkbox;
}

function createCheckboxFromDB(id) {
  const checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  checkbox.classList.add("checkbox");
  checkbox.id = id;
  switch(id){
    case 1:
      checkbox.checked=true;
    break;
    case 2:
      checkbox.checked=false;
    break;
  }

  checkbox.addEventListener("change", function (event) {
    if (this.checked) {
      checkbox.id = 1;
    } else {
      checkbox.id = 2;
    }
    const clickedElement = event.target;
    const listItem = clickedElement.closest(".taskToDoBlock");
    const idTasks = listItem.id;
    const taskData = {
      whatToDo: listItem.querySelector(".toDoTextBlock").textContent,
      Done_idDone: checkbox.id,
      Category_idCategory: listItem.querySelector(".categoryBlock").id,
      Priority_idPriority: listItem.querySelector(".priorityBlock").id,
    };
    fetch(`http://localhost:3000/tasks/${idTasks}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(taskData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        // Handle the response from the server, if needed
        console.log("Task added successfully:", data);
      })
      .catch((error) => {
        // Handle errors here
        console.error("There was a problem adding the task:", error);
      });
  });

  return checkbox;
}

function createToDoTextBlock(taskText) {
  const toDoTextBlock = document.createElement("div");
  toDoTextBlock.textContent = taskText;
  toDoTextBlock.classList.add("toDoTextBlock");
  return toDoTextBlock;
}

function createButton(className) {
  const button = document.createElement("button");
  button.classList.add(className);
  return button;
}

function handlePriorityBlockClick(priorityBlock) {
  const priorityModal = getOrCreatePriorityModal();
  const iconPrioritySources = [
    "images/priority1.png",
    "images/priority2.png",
    "images/priority3.png",
  ];
  const priorityModalContent = priorityModal.querySelector(
    ".priorityModalContent"
  );
  priorityModalContent.innerHTML = ""; // Clear existing content
  iconPrioritySources.forEach((iconSrc) => {
    const priorityIcon = document.createElement("img");
    priorityIcon.src = iconSrc;
    priorityIcon.classList.add("priorityIcon");
    priorityModalContent.appendChild(priorityIcon);
    priorityIcon.addEventListener("click", function () {
      //const clickedElement = event.target;
      //const listItem = clickedElement.closest(".taskToDoBlock");
      const selectedIconSrc = this.src;
      const prioritySelectedIcon = document.createElement("img");
      prioritySelectedIcon.src = selectedIconSrc;
      prioritySelectedIcon.classList.add("prioritySelectedIcon");
      priorityBlock.innerHTML = "";
      priorityBlock.appendChild(prioritySelectedIcon);
      priorityModal.style.display = "none";
      // Additional logic after selecting priority icon
      console.log(selectedIconSrc);
      if (selectedIconSrc.endsWith("images/priority1.png")) {
        priorityBlock.id = 1;
      } else if (selectedIconSrc.endsWith("images/priority2.png")) {
        priorityBlock.id = 2;
      } else if (selectedIconSrc.endsWith("images/priority3.png")) {
        priorityBlock.id = 3;
      }
      //const clickedElement = event.target;
      const listItem = priorityBlock.closest(".taskToDoBlock");
      const idTasks = listItem.id;
      console.log(idTasks);
      const taskData = {
        whatToDo: listItem.querySelector(".toDoTextBlock").textContent,
        Done_idDone: listItem.querySelector(".checkbox").id,
        Category_idCategory: listItem.querySelector(".categoryBlock").id,
        Priority_idPriority: priorityBlock.id,
      };
      fetch(`http://localhost:3000/tasks/${idTasks}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(taskData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((data) => {
          // Handle the response from the server, if needed
          console.log("Task added successfully:", data);
        })
        .catch((error) => {
          // Handle errors here
          console.error("There was a problem adding the task:", error);
        });
    });
  });

  const rect = priorityBlock.getBoundingClientRect();
  const top = rect.bottom + window.scrollY;
  const left = rect.left + window.scrollX;
  priorityModal.style.left = `${left}px`;
  priorityModal.style.top = `${top}px`;
  priorityModal.style.display = "block";
}

function getOrCreatePriorityModal() {
  let priorityModal = document.querySelector("#iconPriorityModal");
  if (priorityModal) {
    return priorityModal;
  }
  priorityModal = document.createElement("div");
  priorityModal.id = "iconPriorityModal";
  priorityModal.classList.add("priorityModal");
  priorityModal.style.display = "none";
  const priorityModalContent = document.createElement("div");
  priorityModalContent.classList.add("priorityModalContent");
  priorityModal.appendChild(priorityModalContent);
  document.body.appendChild(priorityModal);

  window.addEventListener("click", function (event) {
    let isClickedInsidePriorityBlock = false;

    this.document
      .querySelectorAll(".priorityBlock")
      .forEach((priorityBlock) => {
        if (
          priorityBlock.contains(event.target) ||
          event.target === priorityBlock
        ) {
          isClickedInsidePriorityBlock = true;
        }
      });
    const isClickedInsideModal =
      priorityModal.contains(event.target) || event.target === priorityModal;

    if (!isClickedInsidePriorityBlock && !isClickedInsideModal) {
      priorityModal.style.display = "none";
    }
  });

  return priorityModal;
}

function handleCategoryBlockClick(categoryBlock) {
  const categoryModal = getOrCreateCategoryModal();
  const iconCategorySources = [
    "images/work.png",
    "images/study.png",
    "images/grocery.png",
    "images/sport.png",
    "images/other.png",
  ];
  const categoryModalContent = categoryModal.querySelector(
    ".categoryModalContent"
  );
  categoryModalContent.innerHTML = "";
  iconCategorySources.forEach((iconSrc) => {
    const categoryIcon = document.createElement("img");
    categoryIcon.src = iconSrc;
    categoryIcon.classList.add("categoryIcon");
    categoryModalContent.appendChild(categoryIcon);
    categoryIcon.addEventListener("click", function () {
      const selectedIconSrc = this.src;
      const categorySelectedIcon = document.createElement("img");
      categorySelectedIcon.src = selectedIconSrc;
      categorySelectedIcon.classList.add("categorySelectedIcon");
      categoryBlock.innerHTML = "";
      categoryBlock.appendChild(categorySelectedIcon);
      categoryModal.style.display = "none";
      
      if (selectedIconSrc.endsWith("images/work.png")) {
        categoryBlock.id = 1;
      } else if (selectedIconSrc.endsWith("images/study.png")) {
        categoryBlock.id = 2;
      } else if (selectedIconSrc.endsWith("images/grocery.png")) {
        categoryBlock.id = 3;
      } else if (selectedIconSrc.endsWith("images/sport.png")) {
        categoryBlock.id = 4;
      } else if (selectedIconSrc.endsWith("images/other.png")) {
        categoryBlock.id = 5;
      }
      const listItem = categoryBlock.closest(".taskToDoBlock");
      const idTasks = listItem.id;
      console.log(idTasks);
      const taskData = {
        whatToDo: listItem.querySelector(".toDoTextBlock").textContent,
        Done_idDone: listItem.querySelector(".checkbox").id,
        Category_idCategory: categoryBlock.id,
        Priority_idPriority: listItem.querySelector(".priorityBlock").id,
      };
      fetch(`http://localhost:3000/tasks/${idTasks}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(taskData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((taskData) => {
          // Handle the response from the server, if needed
          console.log("Task added successfully:", taskData);
        })
        .catch((error) => {
          // Handle errors here
          console.log("Task with error:", taskData);
          console.error("There was a problem adding the task:", error);
        });
    });
  });

  const rect = categoryBlock.getBoundingClientRect();
  const top = rect.bottom + window.scrollY;
  const left = rect.left + window.scrollX;
  categoryModal.style.left = `${left}px`;
  categoryModal.style.top = `${top}px`;
  categoryModal.style.display = "block";
}

// Hide modal when clicking outside the modal or category block
function getOrCreateCategoryModal() {
  let categoryModal = document.querySelector("#iconCategoryModal");
  if (categoryModal) {
    return categoryModal;
  }
  categoryModal = document.createElement("div");
  categoryModal.id = "iconCategoryModal";
  categoryModal.classList.add("categoryModal");
  categoryModal.style.display = "none";
  const categoryModalContent = document.createElement("div");
  categoryModalContent.classList.add("categoryModalContent");
  categoryModal.appendChild(categoryModalContent);
  document.body.appendChild(categoryModal);

  window.addEventListener("click", function (event) {
    let isClickedInsideCategoryBlock = false;

    this.document
      .querySelectorAll(".categoryBlock")
      .forEach((categoryBlock) => {
        if (
          categoryBlock.contains(event.target) ||
          event.target === categoryBlock
        ) {
          isClickedInsideCategoryBlock = true;
        }
      });
    const isClickedInsideModal =
      categoryModal.contains(event.target) || event.target === categoryModal;

    if (!isClickedInsideCategoryBlock && !isClickedInsideModal) {
      categoryModal.style.display = "none";
    }
  });
  return categoryModal;
}


// Function to fetch tasks from the backend
function fetchTasks() {
  fetch('http://localhost:3000/tasks',{
  method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })          
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then((data) => {
      // Handle the received tasks data
      displayTasks(data); // Pass the tasks data to a function to display it
    })
    .catch((error) => {
      console.error('There was a problem fetching tasks:', error);
    });
}

// Function to display tasks in the frontend
function displayTasks(tasks) {
  const taskList = document.querySelector('.taskList');
  console.log(tasks);
  tasks.forEach((task) => {
    
    const taskToDoBlock = createTaskElementFromDB(task); // Use your existing function to create task elements
    taskList.appendChild(taskToDoBlock);
    
  });
}

// Make the fetchTasks() call when the page loads
document.addEventListener('DOMContentLoaded', fetchTasks);


document.addEventListener("DOMContentLoaded", function () {
  const addTaskInput = document.querySelector(".addTaskInput");
  const addBtn = document.querySelector(".add-btn");
  const taskList = document.querySelector(".taskList");
  //console.log(taskList);
  //const priorityBlocks = document.querySelectorAll(".taskList .taskToDoBlock .priorityBlock");
  //console.log(priorityBlocks);

  
  /*  console.log("say hello");
    priorityBlocks.forEach((priorityBlock) => {
      priorityBlock.addEventListener("click", function () {
        handlePriorityBlockClick(priorityBlock);
      });
      });*/

      taskList.addEventListener("click", function (event) {
        let clickedElement = event.target;
        while (clickedElement) {
          if (clickedElement.classList.contains("priorityBlock")) {
            handlePriorityBlockClick(clickedElement);
            break;
          }
          clickedElement = clickedElement.parentElement;
        }
      });

      taskList.addEventListener("click", function (event) {
        let clickedElement = event.target;
        while (clickedElement) {
          if (clickedElement.classList.contains("categoryBlock")) {
            handleCategoryBlockClick(clickedElement);
            break;
          }
          clickedElement = clickedElement.parentElement;
        }
      });



  addBtn.addEventListener("click", function () {
    const taskText = addTaskInput.value.trim();
    if (taskText !== "") {
      const taskToDoBlock = createTaskElement(taskText);
      taskList.appendChild(taskToDoBlock);
      addTaskInput.value = "";

      // Make a POST request to your backend API
      // Create an object representing the task data
      const taskData = {
        idTasks: taskToDoBlock.id,
        whatToDo: taskText,
      };
      fetch("http://localhost:3000/tasks/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(taskData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((data) => {
          // Handle the response from the server, if needed
          console.log("Task added successfully:", data);
        })
        .catch((error) => {
          // Handle errors here
          console.error("There was a problem adding the task:", error);
        });
    }
  });

  taskList.addEventListener("click", function (event) {
    const clickedElement = event.target;

    // EDIT TASK
    if (clickedElement.classList.contains("editButton")) {
      const listItem = clickedElement.closest(".taskToDoBlock");
      const toDoTextBlock = listItem.querySelector(".toDoTextBlock");
      // Create a div for editing
      const editText = document.createElement("div");
      editText.textContent = toDoTextBlock.textContent; // Set the text content
      editText.classList.add("editInput");
      editText.setAttribute("contentEditable", true); // Enable editing
      // Replace the task text with a div for editing
      listItem.replaceChild(editText, toDoTextBlock);
      // Event listener for updating the task text
      editText.addEventListener("blur", function () {
        toDoTextBlock.textContent = editText.textContent;
        listItem.replaceChild(toDoTextBlock, editText);

        // Make a PUT request to your backend API (for whatToDotext)
        // Create an object representing the task data
        const idTasks = listItem.id;
        const toDoText = toDoTextBlock.textContent;
        const Done_idDone = listItem.querySelector(".checkbox").id;
        const taskData = {
          whatToDo: toDoText,
          Done_idDone: Done_idDone,
          Category_idCategory: 1,
          Priority_idPriority: listItem.querySelector(".priorityBlock").id,
        };
        fetch(`http://localhost:3000/tasks/${idTasks}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(taskData),
        })
          .then((response) => {
            if (!response.ok) {
              throw new Error("Network response was not ok");
            }
            return response.json();
          })
          .then((data) => {
            // Handle the response from the server, if needed
            console.log("Task added successfully:", data);
          })
          .catch((error) => {
            // Handle errors here
            console.error("There was a problem adding the task:", error);
          });
      });
      editText.focus(); // Focus on the div for editing
    }

    // DELETE TASK
    if (clickedElement.classList.contains("deleteButton")) {
      const listItem = clickedElement.closest(".taskToDoBlock");
      idTasks = listItem.id;
      //  Make a DELETE request to your backend API
      fetch(`http://localhost:3000/tasks/${idTasks}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => {
          if (response.ok) {
            // Remove the task from the frontend after successful deletion
            listItem.remove();
          } else {
            // Handle the case where deletion fails
            console.error("Failed to delete task");
          }
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
   
  });

});
