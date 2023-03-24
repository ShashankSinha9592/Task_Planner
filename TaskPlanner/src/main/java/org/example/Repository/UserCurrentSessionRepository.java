package org.example.Repository;

import org.example.Model.UserCurrentSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCurrentSessionRepository extends JpaRepository<UserCurrentSession, Integer> {

    public Optional<UserCurrentSession> findByToken(String token);

}
