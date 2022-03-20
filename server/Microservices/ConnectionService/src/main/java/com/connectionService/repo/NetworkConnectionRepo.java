package com.connectionService.repo;

import com.connectionService.model.dao.NetworkConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkConnectionRepo extends JpaRepository<NetworkConnection, Integer> {


    @Query("select n.connectionName from NetworkConnection n")
    List<String> getAllConnectionNames();

    @Query(
            value="select  n.connection_name,s.order_number, p.pm_id, nc.connection_rate from " +
                    "network_connection n INNER JOIN service_order s ON (s.connection_id=?1 and n.connection_id=?1) " +
                    "INNER JOIN pm p ON p.connection_id=?1 " +
                    "RIGHT OUTER JOIN nc_component nc ON " +
                    "connection_group_id IN  (SELECT connection_group_id from nc_component where nc.connection_id=?1)",
            nativeQuery = true
    )
    List<Object[]> getConnInfoForGL(int connId);




}
