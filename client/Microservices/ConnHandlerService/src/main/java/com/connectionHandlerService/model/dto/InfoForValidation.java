package com.connectionHandlerService.model.dto;


import com.connectionHandlerService.model.db_dto.Node;
import com.connectionHandlerService.model.db_dto.Tp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

// Don't create BEAN for POJO classes
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoForValidation {
    private List<String> connectionNames;
    private Map<String, Node> fromAndToNode;
    private Map<String, Tp> fromAndToPort;
    private Map<String, Tp> infraPorts;
}
