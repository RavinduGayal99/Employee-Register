An interviwer ask for this.

Spring Boot Practical Test

Employee Register
 Create a simple employee register database containing following entities.
o Employee
o Users
o Roles
 You can define columns as you want.
 User controller should have user login endpoint managed with JWT.
 Feed two roles into Roles table named ‘user’ and ‘admin’ as initial data.
 Employee controller should have CRUD operations.
 Additionally, Employee controller should have endpoint to upload a profile picture.
o Do note store the image in database column.
o Store the image in a storage folder with proper naming.
o Add an endpoint to download the image from employee id.
 Delete endpoint should only works for ‘admin’ roles.
Extra Work (Optional)
1. Age in Days auto update
a. Add a column as ‘current_age_in_days’ into the Employee.
b. Create a scheduled cron job to update the ‘current_age_in_days’ every midnight.
c. (Calculate the age in days using the birthday)
2. Create a PDF download endpoint using Jasper report.
a. PDF should show all Employees as a list.
