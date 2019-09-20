package war.naval.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "battlefield")
public class Battlefield {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	@ManyToOne
	@JoinColumn(name = "field_id")
	private Field field;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;

	private Boolean ship;

	private Boolean impact;

	public Battlefield() {
	}

	public Battlefield(Game game, Field field, Boolean ship, Boolean impact) {
		this.game = game;
		this.field = field;
		this.ship = ship;
		this.impact = impact;
	}

	public Battlefield(Long id, Game game, Field field, Boolean ship, Boolean impact) {
		this.id = id;
		this.game = game;
		this.field = field;
		this.ship = ship;
		this.impact = impact;
	}

	public Battlefield(Long id, Game game, Field field, Player player, Boolean ship, Boolean impact) {
		this.id = id;
		this.game = game;
		this.field = field;
		this.player = player;
		this.ship = ship;
		this.impact = impact;
	}

	public Long getId() {
		return id;
	}

	public Game getGame() {
		return game;
	}

	public Field getField() {
		return field;
	}

	public Boolean getShip() {
		return ship;
	}

	public Boolean getImpact() {
		return impact;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public void setShip(Boolean ship) {
		this.ship = ship;
	}

	public void setImpact(Boolean impact) {
		this.impact = impact;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
