package fr.nkri.core.users.profiles;

import lombok.Getter;
import lombok.Setter;

@Getter
public class GameProfile {

    private final int id;

    @Setter
    private int coins;

    /**
     * Player profile for game data
     * @param id account id
     * @param coins coins amount
     */
    private GameProfile(final int id, final int coins) {
        this.id = id;
        this.coins = coins;
    }
}
