package com.connectionService.repo;

import com.connectionService.model.dao.NcComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NcComponentRepo extends JpaRepository<NcComponent, Long> {
}
