# todoList-david
sample todoList web app, Spring boot + react + redux

## User Guide

### get project
get project from: https://github.com/dingdaofeng/todoList-david.git <br>

todoList-backend is backend project based on Spring boot <br>
todoList-frontend is frontend project based on react & redux <br>

### Backend Project

run backend project:

```
cd todolist-backend
gradle bootRun
```


A restful API project will be started in localhost:8080 <br>
6 APIs are provided by the project:

```
POST http://localhost:8080/item/add
POST http://localhost:8080/item/update
POST http://localhost:8080/item/delete/{id}
POST http://localhost:8080/item/complete/{id}
GET http://localhost:8080/item/get/{id}
GET http://localhost:8080/item/list

```

The important model `TodoItem` is defined as below:

```
// unique id
private long id;
    
private String name;
private String description;
    
private String dueDate;
    
// to mark if the todo task is completed
private boolean done;
// to mark if the todo task is overtime
private boolean overTime;
```

to test project:

```
cd todolist-backend
gradle test
```

### Frontend Project
Frontend project depends on APIs of Backend Project<br>
run frontend project:

```
cd todolist-frontend
npm i
npm run dev
```

A react project will be started in localhost:3000<br>

On top menu, there are 3 tab named "Pending", "Done" and "add".<br>

####Show Pending Item
Click "Pending", show pending items.<br>
Pending todoItem will be shown in a table.<br>
If any item is overtime, the Due date of it will be shown as red and bold<br>
In Operation column, click "Finish" to complete item, click "delete" to delete item.

####Show Done Item
Click "Done", show Done items.<br>
In Operation column, click "delete" to delete item.

#### Add Item
Click "Add", show add item page.<br>

#### View & Updat Item
No matter Pending item table or Done item table, click the name of item can go to the update item page.<br>
In this page, user can view the detail of item and can update item or finish item.



## License

Create React App is open source software [licensed as MIT](https://github.com/facebook/create-react-app/blob/master/LICENSE).
