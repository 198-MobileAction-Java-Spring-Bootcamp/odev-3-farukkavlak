# Son Teslim Tarihi ve Saati 19/06/2022 - 23:00
Car Registration System;<br>
TODO/Features
- [X] Registration user with name,surname,username,email and password
- [X] Password validation - Password must contain at least one uppercase letter, one lowercase letter, one number and one symbol.(This feature was added last, so some images have no password validation.)
- [X] It is not possible to register to the system with the same user name or email.
- [X] The user can only change his password, to do this user must be logged in. This is not a "I forgot password "feature.The password should be kept in the database in an encoded.
- [X] The password should be kept in the database in an encoded.
- [X] In the password change process, the old password should also be taken as a parameter. (Such as old password, new password, new password again for check)
- [X] The user can be deleted. When user is deleted, the cars owned by this user should also be deleted from the system.
- [X] The user should be able to query all the cars has
- [X] Users logged into the system can add their own cars.
- [X] The same car (same license plate) cannot be added by more than one person.
- [X] Plates cannot contain spaces, lowercase letters or Turkish characters.
- [X] Cars can be deleted or updated.
- [X] There should be a service that brings the vehicles according to the brand and model.
## Controllers
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/Controllers.png"></img>
## Schemas
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/Schemas.png"></img>
#### Register User
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/1-signUpSaved.png"></img>
#### Register User - Username is already taken - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/2-signUpError1.png"></img>
#### Register User - Email is already taken - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/3-signUpError2.png"></img>
#### Change Password - Not logged in - Access Denied - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/3-signUpError2.png"></img>
#### Login 
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/5-login.png"></img>
#### Change Password - Old password is not correct - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/6-changePasswordErrOld.png"></img>
#### Change Password - New passwords do not match - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/7-changePasswordErrNewPassword.png"></img>
#### Change Password
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/8-changePassword.png"></img>
#### Passwords encoded in database
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/9-encoded.png"></img>
#### Save Car - Not logged in - Access Denied - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/10-saveCarErrDenied.png"></img>
#### Login Error - Username or password is wrong - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/11-loginErr.png"></img>
#### Save Car
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/12-saveCar.png"></img>
#### Update Car
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/13-update.png"></img>
#### Save Car - Plate is invalid - Lower case - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/14-plateIsNotValid.png"></img>
#### Save Car - Plate is invalid - Space - Error 
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/15-plateIsNotValid2.png"></img>
#### Save Car - Plate is invalid - Turkish Letters - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/16-plateIsNotValid3.png"></img>
#### Save Car - You cannot add same car twice - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/17-saveCarErrTwice.png"></img>
#### Save Car
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/18-saveCar2.png"></img>
#### Register User
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/19-signUpSaved2.png"></img>
#### Save Car
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/20-saveCar3.png"></img>
#### Get Car with brand and model
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/21-getCarWith.png"></img>
#### Get user owned cars
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/22-getCars.png"></img>
#### Delete Car
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/23-carDeleted.png"></img>
#### Get user owned cars
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/24-getCars2.png"></img>
#### Delete user with owned cars
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/25-userDeleted.png"></img>
#### Get Car with brand and model
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/26-getCarWith2.png"></img>
#### Logout
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/27-logout.png"></img>
#### Register User Password Validation - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/28-signupPasswordStrength.png"></img>
#### Register User Password Validation - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/29-signupPasswordStrength2.png"></img>
#### Register User Password Validation
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/30-signupPasswordStrength3.png"></img>
#### Change Password Validation - Error
<img src="https://github.com/198-MobileAction-Java-Spring-Bootcamp/odev-3-farukkavlak/blob/main/SwaggerUI/31-changePasswordStrength.png"></img>


