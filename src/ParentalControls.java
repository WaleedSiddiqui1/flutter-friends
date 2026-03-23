/**
 * This class represents the parental controls.
 * @author Theodore Igbeyi
 */

import java.time.*;
import java.time.temporal.*;

public class ParentalControls {
    /** The player's playtime limit */
    static private LocalTime[] timeLimit = {LocalTime.MIN, LocalTime.MIN};
    /** The total playtime of the user */
    static Duration playtimeTotal = Duration.ZERO;
    /** The total playtime and number of sessions for specific games */
    static long[][] gameTimes = {{0, 0}, {0, 0}, {0, 0}};
    /** A list of pets the user has */
    private Pet[] pets;

    /**
     * The Parental Controls constructor
     */
    public ParentalControls() {}

    /**
     * Checks if the current time is within the playtime limit
     * @return true if the current time is within the playtime limit and vice versa
     */
    public Boolean isWithinPlaytimeLimit() {
        if (timeLimit == null) return true;

        LocalTime currentTime = LocalTime.now();

        if (currentTime.isAfter(timeLimit[0]) && currentTime.isBefore(timeLimit[1])) return true;
        return false;
    }

    /**
     * Updates the total playtime, the total for a specific game, and the number of sessions for that game
     * @param time the time to be added to the playtime total
     * @param gameNum the number of the game file for which the playtime is being added to
     */
    public void addPlaytime(Duration time, int gameNum) {
        playtimeTotal = playtimeTotal.plus(time);
        gameTimes[gameNum-1][0] += time.toMinutes();
        gameTimes[gameNum-1][1]++;
    }

    /**
     * Translates a number of minutes into days, hours, and minutes
     * @param durMinutes the number of minutes to be translated
     * @return the string containing the number of minutes in days, hours, and minutes
     */
    private String toString(long durMinutes) {
        String str;
        long days, hours, minutes = durMinutes;

        days = Math.round(minutes / 1440);

        minutes -= days*1440;

        hours = Math.round(minutes / 60);

        minutes -= hours*60;

        str = String.format("%dd, %dhr, %dmin", days, hours, minutes);
        return str;
    }

    /**
     * Resets the playtime total and the totals for the individual games
     */
    public void resetPlaytimeTotal() {
        playtimeTotal = Duration.ZERO;
        gameTimes = new long[][]{{0, 0}, {0, 0}, {0, 0}};
    }

    /**
     * Restores a pet's stats
     * @param petNum the save file number the pet to be restored is in
     */
    public void restorePet(int petNum) {
        Pet pet = pets[petNum-1];
        int max = -1;

        if (pet.getStage() == 0) { max = 100; }
        else if (pet.getStage() == 1) { max = 200; }

        pet.setHealth(max);
        pet.setFullness(max);
        pet.setSleep(max);
        pet.setHappiness(max);
    }

    // Getters

    /**
     * Gets the playtime limit
     * @return the start and end times in which the player can play
     */
    public LocalTime[] getTimeLimit() { return timeLimit; }

    /**
     * Gets the playtime total in days, hours, and minutes
     * @return the playtime total
     */
    public String getPlaytimeTotal(boolean asFormatted) {
        if (asFormatted) return toString(playtimeTotal.toMinutes());
        else return playtimeTotal.toString();
    }

    /**
     * Gets the average playtime for a specific game in a day hour minute format
     * @param gameNum the number of the file the user wants the average playtime for
     * @return the average playtime for a specific game
     */
    public String getGameTimeAverages(int gameNum) {
        if (gameTimes[gameNum-1][1] != 0) return toString(gameTimes[gameNum-1][0] / gameTimes[gameNum-1][1]);
        else return toString(0);
    }

    public long[][] getGameTimes() { return gameTimes; }

    // Setters

    /**
     * Sets the playtime limit
     * @param times the new start and end times in which the player can play
     */
    public void setTimeLimit(LocalTime[] times) { timeLimit = times; }

    public void setPlaytimeTotal(Duration total) { playtimeTotal = total; }

    public void setGameTimes(int gameNum, long gameTotal, long numSessions) {
        gameTimes[gameNum-1][0] = gameTotal;
        gameTimes[gameNum-1][1] = numSessions;
    }

}
