# BuddyBot User Guide

Introduction
BuddyBot is an intelligent chatbot designed to help users manage their tasks efficiently. Whether you need to keep track of deadlines, events, or simple to-dos, BuddyBot makes it easy with an intuitive command-based interface.


## Adding deadlines

Description: Adds a task with a deadline.

Command: /deadline <task description> /by <yyyy-mm-dd>

Example: /deadline Finish CS2103T project /by 2025-02-28

Expected Output: Task added: Finish CS2103T project (by: 28 Feb 2025)

## Adding Events

Description: Adds an event with a specific date and time.

Command : /event <event description> /at <yyyy-mm-dd hh:mm>

Example: /event NUS Hackathon /at 2025-03-05 14:00

Expected Output: Event added: NUS Hackathon (at: 5 Mar 2025, 2:00 PM)

## Adding To-Dos

Description: Adds a task without a deadline.

Command : /todo <task description>

Example: /todo Buy groceries

Expected Output: Task added: Buy groceries


## Viewing Tasks

Description: Displays all stored tasks.

Command: /list

Expected Output: 
1. [T] Buy groceries
2. [D] Finish CS2103T project (by: 28 Feb 2025)
3. [E] NUS Hackathon (at: 5 Mar 2025, 2:00 PM)

## Marking Tasks as Done

Description: Marks a specific task as completed.

Command: /done <task number>

Example: /done 2

Expected Output: Task marked as done: Finish CS2103T project


## Deleting Tasks

Description: Removes a task from the list.

Command : /delete <task number>

Example: /delete 1

Expected Output: Task deleted: Buy groceries


## Finding Tasks

Description: Searches for tasks containing a specific keyword.

Command: /find <keyword>

Example: /find project

Expected Output: 1. [D] Finish CS2103T project (by: 28 Feb 2025)

## Exiting the Program

Description: Exits BuddyBot.

Command: /bye

Expected Output: Goodbye! See you soon.