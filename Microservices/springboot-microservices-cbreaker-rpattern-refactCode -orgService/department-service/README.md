1)To save the Department
http://localhost:8080/api/departments

Request:
========
{
"departmentName":"IT",
"departmentDescription":"Information Technology",
"departmentCode":"IT002"
}
2)Get Deprtment
http://localhost:8080/api/departments/IT001
http://localhost:8082/api/departments/IT001

3) Hit the below endpoint URL(refresh), to get the refresh values
   http://localhost:8080/actuator/refresh - POST
    
    