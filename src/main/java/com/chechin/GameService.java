package com.chechin;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameService {

    private final AtomicLong idCounter = new AtomicLong();

    private Map<Long, Game> gamesById = new HashMap<>();

    public Long addGame(Game initalGame) {
        Long gameId = idCounter.incrementAndGet();
        gamesById.put(gameId, initalGame);
        return gameId;
    }

    public Game getGame(Long id) {
        if (gamesById.containsKey(id)) {
            return gamesById.get(id);
        } else {
            throw new IllegalArgumentException("No game found for id: " + id);
        }
    }

    public void deleteGame(Long id) {
        if (gamesById.containsKey(id)) {
            gamesById.remove(id);
        } else {
            throw new IllegalArgumentException("No game found for id: " + id);
        }
    }

    public Game getGameNextGeneration(Long id) {
        Game game = gamesById.get(id);
        if (game != null) {
            return game.getNextGeneration();
        } else {
            throw new IllegalArgumentException("No game found for id: " + id);
        }
    }
}
