package fr.nkri.core.servers.services;

import fr.nkri.core.servers.ranks.Rank;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * All methods to manage the entire server part: grades, maintenance, etc.
 */
public interface IServerService {

    /**
     * Get all grades from the API
     * @return Rank array
     */
    CompletableFuture<Set<Rank>> getAllRanks();

}
