package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.equalTo;

//this is to set order of execution
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    //task1
    //create an employee using restAssured
    //1. Set the base URI
    String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String employee_id;

    //We can generate the token in Postman and paste it here
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzE0MzU4MjIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTczMTQ3OTAyMiwidXNlcklkIjoiNjkzNiJ9.-_hOK43fHWkZiK6PFlG46Do32MDx0bmsQKAczlBxLqI";

    //from JUnit
    @Test
    //we add "a" so this test executes before b
    public void aCreateAnEmployee(){
        // use Given keyword to prepare request specifications
        // we will mention the header-->content type and Authorization
        //body-->the request body
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"asana\",\n" +
                        "  \"emp_lastname\": \"lawrance\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1993-01-12\",\n" +
                        "  \"emp_status\": \"permanent\",\n" +
                        "  \"emp_job_title\": \"QA Manager\"\n" +
                        "}");

        //hitting the endpoint
        //when keyword to send the request to the server
        //make sure to have the request attached to the "when" keyword
        Response response = request.when().post("/createEmployee.php");
        //print the response
        response.prettyPrint();
        //verify that the response code is 201
        response.then().assertThat().statusCode(201);
        //verify that the message is Employee Created
        response.then().assertThat().body("Message",equalTo("Employee Created"));
        //verify that the employee first name is Kiwi
        response.then().assertThat().body("Employee.emp_firstname",equalTo("asana"));
        //we have to retrieve the employee ID first so we can use it in automation, after creating the employee
        //store it in global variable so we can use it anywhere
        //jsonPath is the method which returns the value in string against the key specified
        employee_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println("The employee ID is: "+employee_id);

    }

    @Test
    //we add "b" so this test executes after "a"
    public void bgetCreatedEmployee(){
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        //we get the employee ID from when we perform GetOneEmployee, so the path
        //to get the ID from the body is as follows
        String tempEmpId=response.jsonPath().getString("employee.employee_id");
        //now we compare it with the previous one we have in global variable
        Assert.assertEquals(tempEmpId,employee_id);
    }

    @Test
    public void cUpdateEmployee(){
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"denis\",\n" +
                        "  \"emp_lastname\": \"sekar\",\n" +
                        "  \"emp_middle_name\": \"msa\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2020-11-07\",\n" +
                        "  \"emp_status\": \"probation\",\n" +
                        "  \"emp_job_title\": \"trainee\"\n" +
                        "}");

        Response response=request.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);

    }

    @Test
    //we add "b" so this test executes after "a"
    public void dgetUpdatedEmployee(){
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        Response response=request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        //we get the employee ID from when we perform GetOneEmployee, so the path
        //to get the ID from the body is as follows
        String tempEmpId=response.jsonPath().getString("employee.employee_id");
        //now we compare it with the previous one we have in global variable
        Assert.assertEquals(tempEmpId,employee_id);
    }

    @Test
    public void eGetAllEmployees(){
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token);
        Response response=request.when().get("/getAllEmployees.php");
        response.prettyPrint();
    }

    @Test
    public void fGetJobTitle(){
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token);
        Response response=request.when().get("/jobTitle.php");
        response.prettyPrint();
    }

    @Test
    public void gGetEmployeeStatus(){
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token);
        Response response=request.when().get("/employeementStatus.php");
        response.prettyPrint();
    }
    //automate all GET CALLS
}
