package result;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * A database for the game results.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GameResult {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name of the player.
     */
    @Column(nullable = false)
    private String player;

    /**
     * Indicates whether the player has solved the game.
     */
    private boolean solved;

    /**
     * The rounds of played by the player.
     */
    @Column(nullable = false)
    int roundCount;
    /**
     * The duration of the game.
     */
    @Column(nullable = false)
    private long duration;

    /**
     * The timestamp when the results was saved.
     */
    @Column(nullable = false)
    private ZonedDateTime created;

    /**
     * Gets the time when the program persists.
     */
    @PrePersist
    protected void onPersist() {
        created = ZonedDateTime.now();
    }

    /**
     * Gets the player's name.
     * @return player's name
     */

    public String getPlayerName() {
        return player;
    }

    /**
     * Gets the game's total rounds.
     * @return the total number of rounds.
     */
    public int getRoundCount() {
        return roundCount;
    }

    /**
     * Gets the game duration in seconds.
     * @return the game duration in seconds
     */
    public long getGameDuration() {
        return duration;
    }

    /**
     * Gets the date.
     * @return the date
     */
    public ZonedDateTime getDateTime() {
        return created;
    }

    /**
     * Gets whether the game is solved.
     * @return {@code true} if the game is solved and
     * {@code false} if the game is not solved.
     */
    public boolean isSolved() {
        return solved;
    }
}
