Basic application description:
==============================

The application was written to open/create a new account that contains:

    1. The IBAN Account Number
    2. The account name
    3. The account balance
    
Using this information, you can lookup an account using an IBAN Account number.

You can also transfer funds between two accounts using the respective IBAN Account numbers and the transfer amount.

All necessary validations are performed on the accounts namely, 

    1. Negative balance checks
    2. Null checks
    3. Valid data checks
    4. Valid IBAN Account number validations
    
Lastly, you can also enter an IBAN in any format that you like. All IBAN numbers are saved in the database without spaces.           

Build the app from the command line
===================================

    mvn clean install

Starting the app from the command line
======================================

You need to be inside the "transfer-services" modules when starting up the application in one of 2 ways:

    1. mvn spring-boot:run
    2. java -jar ./target/transfer-services-0.0.1-SNAPSHOT.jar
    
Technologies used
=================     

    1. maven
    2. spring boot 2.0.3
    3. Swagger
    4. Jacoco
    5. postman
    6. H2 Database
    
    
H2 Database Console:
====================

The console can be accessed here:

    http://localhost:8080/transfer/services/h2-console
    
Username:
 
    root
    
password:

    root
    
    
Endpoints:
==========

The following endpoints were created:

    http://localhost:8080/transfer/services/openAccounts
    http://localhost:8080/transfer/services/findAccounts/{ibanAccountNumberToSearchFor}
    http://localhost:8080/transfer/services/transferFunds
    
Testing calling the endpoints:
==============================

A postman project was included to demonstrate all the scenarios under the "postman" folder, namely:

1. Open an account

        Method: POST
        Endpoint: http://localhost:8080/transfer/services/openAccounts
        
        Body:
    
            {
                "ibanAccountNumber": "SA44 2000 0001 2345 6789 1234",
                "accountName": "Mr. Saudi Man",
                "balance": "1500.00"
            }

2. Find an existing account
    
        Method: GET
        Endpoint: http://localhost:8080/transfer/services/findAccounts/SA4420000001234567891234
    
3. Transfer funds from one account to another  
    
        Method: PATCH
        Endpoint: http://localhost:8080/transfer/services/transferFunds
        
        Body:
    
            {
                "fromAccount": {
                    "ibanAccountNumber": "NL91 ABNA 0417 1643 00"
                },
                "toAccount": {
                    "ibanAccountNumber": "DE91 1000 0000 0123 4567 89"
                },
                "debitAmount": "500"
            }   

    
    
               
   