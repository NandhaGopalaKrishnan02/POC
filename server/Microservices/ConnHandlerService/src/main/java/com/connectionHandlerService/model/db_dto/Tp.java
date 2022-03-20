package com.connectionHandlerService.model.db_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Tp {
    private Long tpId;
    private int ptpId;
    private String tpPrimaryRate;
    private String tpNativeName;
    private String tpState;
    private String tpType;
    private int tpGroupId;
}
