package war.naval.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import war.naval.dto.ShipDto;
import war.naval.model.Battlefield;
import war.naval.model.Field;
import war.naval.model.Game;
import war.naval.model.Player;
import war.naval.repository.BattlefieldRepository;
import war.naval.repository.FieldRepository;
import war.naval.repository.GameRepository;
import war.naval.repository.PlayerRepository;

@Controller()
public class BattlefieldController {

	@Autowired
	BattlefieldRepository battlefieldRepository;

	@Autowired
	FieldRepository fieldRepository;

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerRepository playerRepository;

	@ResponseBody
	@GetMapping("/battlefield/attack")
	private boolean sendAttack(Long idGame, String username, int x, int y) throws Exception {

		Game game = gameRepository.findOne(idGame);
		Player player = playerRepository.findByUsername(username);

		if (game != null && player != null && game.getTurn() == player) {

			Player opponent = null;
			if (game.getPlayer() == player) {
				opponent = game.getOpponent();
			} else if (game.getOpponent() == player) {
				opponent = game.getPlayer();
			}

			Field field = fieldRepository.findByXAndY(x, y);
			Battlefield battlefield = battlefieldRepository.findByGameAndFieldAndPlayer(game, field, opponent);

			if (battlefield.getImpact() != null && battlefield.getImpact()) {
				throw new Exception("La posición ya fue impactada");
			}

			battlefield.setImpact(true);
			battlefieldRepository.save(battlefield);

			game.setTurn(opponent);
			gameRepository.save(game);

			return battlefield.getShip();
		} else {
			throw new Exception("La jugada no cumple con la validación o el juego no existe");
		}

	}

	@ResponseBody
	@PostMapping("/battlefield/create")
	private List<Battlefield> sendBattefield(@RequestParam Long idGame, @RequestParam String username,
			@RequestBody List<ShipDto> ships) throws Exception {

		validateShips(ships);

		List<Battlefield> battlefields = new ArrayList<>();

		Game game = gameRepository.findOne(idGame);
		Player player = playerRepository.findByUsername(username);

		if (game != null && player != null) {

			for (ShipDto ship : ships) {
				Field field = fieldRepository.findByXAndY(ship.getX(), ship.getY());

				Battlefield battlefield = battlefieldRepository.findByGameAndFieldAndPlayer(game, field, player);

				if (battlefield == null) {
					battlefield = new Battlefield();
					battlefield.setGame(game);
					battlefield.setField(field);
					battlefield.setShip(ship.isShip());
					battlefield.setPlayer(player);
					battlefieldRepository.save(battlefield);
				}
			}

			battlefields = battlefieldRepository.findByGameAndPlayer(game, player);

		} else {
			throw new Exception("El juego no existe o el jugador no existe");
		}

		return battlefields;

	}

	@ResponseBody
	@GetMapping("/battlefield/fields")
	private List<ShipDto> fields() throws Exception {

		List<Field> fields = fieldRepository.findAll();
		List<ShipDto> ships = new ArrayList<>();

		for (Field field : fields) {
			ships.add(new ShipDto(field.getX(), field.getY(), false));
		}

		return ships;

	}

	private void validateShips(List<ShipDto> ships) throws Exception {

		if (ships == null || ships.isEmpty() || ships.size() > 100 || ships.size() < 100) {
			throw new Exception("Los barcos son obligatorios y no pueden haber mas de 100 campos");
		}

		int count = 0;
		for (ShipDto shipDto : ships) {
			if (shipDto.isShip()) {
				count++;
			}
		}

		if (count > 20 || count < 20) {
			throw new Exception("Los barcos son obligatorios");
		}

	}

	public static void print2D(boolean mat[][]) {
		// Loop through all rows
		for (int i = 0; i < mat.length; i++) {
			System.out.println();
			// Loop through all elements of current row
			for (int j = 0; j < mat[i].length; j++) {
				boolean result = mat[i][j];
				if (result) {
					System.out.print(" [1] ");
				} else {
					System.out.print(" [0] ");
				}

			}
		}
	}

}
