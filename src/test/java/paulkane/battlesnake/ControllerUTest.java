package paulkane.battlesnake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.MoveResponse;
import paulkane.battlesnake.model.StartResponse;
import paulkane.battlesnake.model.domain.MOVE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static paulkane.battlesnake.model.domain.HEAD_TYPE.SAND_WORM;
import static paulkane.battlesnake.model.domain.TAIL_TYPE.PIXEL;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerUTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private MoveService moveService;
    @MockBean private StartService startService;

    @Test
    public void testStart() throws Exception {
        when(startService.start(any(BattleSnakeRequest.class)))
            .thenReturn(StartResponse.builder()
                .color("#ff00ff")
                .headType(SAND_WORM)
                .tailType(PIXEL)
                .build());
        this.mockMvc
            .perform(
                post("/start")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(getContent("start-request.json"))

            )
            .andExpect(status().isOk())
            .andExpect(content().json("{\n" +
                "\n" +
                "    \"color\": \"#ff00ff\",\n" +
                "    \"headType\": \"bendr\",\n" +
                "    \"tailType\": \"pixel\"\n" +
                "\n" +
                "}"));
    }

    @Test
    public void testMove() throws Exception {
        when(moveService.move(any(BattleSnakeRequest.class)))
            .thenReturn(MoveResponse.builder().move(MOVE.up)
                .build());
        this.mockMvc
            .perform(
                post("/move")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(getContent("move-request.json"))
            )
            .andExpect(status().isOk())
            .andExpect(content().json("{\"move\":\"up\"}"))
        ;
    }

    @Test
    public void testEnd() throws Exception {

        this.mockMvc
            .perform(
                post("/end")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(getContent("end-request.json"))
            )
            .andExpect(status().isOk());
    }

    @Test
    public void testPing() throws Exception {
        this.mockMvc
            .perform(
                post("/ping")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content("")
            )
            .andExpect(status().isOk());
    }

    private byte[] getContent(String fileName) throws Exception {
        Path classPathResource = Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI());
        return Files.readAllBytes(classPathResource);
    }
}