package com.connectionHandlerService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceParameters {
    private String _15MCollectionType;
    private String _15MMonitoring;
    private String _15MInterval;
    private String _24HCollectionType;
    private String _24HMonitoring;
    private String _24HInterval;
    private String BiDirCollectionType;
    private String BiDirMonitoring;
    private String BiDirInterval;
    private boolean allowAlarmReporting;
    private boolean assignAlarmProfile;
    private String alarmProfileName;

}
