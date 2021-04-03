# Expense Tracker

## Dhrubo Karmaker

**Expense Tracker** is a Java application aimed to track your weekly expenditure. It tracks all your purchases of 
current and previous weeks. It lets you set thresholds and checks if you have crossed it. The app is suitable for any
age-group who have started earning and want to keep track of their budget. 

The project interests me because I find it handy to track my weekly expenses, and I can use it to spend my tuition 
income more efficiently.

**User Stories**
- As a user, I want to be able to add a new week to start with.
- As a user, I want to be able to retrieve data from previous weeks.
- As a user, I want to be able to add purchases to current week.
- As a user, I want to be able to be able to set expense threshold.
- As a user, I want to be able to see total expenses for the whole week.
- As a user, I want to be able to save week data in a file.
- As a user, I want As a user, I want to be able to be able to my week data from a file. 

**Phase 4: Task 2**
- I have chosen option 1. The AllWeeks class in model package is robust. The lookupWeek method throws NotFoundException 
if week with given week number is not found.

**Phase 4: Task 3**

There is a lot of coupling between AllWeeks class, and the classes that render different menus
(e.g MainMenuFrame,PurchaseMenu,etc.). The classes that render menu rely on AllWeeks for changes in its fields/states 
like list of weeks,week total etc. I think this can be refactored by using the Observer Design Pattern, making
the AllWeeks class extend Observable, and the menu classes to be Observer. This would remove the association between 
AllWeeks class and the menu classes.