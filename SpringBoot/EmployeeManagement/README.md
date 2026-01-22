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