package GameStates;

import Main.GameEngine;
import Utils.AudioPlayer;

public enum GameState {
    PLAYING, MENU, OPTIONS, QUIT;

    public static GameState state = MENU;

    public static void setState(GameState state) {
        GameState.state = state;

        if (state == GameState.PLAYING) {
            GameEngine.audioPlayer.playSong(AudioPlayer.LEVEL_1);
        } else if (state == GameState.MENU) {
            GameEngine.audioPlayer.playSong(AudioPlayer.BACKGROUND);
        } else if (state == GameState.QUIT) {
            System.exit(0);
        }
    }
}
