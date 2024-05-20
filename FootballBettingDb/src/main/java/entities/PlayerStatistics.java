package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics implements Serializable {
    //тук трябва да се създаде композитен ключ. В java 2 от начините са:
    // - чрез използване на клас, анотиран с @Embeddable, който държи в себе си
    //   полетата които ще представляват композитния ключ и трябва да имаме equals() и hashCode() методите
    //   пренаписани за да може сравнението на записите да бъде напълно уникално. След това в класа в който ни е необходим
    //   композитния ключ като поле се слага обект от embeddable класа, който ще представлява нашият композитен ключ и
    //   се анотира с @EmbeddedId
    // - чрез самите @Id-анотации както ще го направим сега

    @Id
    @ManyToOne
    @JoinColumn
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn
    private Player player;

    @Column(name = "scored_goals")
    private Short scoredGoals;

    @Column(name = "player_assists")
    private Short playerAssists;

    @Column(name = "played_minutes_during_game")
    private Short playedMinutesDuringGame;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Short getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(Short scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public Short getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(Short playerAssists) {
        this.playerAssists = playerAssists;
    }

    public Short getPlayedMinutesDuringGame() {
        return playedMinutesDuringGame;
    }

    public void setPlayedMinutesDuringGame(Short playedMinutesDuringGame) {
        this.playedMinutesDuringGame = playedMinutesDuringGame;
    }
}
