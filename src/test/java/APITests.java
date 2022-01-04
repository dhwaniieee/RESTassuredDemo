import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class APITests {
//    @Test
    void test1(){
        Response responce = get("https://reqres.in/api/users?page=2");
        System.out.println("Responce"+responce.asString());
        System.out.println("status code:"+responce.getStatusCode());
        System.out.println("Body:" + responce.getBody().asString());
        System.out.println("Time Taken:"+responce.getTime());
        System.out.println("Header:"+responce.getHeader("content-type"));

        int statuscode = responce.statusCode();
        Assert.assertEquals(statuscode,200);

    }
//    @Test
    void test2(){
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200);
    }
    @Test
    public void testGet(){
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data[4].first_name",equalTo("George")).
                body("data.first_name",hasItems("George","Rachel"));

    }
    @Test
    public void testPost(){
        Map<String,Object> map = new HashMap<String,Object>();
//
        JSONObject request = new JSONObject();

        request.put("name","Dhwani");
        request.put("job","employee");

        System.out.println(request.toJSONString());
        System.out.println("Dhwani");
        baseURI = "https://reqres.in/api";
        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/users").
        then().
                statusCode(201).
                log().all();
    }

    @Test
    public void testput(){
        JSONObject request = new JSONObject();

        request.put("name","Dhwani");
        request.put("job","employee");

        System.out.println(request.toJSONString());
        System.out.println("Dhwani");
        baseURI = "https://reqres.in/api";
        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void testPatch(){
        JSONObject request = new JSONObject();

        request.put("name","Dhwani");
        request.put("job","employee");

        System.out.println(request.toJSONString());
        System.out.println("Dhwani");
        baseURI = "https://reqres.in";
        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("/api/users/2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void testDelete(){

        baseURI = "https://reqres.in";
        when().
                delete("/api/users/2").
        then().
                statusCode(204).
                log().all();
    }
}
