package Utils;

public class Constants {

    public static class UIData {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * 2);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * 2);
        }

        public static class URMButtons {
            public static final int URM_SIZE_DEFAULT = 56;
            public static final int URM_SIZE = 112;
        }

        public static class PauseButton {
            public static final int PAUSE_SIZE_DEFAULT = 56;
            public static final int PAUSE_SIZE = 56;

        }
    }
    public static class DamageDealer {
        public static final int ENEMY_UFO_LASER_POINT = 50;
        public static final int ENEMY_HELICOPTER_LASER_POINT = 30;
        public static final int ENEMY_HIT_POINT = 5;
        public static final int UFO_REDUCE = 7;
        public static final int HELICOPTER_REDUCE = 7;
        public static final int PLAYER_REDUCE_LASER = 5;
    }
    
    public static class Path {
        public static String MAIN_MENU_BG = "/res/sprite/Main-Menu-BG.jpg";
        public static String BACKGROUND_GAME_1 = "/res/sprite/Main-Background-Cloud.png";
        public static String BACKGROUND_GAME_2 = "/res/sprite/Main-Background-2-Cloud.png";
        public static String BUTTON_MAINMENU = "/res/sprite/Button-MainMenu.png";
        public static String MAINMENU_PANEL = "/res/sprite/Button-MainMenu-Panel-2.png";
        public static String MAP_1 = "/res/sprite/Main-Background.png";
        public static String MAP_1_HVR = "/res/sprite/Main-Background-Hover.png";
        public static String MAP_1_CLK = "/res/sprite/Main-Background-Click.png";
        public static String MAP_2 = "/res/sprite/Main-Background-2.png";
        public static String MAP_2_HVR = "/res/sprite/Main-Background-2-Hover.png";
        public static String MAP_2_CLK = "/res/sprite/Main-Background-2-Click.png";
        public static String ICON = "src/res/sprite/Game-Logo.png";
        
        public static String PLAYER_PLANE = "/res/sprite/Plane-Blue.png";
        public static String PLAYER_PLANE_SHADOW = "/res/sprite/Plane-Shadow-40.png";
        public static String ENEMY_HELICOPTER = "/res/sprite/Enemy-Helicopter.png";
        public static String ENEMY_HELICOPTER_HIT = "/res/sprite/Enemy-Helicopter-Hit.png";
        public static String ENEMY_UFO = "/res/sprite/Enemy-UFO.png";
        public static String ENEMY_UFO_SHADOW = "/res/sprite/Enemy-UFO-Shadow.png";
        public static String ENEMY_UFO_HIT = "/res/sprite/Enemy-UFO-Hit.png";
        public static String EXPLOSION = "/res/sprite/Explosion.png";
        public static String LASER = "/res/sprite/Laser-Sprite.png";
        public static String LASER_ENEMY = "/res/sprite/Laser-Enemy.png";
        public static String COIN = "/res/sprite/Coin.png";
        public static String HEALTH_ICON = "/res/sprite/Health-Icon.png";

        public static String PAUSE_PANEL = "/res/sprite/Pause-Menu.png";
        public static String URM_BUTTONS = "/res/sprite/URM-Buttons.png";
        public static String PAUSE_BUTTON = "/res/sprite/Pause-Button.png";
        public static String CHOOSE_MAP = "/res/sprite/Choose-Map-Title.png";
        public static String DEATH_PANEL = "/res/sprite/Death-Panel.png";
    }

    public static class Health{
        public static int PLAYER_HEALTH = 5;
        public static int HELICOPTER_HEALTH = 10;
        public static int UFO_HEALTH = 20;
    }
    public static class InitialPosition{
        public static int HELICOPTER_INITIAL_POS_Y = -400;
        public static int UFO_INITIAL_POS_Y = -450;
    }
    public static class ObjectSizeData{
        public static ObjectSize PLAYER_PLANE = new ObjectSize(150, 150);
        public static ObjectSize ENEMY_UFO = new ObjectSize(200, 200);

        public static ObjectSize ENEMY_HELICOPTER = new ObjectSize(130, 160);
        public static ObjectSize EXP_HELICOPTER_PLANE = new ObjectSize(100, 96);
        public static ObjectSize EXP_UFO_PLANE = new ObjectSize(200, 192);
        public static ObjectSize EXP_PLAYER_PLANE = new ObjectSize(250, 250);
        public static ObjectSize ENEMY_LASER = new ObjectSize(31, 32);
        public static ObjectSize PLAYER_LASER = new ObjectSize(20, 38);
        public static ObjectSize STAR = new ObjectSize(25, 25);
        public static ObjectSize PAUSE_PANEL = new ObjectSize(516, 440);
        public static ObjectSize GAMEOVER_PANEL = new ObjectSize(516, 516);
    }
}


