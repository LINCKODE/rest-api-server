package me.linckode.restapiserver.MySQL.UserData;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Players, String> {
}
