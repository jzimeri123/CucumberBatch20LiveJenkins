package utils;

import APISteps.APIStepsClass;
import org.json.JSONObject;

public class APIPayloadConstants {
    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"asana\",\n" +
                "  \"emp_lastname\": \"lawrance\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1993-01-12\",\n" +
                "  \"emp_status\": \"permanent\",\n" +
                "  \"emp_job_title\": \"QA Manager\"\n" +
                "}";
        return createEmployeePayload;
    }
    public static String createEmployeeJsonPayload(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","asana");
        obj.put("emp_lastname","lawrance");
        obj.put("emp_middle_name","ms");
        obj.put("emp_gender","F");
        obj.put("emp_birthday","1993-01-12");
        obj.put("emp_status","permanent");
        obj.put("emp_job_title","QA Manager");
        return obj.toString();
    }

    public static String createEmployeeJsonPayloadDynamic(String emp_firstname,
                                                          String emp_lastname,
                                                          String emp_middle_name,
                                                          String emp_gender,
                                                          String emp_birthday,
                                                          String emp_status,
                                                          String emp_job_title){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname",emp_firstname);
        obj.put("emp_lastname",emp_lastname);
        obj.put("emp_middle_name",emp_middle_name);
        obj.put("emp_gender",emp_gender);
        obj.put("emp_birthday",emp_birthday);
        obj.put("emp_status",emp_status);
        obj.put("emp_job_title",emp_job_title);
        return obj.toString();
    }


    public static String updateCreatedEmployeeJsonPayloadDynamic(String employee_id,
                                                                 String emp_firstname,
                                                                 String emp_lastname,
                                                                 String emp_middle_name,
                                                                 String emp_gender,
                                                                 String emp_birthday,
                                                                 String emp_status,
                                                                 String emp_job_title){

        employee_id=APIStepsClass.employee_id;
        JSONObject obj = new JSONObject();
        obj.put("employee_id", employee_id);
        obj.put("emp_firstname",emp_firstname);
        obj.put("emp_lastname",emp_lastname);
        obj.put("emp_middle_name",emp_middle_name);
        obj.put("emp_gender",emp_gender);
        obj.put("emp_birthday",emp_birthday);
        obj.put("emp_status",emp_status);
        obj.put("emp_job_title",emp_job_title);
        return obj.toString();
    }


}
