package com.connectionService.repo;

import com.connectionService.model.dao.Tp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TpRepo extends JpaRepository<Tp, Long> {

    @Query(
            "select t from Tp t join t.node n where n.nodeId=?1 and t.tpPrimaryRate=1120"
    )
    List<Tp> findPortsForPhysical(Long nodeId);


    @Query(
            "select t from Tp t join t.node n where n.nodeId=?1 and (t.tpPrimaryRate=1120 or t.tpPrimaryRate=1130)"
    )
    List<Tp> findPortsForInfra(Long nodeId);

    @Query(
            "select t from Tp t join t.node n where n.nodeId=?1"
    )
    List<Tp> findAllPortsForNode(Long nodeId);


    @Query("select t from Tp t where t.tpNativeName IN (?1, ?2)")
    List<Tp> findFromAndToPort(String fromPort, String toPort);


    @Query(
            value="select * from tp t where " +
                    "t.tp_id = (select n.src_tp_id from nc_component n where (n.connection_group_id=?1 and n.connection_rate=1130)) or " +
                    "t.tp_id = (select n.sink_tp_id from nc_component n where (n.connection_group_id=?1 and n.connection_rate=1130))",
            nativeQuery = true
    )
    List<Tp> findPortsUsingGroupId(String grpId);

    @Query(
            value = "select n.src_tp_id from nc_component n where (n.connection_group_id=?1 and n.connection_rate=1130)"
            ,nativeQuery = true
    )
    String getInfraSrcPort(String grpId);
}
