package com.connectionHandlerService.model.db_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node {

    private Long nodeId;
    private String displayName;
    private String ne_alias;
    private Date commLostTime;
    private Date commUpTime;
    private int commState;
    private int activityState;
    private String neCapability;
    private String neType;

}
