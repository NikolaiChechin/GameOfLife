package com.chechin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public Long createNewGame(@RequestBody boolean[][] initialBoard) {
        Game game = new Game(initialBoard);
        Long gameId = gameService.addGame(game);
        return gameId;
    }

    @RequestMapping(path = "/games/{gameId}", method = RequestMethod.GET)
    public boolean[][] getGame(@PathVariable Long gameId) {
        Game game = gameService.getGame(gameId);
        if (game == null) {
            throw new GameNotFoundException();
        }
        return game.getBoard();
    }

    @RequestMapping(path = "/games/{gameId}/next-gen", method = RequestMethod.POST)
    public boolean[][] getGameNextGeneration(@PathVariable Long gameId) {
        Game nextGeneration;
        try {
            nextGeneration = gameService.getGameNextGeneration(gameId);
        } catch (IllegalArgumentException e) {
            throw new GameNotFoundException();
        }
        return nextGeneration.getBoard();
    }

    @RequestMapping(path = "/games/{gameId}", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable Long gameId) {
        try {
            gameService.deleteGame(gameId);
        } catch (IllegalArgumentException e) {
            throw new GameNotFoundException();
        }
    }

}
