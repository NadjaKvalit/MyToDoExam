document.addEventListener("DOMContentLoaded", function () {
  const addTaskInput = document.querySelector(".addTaskInput");
  const addBtn = document.querySelector(".add-btn");
  const taskList = document.querySelector(".taskList");
  let isCategoryBlockClicked = false;

  addBtn.addEventListener("click", function () {
    const taskText = addTaskInput.value.trim();
    if (taskText !== "") {
      // Create new list item
      const newTask = document.createElement("li");
      newTask.classList.add("newTask");

      const priorityBlock = document.createElement("div");
      priorityBlock.classList.add("priorityBlock");
      document.body.appendChild(priorityBlock); // Append priorityBlock to the body or another target element

      // Create image element inside priorityBlock
      const priorityImage = document.createElement("img");
      priorityImage.classList.add("priorityImage");
      priorityImage.src = "images/priority.png"; // Replace with your default image URL
      priorityBlock.appendChild(priorityImage);

      // Create icon priority modal (similar to previous example)
      const priorityModal = document.createElement("div");
      priorityModal.id = "iconPriorityModal";
      priorityModal.classList.add("priorityModal");
      priorityModal.style.display = "none";
      const priorityModalContent = document.createElement("div");
      priorityModalContent.classList.add("priority-modal-content");
      priorityModal.appendChild(priorityModalContent);

       // Create and append icons to the priority modal content
       const iconPrioritySources = [
        "images/priority1.png",
        "images/priority2.png",
        "images/priority3.png",
      ]; 

/*

// Define taskList and functions
const taskList = document.querySelector('.taskList');

const priorityLevels = {
    'images/priority1.png': 1,
    'images/priority2.png': 2,
    'images/priority3.png': 3,
  };
  
  function getPriorityLevel(iconSrc) {
    return priorityLevels[iconSrc] || 0;
  }




function sortTasksByPriority() {
    const tasks = Array.from(taskList.querySelectorAll('.newTask'));
  
    tasks.sort((taskA, taskB) => {
      const priorityIconA = taskA.querySelector('.priorityBlock .selected-priority-icon img');
      const priorityIconB = taskB.querySelector('.priorityBlock .selected-priority-icon img');
      const priorityLevelA = getPriorityLevel(priorityIconA?.src);
      const priorityLevelB = getPriorityLevel(priorityIconB?.src);
      return priorityLevelB - priorityLevelA;
    });
  
    // Remove all tasks from the list
    tasks.forEach((task) => taskList.removeChild(task));
  
    // Re-append the tasks in the sorted order
    tasks.forEach((task) => taskList.appendChild(task));
  }
*/



      // Create and append icons to the priority modal content
iconPrioritySources.forEach((iconSrc) => {
    const iconPriority = document.createElement("img");
    iconPriority.src = iconSrc;
    iconPriority.classList.add("iconPriority");
    priorityModalContent.appendChild(iconPriority); // Use priorityModalContent instead of modalContent
    iconPriority.addEventListener("click", function () {
      const selectedIconSrc = this.src;
      const selectedPriorityIco = document.createElement("img");
      selectedPriorityIco.src = selectedIconSrc; // Corrected variable name here
      selectedPriorityIco.classList.add("selected-priority-icon"); // Add a class to the selected icon
      priorityBlock.innerHTML = ""; // Clear existing content
      priorityBlock.appendChild(selectedPriorityIco); // Use selectedPriorityIco instead of selectedIcon
      priorityModal.style.display = "none";
     //sortTasksByPriority();
    });
  });

      document.body.appendChild(priorityModal); // Append iconModal to the body or another target element

  
      
      // Event listener to show the icon priority modal when priorityBlock is clicked
      priorityBlock.addEventListener("click", function (event) {
        const rect = priorityBlock.getBoundingClientRect();
        const top = rect.bottom + window.scrollY;
        const left = rect.left + window.scrollX;
        priorityModal.style.left = `${left}px`;
        priorityModal.style.top = `${top}px`;
        priorityModal.style.display = "block";
       
      });

      // Event listener to hide modal when clicking outside the modal or category block
      window.addEventListener("click", function (event) {
        const isClickedInsideCategoryBlock =
        priorityBlock.contains(event.target) ||
        event.target === priorityBlock;
        const isClickedInsideModal =
        priorityModal.contains(event.target) || event.target === priorityModal;

        if (!isClickedInsideCategoryBlock && !isClickedInsideModal) {
            priorityModal.style.display = "none";
        }
      });


      const categoryBlock = document.createElement("div");
      categoryBlock.classList.add("categoryBlock");
      document.body.appendChild(categoryBlock); // Append categoryBlock to the body or another target element

      // Create image element inside categoryBlock
      const categoryImage = document.createElement("img");
      categoryImage.classList.add("categoryImage");
      categoryImage.src = "images/category.png"; // Replace with your default image URL
      categoryBlock.appendChild(categoryImage);

      // Create icon modal (similar to previous example)
      const iconModal = document.createElement("div");
      iconModal.id = "iconModal";
      iconModal.classList.add("modal");
      iconModal.style.display = "none";
      const modalContent = document.createElement("div");
      modalContent.classList.add("modal-content");
      iconModal.appendChild(modalContent);

      // Create and append icons to the modal content
      const iconSources = [
        "images/work.png",
        "images/study.png",
        "images/grocery.png",
        "images/sport.png",
        "images/other.png",
      ]; 
      
      // Replace with icon URLs
      iconSources.forEach((iconSrc) => {
        const icon = document.createElement("img");
        icon.src = iconSrc;
        icon.classList.add("icon");
        modalContent.appendChild(icon);
        icon.addEventListener("click", function () {
          const selectedIconSrc = this.src;
          const selectedIcon = document.createElement("img");
          selectedIcon.src = selectedIconSrc;
          selectedIcon.classList.add("selected-icon"); // Add a class to the selected icon
          categoryBlock.innerHTML = ""; // Clear existing content
          categoryBlock.appendChild(selectedIcon);
          iconModal.style.display = "none";
        });
      });

      document.body.appendChild(iconModal); // Append iconModal to the body or another target element

      // Event listener to show the icon modal when categoryBlock is clicked
      categoryBlock.addEventListener("click", function (event) {
        const rect = categoryBlock.getBoundingClientRect();
        const top = rect.bottom + window.scrollY;
        const left = rect.left + window.scrollX;
        iconModal.style.left = `${left}px`;
        iconModal.style.top = `${top}px`;
        iconModal.style.display = "block";
      });

      // Event listener to hide modal when clicking outside the modal or category block
      window.addEventListener("click", function (event) {
        const isClickedInsideCategoryBlock =
          categoryBlock.contains(event.target) ||
          event.target === categoryBlock;
        const isClickedInsideModal =
          iconModal.contains(event.target) || event.target === iconModal;

        if (!isClickedInsideCategoryBlock && !isClickedInsideModal) {
          iconModal.style.display = "none";
        }
      });

      // Rest of your logic for adding a new task...
      // Create checkbox, taskBlock, buttons, and add them to the new task element

      const checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      checkbox.classList.add("checkbox");

      const taskBlock = document.createElement("div");
      taskBlock.textContent = taskText;
      taskBlock.classList.add("taskBlock");

      const editButton = document.createElement("button");
      editButton.classList.add("editButton");

      const deleteButton = document.createElement("button");
      deleteButton.classList.add("deleteButton");

      newTask.appendChild(priorityBlock);
      newTask.appendChild(categoryBlock);
      newTask.appendChild(checkbox);
      newTask.appendChild(taskBlock);
      newTask.appendChild(editButton);
      newTask.appendChild(deleteButton);

      taskList.appendChild(newTask);

      // Clear input field
      addTaskInput.value = "";
    }
  });
  
});

document.addEventListener("DOMContentLoaded", function () {
    const taskList = document.querySelector(".taskList");
    taskList.addEventListener("click", function (event) {
      const clickedElement = event.target;
      if (clickedElement.classList.contains("editButton")) {
        const listItem = clickedElement.closest(".newTask");
        const taskBlock = listItem.querySelector(".taskBlock");
  
        // Create a div for editing
        const editText = document.createElement("div");
        editText.textContent = taskBlock.textContent; // Set the text content
        editText.classList.add("editInput");
        editText.setAttribute("contentEditable", true); // Enable editing
  
        // Replace the task text with a div for editing
        listItem.replaceChild(editText, taskBlock);
  
        // Event listener for updating the task text
        editText.addEventListener("blur", function () {
          taskBlock.textContent = editText.textContent;
          listItem.replaceChild(taskBlock, editText);
        });
  
        editText.focus(); // Focus on the div for editing
      }
    });
  });

  document.addEventListener('DOMContentLoaded', function() {
    const taskList = document.querySelector('.taskList');

    taskList.addEventListener('click', function(event) {
        const clickedElement = event.target;

        if (clickedElement.classList.contains('deleteButton')) {
            const listItem = clickedElement.closest('.newTask');
            listItem.remove(); // Remove the entire list item
        }
    });
});







