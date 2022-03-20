package com.connectionService.controller;


import com.connectionService.model.DlConn.DLInput;
import com.connectionService.response.Response;
import com.connectionService.service.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/connService/")
public class ConnectionController {


    @Autowired
    GetInfoService getInfoService;

    @GetMapping("/testAuth")
    public ResponseEntity<?> testAuth(@RequestBody DLInput dlInput){
        return ResponseEntity.ok().body(Response.message("Auth working correctly..."));
    }


    @ResponseBody
    @GetMapping("/getValidationInfo")
    public ResponseEntity<?>  getValidationInfo(
            @RequestParam("connGroupId") String connGroupId,
            @RequestParam("fromNE") String fromNE,
            @RequestParam("toNE") String toNE,
            @RequestParam("fromPort") String fromPort,
            @RequestParam("toPort") String toPort,
            @RequestParam("isService") String isService

    ){
        return  ResponseEntity.ok().body(getInfoService.
                getInfoForValidation(fromNE, toNE, fromPort, toPort, Boolean.parseBoolean(isService), connGroupId));
    }
}
