# Command : mvn spring-boot:run  || Server on : http://localhost:8080/cart

Make sure you have Java 17 and Maven installed on your system.

CartNatixis Application
CartNatixis is a Spring Boot application that handles shopping cart data. The application provides a VerificationService that validates the data of each cart before processing them further.

Getting Started
To run the application, follow these steps:

Clone the repository to your local machine.

-git clone https://github.com/Christloyd/shop-cart.git

Run to create Main.json : 

-powershell : mvn spring-boot:run

-server on : http://localhost:8080/cart

A Main.json will appear in C:\temp, and everytime you refresh http://localhost:8080/cart in your browser, the price will change in Main.json.


AOP Logging
The application utilizes Aspect-Oriented Programming (AOP) to log all service method calls. The LogAspect class contains @Before, @After, and @AfterReturning advice to log the execution of service methods. 
Additionally, the aspect intercepts method calls to the VerificationService using @AfterReturning advice. 
This allows the aspect to log cart data to a file named Main.txt in the C:/temp directory.


Command

mvn install is primarily meant for building and packaging your project, and it does not execute the code itself. The aspect you provided 
is using AOP to write to the file fichierMain.txt, but this code is not directly invoked during the build phase.

To create the fichierMain.txt file with the AspectJ aspect during the mvn install phase, you need to execute the code that uses the Aspect. 
In your case, you have AspectJ advice (@AfterReturning) that is executed after the returning pointcut.

However, when you run mvn install, it doesn't execute your application code that involves the @AfterReturning advice, and thus the file is not created.

To CREATE the file : mvn spring-boot:run which will take in account AOP behavior and @AfterReturning 


arduino
Copy code
mvn spring-boot:run
The application will be accessible at http://localhost:8080.


Features

VerificationService
The VerificationService is responsible for validating the data of each cart and calculating the total price of all carts. It performs the following validations:

Name validation: Checks if the name is not empty and contains only valid characters.
Price validation: Checks if the price is a non-negative number within a specified range.
Quantity validation: Checks if the quantity is a positive integer within a specified range.
If any of the carts fail the validation, the service throws an exception with error details in JSON format.

LogAspect
The LogAspect is an Aspect in the application that logs all calls to the services. It includes @Before and @After advice to log before and after service method execution. The aspect also intercepts the VerificationService method calls using @AfterReturning advice and logs the cart data to a file named fichierMain.txt in the C:/temp directory.


Contributing
Contributions to the CartNatixis application are welcome. Please fork the repository, create a feature branch, and submit a pull request with your changes.


Regards
Christloyd

