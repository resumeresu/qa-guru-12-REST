package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static helpers.CustomApiListener.withCustomTemplates;

public class BaseSpec {

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(new RequestLoggingFilter())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
}
