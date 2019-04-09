package paulkane.battlesnake;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import paulkane.battlesnake.model.BattleSnakeRequest;
import paulkane.battlesnake.model.MoveResponse;
import paulkane.battlesnake.model.StartResponse;
import paulkane.battlesnake.model.domain.Snake;

/*
http://10.194.194.143:8080
 */
@Slf4j
@RestController
public class Controller {

    private final StartService startService;
    private final MoveService moveService;

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
        Snake you = moveRequest.getYou();
        String name = you.getName();
        int turn = moveRequest.getTurn();
        log.info("{}-{}: [{}] {}", turn, name, you.getHealth(), you.getBody().toString());
        MoveResponse move = moveService.move(moveRequest);
        log.info("{}-{}: moved={}", turn, name, move.getMove());
        return move;
    }

    @PostMapping("/end")
    public void end(@RequestBody BattleSnakeRequest endRequest) {
        log.info("==========================Game Ended==========================");
        log.info("Winner was={}", getWinner(endRequest));
        log.info("==========================Game Ended==========================");
    }

    private String getWinner(BattleSnakeRequest endRequest) {
        if (endRequest.getBoard().getSnakes().size() > 0) {
            return endRequest.getBoard().getSnakes().get(0).getName();
        }
        return "a draw";
    }

    @PostMapping("/ping")
    public void ping() {
    }
}
