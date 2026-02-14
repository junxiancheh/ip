# Sleeper User Guide

<img src="https://junxiancheh.github.io/ip/images/SleeperGUI.png" width="600" alt="Sleeper GUI">

Sleeper is a clutch task-management chatbot inspired by the deadly assassin's, Steph Curry's, famous "Night Night" celebration. Whether you're running the floor with a busy schedule or just trying to put your to-do list to bed, Sleeper has the handles to help you stay locked in.

## Adding To-Dos
Add a basic task to your playbook, one that doesn't have a timeframe!
Example: `todo <description>`

Example: `todo practice free throws`

```
Splash. Added that to the list.
[D][ ] practice free throws \n
Now you have 2 tasks in the list. \n
Night night. 😴
```

## Adding Deadlines
Adds a task with a specific "shot clock" (deadline). This helps you keep track of exactly when a task needs to be put to sleep.

Example: `deadline <description> /by <d/M/yyyy HHmm> \n`

Example: `deadline submit CS2103T project /by 2/3/2026 2359`

```
Splash. Added that to the list.
[D][ ] submit CS2103T project (by: 2/3/2026 23:59) \n
Now you have 2 tasks in the list. \n
Night night. 😴
```

## Adding Events
Perfect for scheduled games, meetings, or practice sessions that have a specific start and end time.

Example: `event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>>`

Example: `event Hackathon /from 14/2/2026 0900 /to 15/2/2026 1800`

```
Splash. Added that to the list. 
[E][ ] Hackathon (from: 14/2/2026 09:00 to: 15/2/2026 18:00)
Now you have 3 tasks in the list.
Night night. 😴
```

## Listing Tasks
Displays every tasks in your list.

Example: `list`

```
Here are the tasks in your list:
    1: [T][ ] practice free throws
    2: [D][ ] submit CS2103T project (by: 2/3/2026 23:59)
    3: [E][ ] Hackathon (from: 14/2/2026 09:00 to: 15/2/2026 18:00)
Stay locked in.
```

## Clearing List
Clear every tasks in your list.

Example: `clear`

```
All tasks have been cleared from your list. 🧹 
Time to rest!
```

## Finding Tasks
Find the specific tasks that match the keyword.

Example: `find <keyword>`

Example: `find project`

```
Here are the matching tasks in your list:
    1: [D][ ] submit CS2103T project (by: 2/3/2026 23:59)
```

## Marking & Unmarking Tasks
Mark the tasks as done or undone using the index!

Example: `mark <index>`

Example: `mark 2`

Example: `unmark <index>`

Example: `unmark 2`

```
Splash! 🏀 I've marked this task as done: 
  [T][X] practice free throws
Night night!
```

## Deleting Tasks
Removes a task from your list permanently.

Example: `delete <index>`

Example: `delete 1`

```
Ball game. 🏀 I've retired this task: 
[T][ ] warm up drills
Now you have 2 tasks in the list.
```


## Editing Tasks
Edit the task descriptions without having to delete and re-add it.

Example: `edit <index> <new_description>`

1. Editing a To-Do
Example: `edit 1 todo record highlights`

2. Editing a Deadline
Example: `edit 2 deadline submit CS2103T project /by 2/3/2026 2359`

3. Editing an Event
Example: `event Hackathon /from 14/2/2026 0900 /to 15/2/2026 1800`


```
Ball game. 🏀 I've retired this task: 
[T][ ] warm up drills
Now you have 2 tasks in the list.
```
