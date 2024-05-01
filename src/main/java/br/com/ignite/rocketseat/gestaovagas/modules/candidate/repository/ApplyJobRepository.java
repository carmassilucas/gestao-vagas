package br.com.ignite.rocketseat.gestaovagas.modules.candidate.repository;

import br.com.ignite.rocketseat.gestaovagas.modules.candidate.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
