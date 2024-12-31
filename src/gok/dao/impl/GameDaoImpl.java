package gok.dao.impl;

import gok.dao.GameDao;
import gok.model.Game;
import gok.model.User;
import gok.util.JDBCUtil;

import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

    @Override
    public List<Game> getGame() throws Exception {
        Game game = new Game();
        List<Object> objectList = JDBCUtil.queryAll(game,"ORDER BY score DESC");
        ArrayList<Game> gameList = new ArrayList<>();
        for (Object o:objectList){
            Game g = (Game) o;
            gameList.add(g);
        }
        return gameList;
    }
    //多态
    @Override
    public List<Game> getGame(Game game) throws Exception {
        List<Object> objectList = JDBCUtil.queryAll(game);
        List<Game> gameList = new ArrayList<>();
        for (Object o:objectList){
            Game g = (Game) o;
            gameList.add(g);
        }
        return gameList;
    }

    @Override
    public int deleteGame(List list,Game game) throws Exception {

        int a = JDBCUtil.delAll(list,game);
        return a;
    }

    @Override
    public int addUser(Game game) throws Exception {
        int a = JDBCUtil.addAll(game);
        return a;
    }

    @Override
    public int updateGame(Game game) throws Exception {
        int a = JDBCUtil.upAll(game);
        return a;
    }


}
