package com.connectionService.model.InfraConn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfraParams {
    private String connectionShape;
    private String routing;
    private List<String> terminationEndPoints;
    private boolean preplanConn;
    private String customerName;
    private String groupType;
}
