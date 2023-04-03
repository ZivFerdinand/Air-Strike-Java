package GameStates;

import Main.GameEngine;
import Utils.AudioPlayer;

public enum GameState {
    SPLASH_SCREEN, PLAYING, MENU, OPTIONS, QUIT, ;

    public static GameState state = SPLASH_SCREEN;

    public static void setState(GameState stateToChange) {
        if (state == GameState.PLAYING) {
            GameEngine.audioPlayer.playSong(AudioPlayer.BACKGROUND);
        }
        GameState.state = stateToChange;

        if (stateToChange == GameState.PLAYING) {
            GameEngine.audioPlayer.playSong(AudioPlayer.LEVEL_1);
        } 
        else if (stateToChange == GameState.QUIT) {
            System.exit(0);
        }
    }
}
