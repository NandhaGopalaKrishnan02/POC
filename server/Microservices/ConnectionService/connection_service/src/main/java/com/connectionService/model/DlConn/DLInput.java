package com.connectionService.model.DlConn;

import com.connectionService.model.AssuranceParameters;
import com.connectionService.model.ConnectionParameters;
import com.connectionService.model.OrderParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DLInput {
    private ConnectionParameters connectionParameters;
    private AssuranceParameters assuranceParameters;
    private OrderParameters orderParameters;
}
