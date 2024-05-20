package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bets_games")
public class BetGame implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    private Bet bet;

    @OneToOne
    private ResultPrediction prediction;
}
