package war.naval.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;

	@ManyToOne
	@JoinColumn(name = "opponent_id")
	private Player opponent;

	@ManyToOne
	@JoinColumn(name = "start_player_id")
	private Player start;

	@ManyToOne
	@JoinColumn(name = "turn_player_id")
	private Player turn;

	private Timestamp creation;

	public Game() {
	}

	public Game(Long id, Player player, Player opponent, Player start, Player turn) {
		this.id = id;
		this.player = player;
		this.opponent = opponent;
		this.start = start;
		this.turn = turn;
	}

	public Game(Long id, Player player, Player opponent, Player start, Player turn, Timestamp creation) {
		this.id = id;
		this.player = player;
		this.opponent = opponent;
		this.start = start;
		this.turn = turn;
		this.creation = creation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public Player getStart() {
		return start;
	}

	public void setStart(Player start) {
		this.start = start;
	}

	public Player getTurn() {
		return turn;
	}

	public void setTurn(Player turn) {
		this.turn = turn;
	}

	public Timestamp getCreation() {
		return creation;
	}

	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}

}
