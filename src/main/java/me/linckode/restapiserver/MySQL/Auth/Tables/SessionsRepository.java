package me.linckode.restapiserver.MySQL.Auth.Tables;

import org.springframework.data.repository.CrudRepository;

public interface SessionsRepository extends CrudRepository<Sessions, String> {
}
