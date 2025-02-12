# Alden User Guide

Introducing Alden. The bot to remind you on everything.

![Ui](https://github.com/user-attachments/assets/4c1266d2-2fdc-4038-aa23-b7b89b1c3a06)

## Adding deadlines

To add a deadline, use the `deadline` command followed by the task description and the due date in "yyyy/MM/dd HHmm" format

Example: `deadline Watch Movie /by 2025/02/11 1300`

Expected outcome: Adds a deadline task with the description "Watch Movie" and the due date "2025/02/11 1300".

```
Got it. I've added this task:
 [D][] Watch Movie (by: Feb 11 2025 1:00 PM)
Now you have 1 task in the list.
```

## Adding todos

To add a todo, use the `todo` command followed by the task description. 

Example: 'todo Watch Netflix`

Expected outcome: Adds a todo task with the description "Watch Netflix". 

```
Got it. I've added this task:
  [T][] Watch Netflix
Now you have 2 tasks in the list.
```

## Adding events

To add an event, use the `event` command followed by the event description, start date, and end date in "yyyy/MM/dd HHmm" format

Example: `event Disney+ Screening /from 2025/02/14 1300 /to 2025/02/15 1500`

Expected outcome: Adds a deadline task with the description "Watch Movie" and the due date "2025/02/11 1300".

```
Got it. I've added this task:
 [E][] Disney+ Screening (from: Feb 14 2025 1:00 PM to: Feb 15 2025 3:00PM)
Now you have 3 tasks in the list.
```

## Listing tasks

To list all tasks, use the `list` command.

Example: `list`

Expected outcome: Shows all tasks in the task list

```
Here are the tasks in your list:
1. [D][] Watch Movie (by: Feb 11 2025 1:00 PM)
2. [T][] Watch Netflix
3. [E][] Disney+ Screening (from: Feb 14 2025 1:00 PM to: Feb 15 2025 3:00PM)
```

## Marking tasks

To mark a task as done, use the `mark` command followed by the task number

Example: `mark 1`

Expected outcome: Marks the first task in the list as done.

```
Nice! I've marked this task as done:
    [D][X] Watch Movie (by: Feb 11 2025 1:00 PM)
```


## Unmarking tasks

To unmark a task as done, use the `unmark` command followed by the task number

Example: `unmark 1`

Expected outcome: Unmarks the first task in the list from done to not done.

```
Nice! I've marked this task as done:
    [D][] Watch Movie (by: Feb 11 2025 1:00 PM)
```


## Deleting tasks

To delete a task as done, use the `delete` command followed by the task number

Example: `delete 1`

Expected outcome: Deletes the first task in the list.

```
Noted. I've removed this task:
  [D][] Watch Movie (by: Feb 11 2025 1:00 PM)
Now you have 2 tasks in the list.
```

### Finding tasks 
To find tasks based on certain keywords, use the `find` command followed by the task description. 

Example: `find Watch`

Expected outcome: Lists all tasks with matching descriptions from the task list

```
Here are the matching tasks in your list:
1. [T][] Watch Netflix (from Feb 11 2025 1:00 PM to Feb 13 2025 3:00 PM)
```
 
## Sorting tasks
To sort the list of tasks based of their chronological order, use the `sort date` or `sort chronological` command.

Example: `sort date`

Expected outcome: Sort the tasks in the task list based on their chronological dates.

```
Here are the tasks in your list:
1. [T][] Watch Netflix (from Feb 11 2025 1:00 PM to Feb 13 2025 3:00 PM)
2. [E][] Disney+ Screening (from: Feb 14 2025 1:00 PM to: Feb 15 2025 3:00 PM)
```

## Stopping the application
To stop the application, use the `bye` command.

Example: `bye`

Expected outcome: Stops the application.
