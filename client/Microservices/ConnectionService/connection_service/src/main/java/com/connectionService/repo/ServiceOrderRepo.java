package com.connectionService.repo;

import com.connectionService.model.dao.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceOrderRepo extends JpaRepository<ServiceOrder, Integer> {


    @Query("select s from ServiceOrder s join s.networkConnection n where n.connectionId=?1")
    ServiceOrder joinNcSO(int connId);

}
