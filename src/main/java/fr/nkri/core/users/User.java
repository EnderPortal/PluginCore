package fr.nkri.core.users;

import fr.nkri.core.users.profiles.GameProfile;
import lombok.Getter;

@Getter
public class User {

    private final int id;
    private final String username, mail;
    private final int rankId;
    private final GameProfile profile;

    /**
     * Represents a user
     *
     * @param id account id
     * @param username player name
     * @param mail user mail
     * @param rankId rank id
     * @param profile game profile
     */
    public User(final int id, final String username, final String mail, final int rankId, final GameProfile profile) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.rankId = rankId;
        this.profile = profile;
    }
}
