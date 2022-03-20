package com.connectionService.repo;

import com.connectionService.model.dao.Pm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PmRepo extends JpaRepository<Pm,Long> {
}
