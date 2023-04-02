package Main;

import GameStates.Playing;

public class Score {
    private int score;
    public Score()
    {
        score = 0;
    }

    public void setScore(int score)
    {
        if(Playing.paused == false && Playing.gameOver == false)
            this.score +=score;
    }
    public int getScore()
    {
        return score;
    }
}
