package com.connectionService.repo;

import com.connectionService.model.dao.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* Read this:
* https://stackoverflow.com/questions/2302802/how-to-fix-the-hibernate-object-references-an-unsaved-transient-instance-save
* */
@Repository
public interface NodeRepo extends JpaRepository<Node, Long> {
    @Query("select n from Node n where n.displayName IN (:fromNe, :toNe)")
    List<Node> findFromAndToNe(@Param("fromNe") String fromNe, @Param("toNe")String toNe);
    List<Node> findByDisplayNameStartingWith(String nodeStartsWith);
    List<Node> findByDisplayNameEndingWith(String nodeEndsWith);
    List<Node> findByDisplayNameContaining(String nodeContaining);

}
