package paulkane.battlesnake;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.MoveResponse;
import paulkane.battlesnake.model.StartResponse;

/*
http://10.194.195.169:8080
 */
@Slf4j
@RestController
public class Controller {

    private StartService startService;
    private MoveService moveService;

    public Controller(StartService startService, MoveService moveService) {
        this.startService = startService;
        this.moveService = moveService;
    }

    @PostMapping("/start")
    public StartResponse start(@RequestBody BattleSnakeRequest startRequest) {
        return startService.start(startRequest);
    }

    @PostMapping("/move")
    public MoveResponse move(@RequestBody BattleSnakeRequest moveRequest) {
        log.info(moveRequest.getYou().getHealth() + ":" + moveRequest.getYou().getBody().toString());
        MoveResponse move = moveService.move(moveRequest);
        log.info("moved=" + move.getMove());
        return move;
    }

    @PostMapping("/end")
    public void end(@RequestBody BattleSnakeRequest endRequest) {
    }

    @PostMapping("/ping")
    public void ping() {
    }
}
