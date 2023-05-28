![task-management](https://user-images.githubusercontent.com/102857782/231239116-6dfb70af-3fdc-40e2-8043-746f4b32931a.jpg)


# Task_Planner
A Task Planner helps in ease for user to plan his tasks and execute them accordingly


<h3>Teck Stacks</h3>
<p>* Java</p>
<p>* Spring Boot</p>
<p>* Spring Data Jpa</p>
<p>* MySql</p>
<p>* Maven</p>
<p>* Lombok</p>
<p>* Swagger</p>


<h3>Features</h3>
<p>* Validations</p>
<p>* User Authentications and Authorization</p>
<p>* Manage and Controls His Tasks and Sprint</p>


<b>URL</b> : https://taskplanner-production-bcaf.up.railway.app

<h3>User Controller</h3> 


![usercontroller](https://user-images.githubusercontent.com/102857782/231237475-adddd6cc-fc33-4e9e-b7b0-a8054a4e777f.png)


<h3>Task Controller</h3>

![taskcontroller](https://user-images.githubusercontent.com/102857782/231237533-2c4df4cc-805c-40cf-be93-07796d14716f.png)


<h3>Sprint Controller</h3>

![sprintcontroller](https://user-images.githubusercontent.com/102857782/231237581-4852582f-47bc-4321-b0bc-c004574673ad.png)


<h3>Login/Logout Controller</h3>

![logincontroller](https://user-images.githubusercontent.com/102857782/231237630-e35b1fb8-1582-44aa-ae68-5b94f45c90ed.png)

 <b>NOTE</b> :  All the endpoints are protected by hence they requires the JWT Token except for registering the user and loging the user. To use these protected apis user must need to login by his login credentials and once the user is successfully logged in user will be provided the JWT Token and is supposed to sent in the headers section named as <b>" Token : <jwt_token> ".
