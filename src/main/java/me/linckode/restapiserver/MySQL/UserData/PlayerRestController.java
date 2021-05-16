package me.linckode.restapiserver.MySQL.UserData;

import me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions.BadRequestException;
import me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions.ConflictException;
import me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions.NotFoundException;
import me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions.UnauthorizedException;
import me.linckode.restapiserver.MySQL.Auth.Tables.SessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerRestController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SessionsRepository sessionsRepository;

    @GetMapping("")
    public Players getPlayer(@RequestParam(name = "accessToken") String accessToken, @RequestParam(name = "uid") String uid){

        if (accessToken.equals(""))
            throw new BadRequestException("Token cannot be null!");
        if (!sessionsRepository.existsById(accessToken))
            throw new UnauthorizedException("Bad access token.");


        if (playerRepository.existsById(uid)) {
            return playerRepository.findById(uid).get();

        }
        else
            throw new NotFoundException("Requested player not found!");
    }

    @GetMapping("/all")
    public Iterable<Players> getAllPlayers(@RequestParam(name = "accessToken") String accessToken){
        if (!sessionsRepository.existsById(accessToken))
            throw new UnauthorizedException("Bad access token!");
        return playerRepository.findAll();
    }

    @PostMapping("/new")
    public Players createPlayer(@RequestParam(name = "accessToken") String accessToken, @RequestParam(name = "uid") String uid, @RequestParam(name = "name") String name,
                                @RequestParam(name = "x") Double x, @RequestParam(name = "y") Double y, @RequestParam(name = "z") Double z,
                                @RequestParam(name = "worldName") String worldName, @RequestParam(name = "lastIp") String lastIp){

        if (playerRepository.existsById(uid))
            throw new ConflictException("Player already exists.");

        if (!sessionsRepository.existsById(accessToken))
            throw new UnauthorizedException("Bad access token!");

        Players players = new Players();

        players.setUId(uid);
        players.setName(name);
        players.setX(x);
        players.setY(y);
        players.setZ(z);
        players.setWorldName(worldName);
        players.setLastIp(lastIp);

        playerRepository.save(players);

        return players;
    }

    @PutMapping("/update")
    public Players updatePlayer(@RequestParam(name = "accessToken") String accessToken, @RequestParam(name = "uid") String uid,
                                @RequestParam(name = "x", required = false) Double x, @RequestParam(name = "y", required = false) Double y,
                                @RequestParam(name = "z", required = false) Double z, @RequestParam(name = "worldName", required = false) String worldName,
                                @RequestParam(value = "lastIp", required = false) String lastIp){

        if (!sessionsRepository.existsById(accessToken))
            throw new UnauthorizedException("Bad access token.");
        if (!playerRepository.existsById(uid))
            throw new NotFoundException("Player not found.");

        Players player = playerRepository.findById(uid).get();

        if (x != null)
            player.setX(x);
        if (y != null)
            player.setY(y);
        if (z != null)
            player.setZ(z);
        if (worldName != null)
            player.setWorldName(worldName);
        if (lastIp != null)
            player.setLastIp(lastIp);

        playerRepository.save(player);
        return player;
    }

    @DeleteMapping("/delete")
    public String deletePlayer (@RequestParam(name = "accessToken") String accessToken, @RequestParam(name = "uid") String uid){
        if (!sessionsRepository.existsById(accessToken))
            throw new UnauthorizedException("Bad access token!");
        if (!playerRepository.existsById(uid))
            throw new NotFoundException("Player not found!");

        playerRepository.deleteById(uid);
        return "OK";
    }

}
