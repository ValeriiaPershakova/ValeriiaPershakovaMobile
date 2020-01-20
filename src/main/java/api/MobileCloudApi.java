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
import setup.TokenHandler;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static org.hamcrest.Matchers.lessThan;

/**
 * Class provides methods to work with MobileCloud API
 */
public class MobileCloudApi {
    private static RequestSpecification baseRequestConfiguration =
            new RequestSpecBuilder()
                    .addHeader("Authorization", format("Bearer %s", TokenHandler.getToken()))
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.ANY)
                    .setRelaxedHTTPSValidation()
                    .log(LogDetail.ALL)
                    .build();

    private static ResponseSpecification baseSuccessfullResponse =
            new ResponseSpecBuilder()
                    .expectResponseTime(lessThan(30000L))
                    .build();
    private static String baseUrl = getBaseUrl();

    /**
     * TakeDevice method send request to MobileCloud to take certain or random device if it is available
     *
     * @param capabilities contains desired device capabilities (platformName and UDID). At least platformName must be provided
     * @return UDID of the taken device
     */
    public static String takeDevice(DesiredCapabilities capabilities) {
        JsonObject body = getTakeRequestBody(capabilities);
        ValidatableResponse response = RestAssured.given(baseRequestConfiguration)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, format("https://%s/device", baseUrl))
                .prettyPeek()
                .then()
                .specification(baseSuccessfullResponse)
                .statusCode(HttpStatus.SC_OK);
        return response.extract()
                .path("desiredCapabilities.udid");
    }


    /**
     * installAppToDevice method install app on device (defined by UDID)
     *
     * @param app        absolute path to app file
     * @param deviceUDID
     */
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
        Matcher matcher = pattern.matcher(Driver.getURL());
        matcher.find();
        return matcher.group() + "/automation/api";
    }

    private static JsonObject getTakeRequestBody(DesiredCapabilities capabilities) {
        JsonObject jsonCaps = new JsonObject();
        jsonCaps.addProperty("platformName", String.valueOf(capabilities.getPlatform()));
        if (capabilities.getCapability("udid") != null) {
            jsonCaps.addProperty("udid", String.valueOf(capabilities.getCapability("udid")));
        }
        JsonObject body = new JsonObject();
        body.add("desiredCapabilities", jsonCaps);
        return body;
    }
}
