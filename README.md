## ReqRes-API-RestAssured

API automation challenge using RestAssured.

#### API documentation and rules

The API rules along with its implementation and execution instructions can be found [here](https://reqres.in/). 

#### Requirements

For the execution of the project it is necessary to have:
- Maven
- Java 1.8 ou +

##### Execution

To run the project, navigate to the root folder with a terminal of your choice and use the command:

`$ mvn test`

The command will run all tests contained in the project.

To run specific test methods: 

`$ mvn test -Dtest=Test1,Test2`

To run a specific group of tests:

`$ mvn test -DincludeGroups=TestGroup1,TestGroup2` 

To exclude a specific test suite group, use the command: 

`$ mvn test -DexcludeGroups=TestGroup3,TestGroup4`

To delete a specific package containing a test suite, use the command: 

`$ mvn test -Dtest="test.java.com.service.map.**"`

##### End
