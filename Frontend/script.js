function createTaskElement(taskText) {
  const taskToDoBlock = document.createElement("li");
  taskToDoBlock.classList.add("taskToDoBlock");
  const priorityBlock = createPriorityBlock();
  const categoryBlock = createCategoryBlock();
  const checkbox = createCheckbox();
  const toDoTextBlock = createToDoTextBlock(taskText);
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
  priorityBlock.addEventListener("click", function () {
    handlePriorityBlockClick(priorityBlock);
  });
  return priorityBlock;
}

function createCategoryBlock() {
  const categoryBlock = document.createElement("div");
  categoryBlock.classList.add("categoryBlock");
  const categoryImage = document.createElement("img");
  categoryImage.classList.add("categoryImage");
  categoryImage.src = "images/category.png"; 
  categoryBlock.appendChild(categoryImage);
  categoryBlock.addEventListener("click", function () {
    handleCategoryBlockClick(categoryBlock);
  });
  return categoryBlock;
}

function createCheckbox() {
  const checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  checkbox.classList.add("checkbox");
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
      const selectedIconSrc = this.src;
      const prioritySelectedIcon = document.createElement("img");
      prioritySelectedIcon.src = selectedIconSrc;
      prioritySelectedIcon.classList.add("prioritySelectedIcon");
      priorityBlock.innerHTML = "";
      priorityBlock.appendChild(prioritySelectedIcon);
      priorityModal.style.display = "none";
      // Additional logic after selecting priority icon
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

document.addEventListener("DOMContentLoaded", function () {
  const addTaskInput = document.querySelector(".addTaskInput");
  const addBtn = document.querySelector(".add-btn");
  const taskList = document.querySelector(".taskList");

  addBtn.addEventListener("click", function () {
    const taskText = addTaskInput.value.trim();
    if (taskText !== "") {
      const taskToDoBlock = createTaskElement(taskText);
      taskList.appendChild(taskToDoBlock);
      addTaskInput.value = "";
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
      });
      editText.focus(); // Focus on the div for editing
    }

    // DELETE TASK
    if (clickedElement.classList.contains("deleteButton")) {
      const listItem = clickedElement.closest(".taskToDoBlock");
      listItem.remove(); // Remove the entire list item
    }
  });
});
