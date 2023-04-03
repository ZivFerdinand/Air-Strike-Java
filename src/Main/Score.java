package Main;

import GameStates.Playing;

public class Score {
    private int score;
    private Playing playing;

    public Score(Playing playing) {
        this.playing = playing;
        score = 0;
    }

    public void setScore(int score) {
        if (!Playing.isPaused() && !Playing.isGameOver() && playing.getPlayerPlane().getHealth() != 0)
            this.score += score;
    }

    public int getScore() {
        return score;
    }
}
