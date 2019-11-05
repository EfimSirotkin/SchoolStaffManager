# UML Diagrams
1. [Use Case Diagram](#1)<br>
1.1 [Actors](#1.1)<br>
1.2 [Use Cases](#1.2)<br>
1.2.1 [Authorization](#1.2.1)<br>
1.2.2 [Registration](#1.2.2)<br>
1.2.3 [View Staff List](#1.2.3)<br>
1.2.4 [View Employee Information](#1.2.4)<br>
1.2.5 [Import Information from remote source](#1.2.5)<br>
1.2.6 [Export Information to the local source](#1.2.6)<br>
1.2.7 [Export Information to the remote source](#1.2.7)<br>
1.2.8 [Request For Update](#1.2.8)<br>
1.2.9 [Add Employee Information](#1.2.9)<br>
1.2.10 [Remove Employee record](#1.2.10)<br>
1.2.11 [Edit Employee Record](#1.2.11)<br>
1.2.12 [Send mail message](#1.2.12)<br>

2. [Activity Diagrams](#2)<br>
2.1 [Authorization](#2.1)<br>
2.2 [Registration](#2.2)<br>
2.3 [View Staff List](#2.3)<br>
2.4 [View Employee Information](#2.4)<br>
2.5 [Import Information from remote source](#2.5)<br>
2.6 [Export Information to the local source](#2.6)<br>
2.7 [Export Information to the remote source](#2.7)<br>
2.8 [Request For Update](#2.8)<br>
2.9 [Add Employee Information](#2.9)<br>
2.10 [Remove Employee record](#2.10)<br>
2.11 [Edit Employee Record](#2.11)<br>
2.12 [Send mail message](#2.12)<br>

3. [Sequence Diagrams](#3)<br>

4. [State Diagrams](#4)<br>
4.1 [Authorization](#4.1)<br>
4.2 [Add New Employee](#4.2)<br>
4.3 [Send mail message](#4.3)<br>

5. [Class Diagram](#5)<br>

6. [Deployment and Component Diagram](#6)<br>

<a name="1"/>

### 1. Use Case Diagram 
Use Case Diagram basically represent the following structure:
![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/UseCaseDiagram/UseCaseDiagram.png)

<a name="1.1"/>

#### 1.1 Actors
Actor | Description
--- | ---
User|A guest-authorized person who uses the application to view the list of employees, their personal information. Export is not allowed.
Service Worker|A person, registered in the system, who uses the application to order instruments and other machines, to view employees list. Request for personal data update is allowed.
Teacher|A person , registered in the system, who uses the application to order materials, books, etc., also can send request to the administry for personal data update.
Administrator|A person , registered in the system, who uses the application to add the records, edit them and also delete them. Sending email messages and remote export(*GDrive*) are allowed.
Admin|A person, who has all rights levels.

<a name="1.2"/>

#### 1.2 Use Cases

<a name="1.2.1"/>

##### 1.2.1 Authorization
**Description.** The use case **"Authorization"** allows the user to log in.
Event flow:
1. The User click "Authorization" button.
2. The app moves to the authorization page.
3. The user inputs "Login" and "Password".
4. The user click "Log in" button.
5. The app checks the equality of input data with database. In case of incorrect input data the user repeats steps 3 and 4.  
6. The app moves to Home Page.
7. The end.

<a name="1.2.2"/>

##### 1.2.2 Registration
**Description.** The use case **"Registration"** allows the visitor to create his account in the system.
Event flow:
1. The user presses the "Enter" button on the toolbar.
2. The application goes to the login page.
3. The user presses the "Register" button.
4. The user enters "Login", "First Name", "Last Name", "Password" and "Date of Birth".
5. The user presses the "Register" button.
6. The application checks the validity and uniqueness of the data. If the data is incorrect, the application displays an error message, then the user repeats steps 4 and 5.
7. The application adds a new user to the system.
8. The application directs the user to the Home page.
9. The end.

<a name="1.2.3"/>

##### 1.2.3 View Staff List
**Description.** The use case **"View Staff List"** allows the user to view the full list of employees.
Event flow:

1. The user presses the button "View the list of employees" on the main page of the attachment.
2. The application checks for data on the local machine.
3. If there is no data on the local machine, the application downloads them via the Internet.
4. If there is no Internet connection, an error message is displayed, after which the application goes to the main page.
5. Upon successful loading, a list of all employees is displayed in the viewing window.
6. The end.

<a name="1.2.4"/>

##### 1.2.4 View Employee Information
**Description.** The Use Case **"View Employee Information"** allows the user to view detailed information about the employee.
Event flow:
1. In the main application window, the user presses the "View employee list" key.
3. In the window that opens, the user enters information about the employee, the database is searched.
4. The application checks for the presence of an employee record with the specified data.
5. If there is no entry, the application displays an error message, after which a window for viewing the list of employees opens.
6. Upon successful search, the application displays detailed information about the employee.
7. The end.

<a name="1.2.5"/>

##### 1.2.5 Import Information from remote source
**Description.** The Use Case **"Import Information from remote source"** allows the user to import information from a remote source..
Event flow:
1. The user clicks the "View employee list" button in the main application window.
2. The application displays a viewing window.
3. The user clicks the "Import from GDrive" button.
4. The application checks the internet connection.
5. If there is no connection, an error message is displayed, after which the application goes to the home page.
6. Data capture occurs through * Google API *.
7. The application checks the success of data import
7. If an error occurs, a corresponding message is displayed and the application goes to the home page.
8. If import is successful, a message is displayed.
8. The end.

<a name="1.2.6"/>

##### 1.2.6 Export Information to the local source
**Description.** The Use Case **"Export Information to the local source"** allows the user to export data to local storage.
Event flow:
1. The user clicks the "View Employee List" button on the home page.
2. The application goes to the viewing page.
3. The user clicks the "Export to local storage" button.
4. The application creates an Excel file and fills it with data.
5. If there are no errors, the application displays a message about the export success.
6. The end.

##### 1.2.7 Export Information to the remote source<a name="1.2.7"></a>
**Description.** The Use Case **"Export Information to the remote source"** allows an administrator to export data to ** GDrive **.
Event flow:  
1. The administrative employee on the application’s home page clicks the “View employee list” button.
2. The application opens a viewing window.
3. The administrative employee clicks the "Export Data to GDrive" button.
4. The application creates an Excel file and initializes it with data.
5. If the operation is successful, the application checks for an Internet connection.
6. If the connection is remote, the application sends the Excel file via the Google API to GDrive.
7. The application verifies the success of the shipment, an appropriate message is displayed on the screen.
8. The application returns control to the page for viewing the list of employees.
9. The end.

<a name="1.2.8"/>

##### 1.2.8 Request For Update
**Description.** The Use Case **"Request For Update"** allows household or teaching staff to send requests to the administration for updating their personal data.
Event flow:
1. The teacher or manager on the main page of the application click the button "View the list of employees."
2. The application goes to the viewing window.
3. The teacher or supply manager searches for their record in the database.
4. The teacher or supply manager edits the fields in which data updating is necessary.
5. The application checks the availability of the Internet connection.
6. If there is a connection, the application generates a message.
7. The application sends an email to the administration via SMTP protocol.
8. Upon successful sending, the application displays the appropriate message on the screen and returns control to the home page.
9. The End

<a name="1.2.9"/>

##### 1.2.9 Add Employee Information<a name="1.2.9"></a>
**Description.** The Use Case **"Add Employee Information"** allows an administrative employee to add employee records.
Event flow:  
1. The administrative employee on the main page of the application clicks the "Edit" button.
2. The application opens an editing window.
3. The administrative employee clicks on the "Add employee record" button.
4. The application opens a form to fill out.
5. The administrative employee fills in personal and additional data about the employee.
6. The application check the uniqueness and validity of the data.
7. Upon successful completion of the check, an entry about the employee is added to the database.
8. The end.

##### 1.2.10 Remove Employee record<a name="1.2.10"></a>
**Description.** The Use Case **"Remove Employee record"** allows the administrator to delete the employee record.
Event flow:  
1. The administrative employee on the main page of the application clicks the "Edit" button.
2. The application opens an editing window.
3. The administrative employee clicks on the "Delete employee record" button.
4. The administrative employee enters data to search for records.
5. The application checks for records.
6. If available, the entry is deleted and a message indicating successful deletion is displayed.
7. The end.

##### 1.2.11 Edit Employee Record<a name="1.2.11"></a>
**Description.** The Use Case **"Edit Employee Record"** allows the administrator to edit the employee record..
Event flow:  
1. The administrative employee on the main page of the application clicks the "Edit" button.
2. The application opens an editing window.
3. The administrative employee clicks on the "Edit employee record" button.
4. The administrative employee enters data to search for records.
5. The application checks for records.
6. If there is a record, the application opens a form for editing.
7. The administrative employee edits the employee data.
8. The application checks the validity and correctness of the entered data.
9. Upon successful verification, the application displays a message.
10. The end.

##### 1.2.12 Send mail message<a name="1.2.12"></a>
**Description.** The Use Case **"Send mail message"** allows an administrative employee to notify employees of upcoming events..
Event flow:    
1. The administrative employee on the main page of the application clicks the "View employee list" button.
2. The application opens a viewing window.
3. The administrative employee clicks on the "Send message" button.
4. The administrative employee enters data to search for records.
5. The application checks for records.
6. If there is a record, the application retrieves the email address.
7. The application checks for an internet connection.
8. If available, the administrative employee draws up the title and body of the message.
9. The application sends a message by mail using the * SMTP * protocol.
10. If sent successfully, a message is displayed.
11. The end.
<a name="2"/>
### 2. Activity Diagrams

<a name="2.1"/> 

##### 2.1 Authorization
When filling out the authorization form, you are logged in. If the data does not match, an error message is displayed.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/Login.png)

<a name="2.2"/>

##### 2.2 Registration
When filling out data forms, they are validated. If the data is incorrect, an error message is displayed with the request to repeat the action, otherwise a new user is registered and goes to the main page.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/Register.png)

<a name="2.3"/>

##### 2.3 View Staff List
The user views the list of employees after clicking on the "View the list of employees" button on the main application screen.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/ViewStaffList.png)
<a name="2.4"/>

##### 2.4 View Employee Information
The user views detailed information about the employee.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/ViewEmployeeInformation.png)

<a name="2.5"/>

##### 2.5 Import Information from remote source
Import data with * GDrive *. If there is no Internet connection, an error message is displayed. If import fails, return to the application home page.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/ImportFromRemote.png)

<a name="2.6"/>

##### 2.6 Export Information to the local source
The user, by clicking the "Export Data" button, initiates the creation of an * Excel * file, its initialization and saving to the computer.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/ExportDataToLocalSource.png)

<a name="2.7"/>

##### 2.7 Export Information to the remote source
When the "Export to GDrive" button is pressed, the administrative employee initiates the formation of an * Excel * file and sends it to the * Gdrive * storage. If there is no Internet connection, the operation is terminated and the application go to the viewing page.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/ExportToGDrive.png)
<a name="2.8"/>

##### 2.8 Request For Update
Service or teaching staff sends a message to the administration requesting data updates.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/RequestForModification.png)

<a name="2.9"/>

##### 2.9 Add Employee Information<a name="2.9"></a>
The administrative employee on the edit page can add an employee record, his personal and additional information. The application checks the validity of the data and creates a new record.
![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/AddEmployee.png)

<a name="2.10"/>

##### 2.10 Remove Employee record<a name="2.10"></a>
An administrator on the edit page can delete an existing entry. The application deletes the entry when it is present.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/DeleteEmployee.png)

<a name="2.11"/>

##### 2.11 Edit Employee Record<a name="2.11"></a>
The administrative employee on the edit page can initiate a change in the data of the employee record in case the record is present in the database. Otherwise, the application informs about the error and returns control to the edit page.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/EditEmployee.png)

<a name="2.12"/>

##### 2.12 Send mail message<a name="2.12"></a>

The administrative employee on the watch page can send a message to the employees. The employee compiles the title and body of the message, after which the application sends the message by mail via the * SMTP * protocol. The function is available only with an internet connection.

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Activity%20Diagrams/SendNotification.png)

<a name="3"/>
### 3. Sequence Diagrams<a name="3"></a>

The sequence diagram of the main use cases is presented below.:

![alt text](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/Sequence%20Diagram/SequenceDiagram.png)

### 4. State Diagrams<a name="4"></a>
These diagrams were created using mockups.:
* Home page  https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Mockups/Main.JPG
* View page  https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Mockups/ViewPage.JPG
* Login page  https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Mockups/LoginPage.png
* Registration page   https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Mockups/RegistrationPage.png
* Send email page  https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Mockups/SendMail.JPG

##### 4.1 Authorization<a name="4.1"></a>
![Autorization](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/StateDiagrams/LoginStateDiagram.png)

##### 4.2 Add New Employee<a name="4.2"></a>
![Add to database](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/StateDiagrams/AddingPersonStateDiagram.png)

##### 4.3 Send email message<a name="4.3"></a>

![Send Email](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/StateDiagrams/SendMailStateDiagram.png)

### 5. Class Diagram<a name="5"></a>
![Class diagram](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/ClassDiagram/ClassDiagram.png)

### 6. Deployment and Component Diagram <a name="6"></a>
![Deployment diagram](https://github.com/EfimSirotkin/SchoolStaffManager/blob/master/Diagrams/ComponentDeploymentDiagram/ComponentDeploymentDiagram.png)
