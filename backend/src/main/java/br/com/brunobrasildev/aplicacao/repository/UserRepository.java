package br.com.brunobrasildev.aplicacao.repository;

import br.com.brunobrasildev.aplicacao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String username);
}
