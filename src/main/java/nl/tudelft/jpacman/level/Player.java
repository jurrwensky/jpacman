package nl.tudelft.jpacman.level;

import java.util.Map;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.sprite.AnimatedSprite;
import nl.tudelft.jpacman.sprite.Sprite;

/**
 * A player operated unit in our game.
 *
 * @author Jeroen Roosen 
 */
public class Player extends Unit {

    /**
     * The amount of points accumulated by this player.
     */
    private int score;

    /**
     * The animations for every direction.
     */
    private final Map<Direction, Sprite> sprites;

    /**
     * The animation that is to be played when Pac-Man dies.
     */
    private final AnimatedSprite deathSprite;

    /**
     * <code>true</code> iff this player is alive.
     */
    private boolean alive;

    /**
     * {@link Unit} iff this player died by collision, <code>null</code> otherwise.
     */
    private Unit killer;

    private int lives;

    public int getLives(){
        return lives;
    }

    /**
     * Creates a new player with a score of 0 points.
     *
     * @param spriteMap
     *            A map containing a sprite for this player for every direction.
     * @param deathAnimation
     *            The sprite to be shown when this player dies.
     */
    protected Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation,int lives) {
        this.lives=lives;
        this.score = 0;
        this.alive = true;
        this.sprites = spriteMap;
        this.deathSprite = deathAnimation;
        deathSprite.setAnimating(false);
    }

    /**
     * Returns whether this player is alive or not.
     *
     * @return <code>true</code> iff the player is alive.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets whether this player is alive or not.
     *
     * If the player comes back alive, the {@link killer} will be reset.
     */
    public void setAlive(boolean isAlive) {
        if (isAlive) {
            deathSprite.setAnimating(false);
            this.killer = null;
        }
        if (!isAlive) {
            deathSprite.restart();
        }
        this.alive = isAlive;
    }

    private void decrementLives(){
        lives--;
    }

    public void collisionJoueur(Unit killer){
        decrementLives();
        if(lives<=0){
            this.setKiller(killer);
            this.setAlive(false);
        }
    }


    /**
     * Returns the unit that caused the death of Pac-Man.
     *
     * @return <code>Unit</code> iff the player died by collision, otherwise <code>null</code>.
     */
    public Unit getKiller() {
        return killer;
    }

    /**
     * Sets the cause of death.
     *
     * @param killer is set if collision with ghost happens.
     */
    private void setKiller(Unit killer) {
        this.killer =  killer;
    }

    /**
     * Returns the amount of points accumulated by this player.
     *
     * @return The amount of points accumulated by this player.
     */
    public int getScore() {
        return score;
    }

    @Override
    public Sprite getSprite() {
        if (isAlive()) {
            return sprites.get(getDirection());
        }
        return deathSprite;
    }

    /**
     * Adds points to the score of this player.
     *
     * @param points
     *            The amount of points to add to the points this player already
     *            has.
     */
    public void addPoints(int points) {
        score += points;
    }
}
