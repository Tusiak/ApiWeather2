import org.json.JSONObject;

import java.io.IOException;
import org.junit.jupiter.api.*;

public class MainAppTest {

    @BeforeAll
    static void init() {
        System.out.println("Rozpoczęcie testów aplikacji");
    }

    @Test
    @DisplayName("Główny test połączenia")
    @Tag("dev")
    public void connectionTest() {
        HttpService httpService = new HttpService();
        JSONObject rootObject = null;
        try {
            String respone = httpService.connect(Config.APP_URL + "?q=" + "Warszawa" + "&appid=" + Config.APP_ID);
            rootObject = new JSONObject(respone);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(rootObject.getInt("cod") == 200);
    }
    @Test
    @DisplayName("Test pogody dla miasta Warszawa")
    public void connectByCityNameTest() {
        MainApp mainApp = new MainApp();
        String responseTest = mainApp.connectByCityName("Warszawa");
        JSONObject jsonObject = new JSONObject(responseTest);
        Assertions.assertEquals(200, jsonObject.getInt("cod"));
        Assertions.assertEquals("Warsaw", jsonObject.getString("name"));
    }
    @Test
    @DisplayName("Test pogody dla kodu 62-510")
    public void connectByZipCodeNameTest() {
        MainApp mainApp = new MainApp();
        String responseTest = mainApp.connectByZipCode("62-510");
        JSONObject jsonObject = new JSONObject(responseTest);
        Assertions.assertEquals(200, jsonObject.getInt("cod"));
        Assertions.assertEquals("Konin", jsonObject.getString("name"));
    }
    @AfterAll
    static void done() {
        System.out.println("Zakończenie testów");
    }
}















