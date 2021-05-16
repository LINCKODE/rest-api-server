package me.linckode.restapiserver.MySQL.Auth;

import me.linckode.restapiserver.MySQL.Auth.Tables.Credentials;
import me.linckode.restapiserver.MySQL.Auth.Tables.CredentialsRepository;
import me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions.NotFoundException;
import me.linckode.restapiserver.MySQL.Auth.Tables.Exceptions.UnauthorizedException;
import me.linckode.restapiserver.MySQL.Auth.Tables.Sessions;
import me.linckode.restapiserver.MySQL.Auth.Tables.SessionsRepository;
import me.linckode.restapiserver.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthRestController {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private SessionsRepository sessionsRepository;

    @PostMapping("/new")
    public Credentials createUser(@RequestParam(name = "masterToken", defaultValue = "") String masterToken) {

        if (masterToken.equals("LSJCymdGRqZQN3Yhdh36Dqc7HxSjNDwSXTwaEMnpqD4vQjd5Wjp2rAX2YQyHbAKa")) {
            Credentials credentials = new Credentials();
            credentials.setUserId(Util.randomString(16));
            credentials.setPrivateToken(Util.randomString(32));
            credentials.setAccessToken("");
            credentialsRepository.save(credentials);
            return credentials;

        } else throw new UnauthorizedException("Bad Master Token!");

    }

    @GetMapping("/getToken")
    public Sessions authUser(@RequestParam(name = "userId", defaultValue = "") String userId, @RequestParam(name = "privateToken", defaultValue = "") String privateToken) {
        Credentials credentials;
        if (credentialsRepository.existsById(userId)) {

            credentials = credentialsRepository.findById(userId).get();
            if (credentials.getPrivateToken().equals(privateToken))
            {
                if (sessionsRepository.existsById(credentials.getAccessToken()))
                    sessionsRepository.deleteById(credentials.getAccessToken());
                Sessions session = new Sessions();
                session.setAccessToken(Util.randomString(64));
                session.setUserId(userId);
                sessionsRepository.save(session);
                credentials.setAccessToken(session.getAccessToken());
                credentialsRepository.save(credentials);
                return session;
            }
            else
                throw new UnauthorizedException("Bad credentials!");
        }
        else throw new NotFoundException("User not found!");

    }


}
