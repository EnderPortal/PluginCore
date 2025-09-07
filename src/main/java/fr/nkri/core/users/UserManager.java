package fr.nkri.core.users;

import fr.nkri.core.users.services.UserServiceImpl;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class UserManager {

    @Getter
    private static UserManager INSTANCE;

    private final Map<UUID, User> users;
    private final UserServiceImpl userService;

    public UserManager(){
        INSTANCE = this;
        this.users = new HashMap<>();
        this.userService = new UserServiceImpl();
    }
}
