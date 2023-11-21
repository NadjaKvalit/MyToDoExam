document.querySelector('.add-btn').addEventListener('click', function() {
    const inputText = document.querySelector('.addTaskInput').value;
    const taskList = document.querySelector('.taskList');

    if (inputText !== '') {
        const newTask = document.createElement('li'); // Create a new paragraph element
        newTask.textContent = inputText; // Set its text content to the input text

        taskList.appendChild(newTask); // Append the new paragraph to the task list container
        document.querySelector('.addTaskInput').value = ''; // Clear the input field after adding the task
    }
});