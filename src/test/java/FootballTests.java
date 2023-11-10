import config.FootballConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;
//import static java.lang.reflect.Array.get;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTests extends FootballConfig {

    @Test
    public void getDetailsOfOneArea() {
        given()
                .queryParam("areas", 2076)
        .when()
                .get("/areas")
        .then();
    }
    @Test
    public void getDetailsOfMultipleAreas(){
        String areaIDs = "2267,2077,2080";

        given()
                .urlEncodingEnabled(false)
                .queryParam("areas",areaIDs)
        .when()
                .get("/areas")
        .then();
    }

    @Test
    public void getDateFoundedAssert(){
        given()
         .when()
                .get("teams/57")
         .then()
                .body("founded", equalTo(1886))
                .body("name", equalTo("Arsenal FC"));
    }

    @Test
    public void getFirsTeamName(){
        given()
        .when()
                .get("competitions/2021/teams")
        .then()
                //.body("teams.runningCompetitions[0].name[0]", equalTo("Premier League"));
                .body("teams.squad[0].id[0]", equalTo(4832));
    }

    @Test
    public void getAllTeamData(){
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);

    }



}