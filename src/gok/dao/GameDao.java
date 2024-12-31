package gok.dao;

import gok.model.Game;
import gok.model.User;

import java.util.List;

public interface GameDao {
    List<Game> getGame() throws Exception;
    int addUser(Game game) throws Exception;
    int updateGame(Game game) throws Exception;
    List<Game> getGame(Game game) throws Exception;
    int deleteGame(List list,Game game) throws Exception;
}

