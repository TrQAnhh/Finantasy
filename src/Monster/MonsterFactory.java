package Monster;

import Entities.Entity;
import Main.GamePanel;

public class MonsterFactory{
    public static Entity createMonster(String monsterType, GamePanel gamePanel) {
        switch (monsterType) {
            case "GhostRider":
                return new MON_GhostRider(gamePanel);
            case "Pumpkin":
                return new MON_Pumpkin(gamePanel);
            case "Reaper":
                return new MON_Reaper(gamePanel);
            case "Spider":
                return new MON_Spider(gamePanel);
            case "Gate Keeper":
                return new MON_GateKeeper(gamePanel);
            case "Bloody Slime":
                return new MON_BloodySlime(gamePanel);
            case "Robot":
                return new MON_Robot(gamePanel);
            case "Green Dragon":
                return new MON_GreenDragon(gamePanel);
            case "Red Pheonix":
                return new MON_RedPheonix(gamePanel);
            case "Boss":
                return new MON_Boss(gamePanel);
            default:
                throw new IllegalArgumentException("Unknown monster type: " + monsterType);
        }
    }
}
