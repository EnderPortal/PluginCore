package fr.nkri.core.servers.ranks;
import lombok.Getter;

@Getter
public class Rank {

    private final int id;
    private final String name, prefix;

    /**
     * Represents a server role
     * @param id rank id
     * @param name rank name
     * @param prefix rank prefix
     */
    public Rank(final int id, final String name, final String prefix) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
    }
}
