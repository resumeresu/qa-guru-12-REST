package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseSpec {

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured()
                    .setRequestTemplate("request.ftl")
                    .setResponseTemplate("response.ftl"))
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
}
