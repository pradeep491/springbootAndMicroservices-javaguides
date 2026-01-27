## Requests and Payloads

1)POST REQUEST
================
http://localhost:8083/api/todos
{
"title": "Learn Microservices",
"description": "preparation",
"completed": false
}

2)GET REQUEST
=============
http://localhost:8083/api/todos/1

3)GET REQUEST
=============
http://localhost:8083/api/todos/getAllTodos

4)PUT Request
================
http://localhost:8083/api/todos/1/updateTodo

5)DELETE REQUEST
=================
http://localhost:8083/api/todos/3

6)PATCH REQUEST
================
http://localhost:8083/api/todos/1/complete

7)Patch Request
===============
http://localhost:8083/api/todos/1/in-complete
