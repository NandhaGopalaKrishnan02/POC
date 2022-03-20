package com.connectionService.model.dto;


import com.connectionService.model.dao.Node;
import com.connectionService.model.dao.Tp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoForValidation {
    private List<String> connectionNames;
    Map<String, Tp> fromAndToPort;
    Map<String, Node> fromAndToNode;
    Map<String, Tp> infraPorts;
}
