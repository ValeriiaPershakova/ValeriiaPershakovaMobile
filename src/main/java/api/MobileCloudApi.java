package api;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.openqa.selenium.remote.DesiredCapabilities;
import setup.Driver;
import setup.TokenInstance;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static org.hamcrest.Matchers.lessThan;

public class MobileCloudApi {
    public static RequestSpecification baseRequestConfiguration =
            new RequestSpecBuilder()
                    .addHeader("Authorization", format("Bearer %s", TokenInstance.getToken()))
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.ANY)
                    .setRelaxedHTTPSValidation()
                    .log(LogDetail.ALL)
                    .build();
    public static ResponseSpecification baseSuccessfullResponse =
            new ResponseSpecBuilder()
                    .expectResponseTime(lessThan(30000L))
                    .build();
    private static String baseUrl = getBaseUrl();

    public static String takeDevice(DesiredCapabilities capabilities) {
        JsonObject jsonCaps = new JsonObject();
        jsonCaps.addProperty("platformName", String.valueOf(capabilities.getPlatform()));
        if (capabilities.getCapability("udid") != null) {
            jsonCaps.addProperty("udid", String.valueOf(capabilities.getCapability("udid")));
        }
        JsonObject json = new JsonObject();
        json.add("desiredCapabilities", jsonCaps);
        ValidatableResponse response = RestAssured.given(baseRequestConfiguration)
                .contentType(ContentType.JSON)
                .body(json)
                .request(Method.POST, format("https://%s/device", baseUrl))
                .prettyPeek()
                .then()
                .specification(baseSuccessfullResponse)
                .statusCode(HttpStatus.SC_OK);
        return response.extract()
                .path("desiredCapabilities.udid");
    }

    public static void installAppToDevice(File app, String deviceUDID) {
        RestAssured.given(baseRequestConfiguration)
                .contentType("multipart/form-data")
                .multiPart("file", app)
                .request(Method.POST, format("https://%s/storage/install/%s", baseUrl, deviceUDID))
                .prettyPeek()
                .then()
                .specification(baseSuccessfullResponse)
                .statusCode(HttpStatus.SC_CREATED);

    }

    private static String getBaseUrl() {
        Pattern pattern = Pattern.compile("\\w+[.\\w]+\\.\\w+");
        Matcher matcher = pattern.matcher(Driver.getDriver());
        matcher.find();
        return matcher.group() + "/automation/api";
    }
}
