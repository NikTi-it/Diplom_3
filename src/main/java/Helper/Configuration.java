package Helper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static Helper.Constants.BASE_PATH;
import static Helper.Constants.BASE_URL;

public class Configuration {

    protected RequestSpecification getConfiguration() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH)
                .build();
    }
}
