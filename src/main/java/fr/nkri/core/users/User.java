package fr.nkri.core.users;

import fr.nkri.core.users.profiles.GameProfile;
import lombok.Getter;

@Getter
public class User {

    private final int id;
    private final String username, mail;
    private final GameProfile profile;

    /**
     * Represents a user
     *
     * @param id account id
     * @param username player name
     * @param mail user mail
     * @param profile game profile
     */
    public User(final int id, final String username, final String mail, final GameProfile profile) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.profile = profile;
    }
}
