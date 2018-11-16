============================================================================================

								String Calculator version 1.0
								
============================================================================================


Use:
This utility can be used to evaluate mathematical expressions.
Eg. 7+(6*5^2+3-4/2) will return 158.00

How to use:
Postman can be used to upload a text file containing mathematical expressions. An example of
the same can be found attached in the project root directory with the name "sample.txt".

Instructions:
1.  Navigate to the root directory of the project in the command prompt.
2.  Run the command mvn spring-boot:run, to lauch the application.
3.  The application will be deployed on port 8500, which can be changed in application.properties
	file.
4.  Use the url "localhost:8500/solve" on postman, to execute a POST request, with the input file
	in the payload.
5.  You should receive the following output for the sample.txt file.
[
    "Case #1: 158.00",
    "Case #2: INVALID EXPRESSION",
    "Case #3: INVALID EXPRESSION",
    "Case #4: -3.00"
]							