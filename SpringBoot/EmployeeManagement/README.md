Http Requests and Payload:
==========================
1)POST REQUEST
    http://localhost:8081/api/departments
    {
    "departmentName": "HR",
    "departmentDescription": "HR Department"
    }
2)GET REQUEST
    http://localhost:8081/api/departments/3
3)GET REQUEST
    http://localhost:8081/api/departments/getAllDepartments
4)PUT REQUEST
    http://localhost:8081/api/departments/3
    {
    "departmentName": "Account",
    "departmentDescription": "Account Department"
    }

###Employee Controller Request Data
====================================
1)POST REQUEST
    http://localhost:8081/api/departments/1/employees
    {
    "firstName":"pradeep",
    "lastName":"kumar",
    "email":"k.pradeep0491@gmail.com"
    }
2)GET REQUEST
    http://localhost:8081/api/departments/1/employees/1
    http://localhost:8081/api/departments/1/employees
3)PUT REQUEST
    http://localhost:8081/api/departments/1/employees/3
    {
    "firstName":"pradeep kumar",
    "lastName":"kandyala",
    "email":"pradeeppunarvika6722@gmail.com"
    }