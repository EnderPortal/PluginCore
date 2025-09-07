package fr.nkri.core.servers.ranks;

import java.util.ArrayList;
import java.util.List;

public class RankManager {

    /**
     * List of server grades, retrieve using the API
     */
    private final List<Rank> ranks;

    public RankManager(){
        this.ranks = new ArrayList<>();
    }

    /**
     * Add a grade to the list
     * @param rank grade to add to my list
     */
    public void addRank(final Rank rank){
        this.ranks.add(rank);
    }

    /**
     * Retrieve a rank using its id,
     * in local storage
     *
     * @param id rank id
     * @return Rank Object
     */
    public Rank getRankById(final int id) {
        for (Rank rank : ranks) {
            if (rank.getId() == id) {
                return rank;
            }
        }
        return null;
    }
}
