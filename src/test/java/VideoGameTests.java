import config.VideoGameConfig;
import config.VideoGameEndpoints;
import objects.VideoGame;
import org.junit.Test;


import static io.restassured.RestAssured.*;

public class VideoGameTests extends VideoGameConfig {

    String gameBodyJSON =
            "{\n" +
                    "  \"category\": \"Platform\",\n" +
                    "  \"name\": \"Mario\",\n" +
                    "  \"rating\": \"Mature\",\n" +
                    "  \"releaseDate\": \"2012-05-04\",\n" +
                    "  \"reviewScore\": 85\n" +
                    "}";

    @Test
    public void getAllGames() {
        given()
                .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void createNewGameByJSONPOST(){

        given()
                .body(gameBodyJSON)
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();

    }

    @Test
    public void createNewGameByXMLPOSTaddnew(){
        String gameBodyXML =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<VideoGameRequest>\n" +
                        "\t<category>Platform</category>\n" +
                        "\t<name>Mario</name>\n" +
                        "\t<rating>Mature</rating>\n" +
                        "\t<releaseDate>2012-05-04</releaseDate>\n" +
                        "\t<reviewScore>85</reviewScore>\n" +
                        "</VideoGameRequest>";
        given()
                .body(gameBodyXML)
                .contentType("application/xml")
                .header("Accept", "application/xml") //OR .accept("application/xml")
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void updateGamePUT(){
        given()
                .body(gameBodyJSON)
        .when()
                .put("videogame/5")
        .then();

    }

    @Test
    public void deleteGame(){
        given()
                .accept("text/plain")
        .when()
                .delete("videogame/5")
        .then();
    }

    @Test public void getSingleGame(){
        given()
                .pathParam("videoGameId", 5)
        .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
        .then();
    }

    @Test
    public void testVideoGameSerializationByJSON(){
        VideoGame videoGame = new VideoGame("Shooter","Quake","Mature","1995-05-01",99);

        given()
                .body(videoGame)
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
}}