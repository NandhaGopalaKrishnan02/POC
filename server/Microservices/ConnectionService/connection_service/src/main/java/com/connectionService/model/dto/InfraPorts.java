package com.connectionService.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfraPorts {
    private String srcTpId;
    private String sinkTpId;
}
