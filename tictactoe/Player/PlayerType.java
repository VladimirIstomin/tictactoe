package tictactoe.Player;

public enum PlayerType {
    USER("user"),
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private final String name;

    private PlayerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PlayerType getPlayerTypeByName(String name) {
        for (PlayerType playerType : PlayerType.values()) {
            if (playerType.getName().equals(name)) {
                return playerType;
            }
        }
        return null;
    }
}
