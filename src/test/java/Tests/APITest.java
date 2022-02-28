package Tests;

import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.response.*;

public class APITest {

    @Test
    void Api_GetTest(){

//        Response rs = get("https://demo.bendigobank.com.au/banking/api/accounts/229cd6dee49abb7e08dcfcb62256006b");
//        System.out.println(rs.asString());
//        Assert.assertEquals(rs.getStatusCode(), 200);
        System.out.println(given().get("https://demo.bendigobank.com.au/banking/api/activity_feed?accountIds[]=8d210ed833c96e0c53e9fc44e519db73&singleAccountFeed=true").getBody().asString());



    }

}
