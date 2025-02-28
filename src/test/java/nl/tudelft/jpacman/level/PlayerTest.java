package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unitaire pour la classe Player.
 */
public class PlayerTest {

    private Player player;

    private  Ghost ghost;
    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        PlayerFactory playerFactory = new PlayerFactory(sprites);
        GhostFactory ghostFactory = new GhostFactory(sprites);
        player = playerFactory.createPacMan(1);
        ghost  = ghostFactory.createBlinky();

    }

    @Test
    void testIsAlive() {
        assertThat(player.isAlive()).isTrue();
    }

    @Test
    void testIsDead() {
        player.collisionJoueur(ghost);
        assertThat(player.isAlive()).isFalse();
    }
}
