package com.connectionService.service;


import com.connectionService.model.dao.Node;
import com.connectionService.model.dao.Tp;
import com.connectionService.model.dto.InfoForValidation;
import com.connectionService.repo.NetworkConnectionRepo;
import com.connectionService.repo.NodeRepo;
import com.connectionService.repo.TpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetInfoService {


    @Autowired
    private  NodeRepo nodeRepo;

    @Autowired
    private   TpRepo tpRepo;

    @Autowired
    private  NetworkConnectionRepo networkConnectionRepo;

    public  InfoForValidation getInfoForValidation( String fromNE, String toNE, String fromPort, String toPort, boolean isService, String grpId){
        System.out.println("Entering getInfoForValidation");
        InfoForValidation infoForValidation = null;

        List<Tp> tpList = tpRepo.findFromAndToPort(fromPort, toPort);
        List<Node> nodes = nodeRepo.findFromAndToNe(fromNE, toNE);
        List<String> connectionNames = networkConnectionRepo.getAllConnectionNames();



        Map<String, Tp> fromAndToPort = new HashMap<>();
        if(tpList.get(0).getTpNativeName().equals(fromPort)){
            fromAndToPort.put("fromPort",tpList.get(0));
        } else {
            fromAndToPort.put("toPort",tpList.get(0));
        }

        if(tpList.get(1).getTpNativeName().equals(toPort)){
            fromAndToPort.put("toPort", tpList.get(1));
        } else {
            fromAndToPort.put("fromPort", tpList.get(1));
        }


        Map<String, Node> fromAndToNode = new HashMap<>();
        if(nodes.get(0).getDisplayName().equals(fromNE)){
            fromAndToNode.put("fromNE", nodes.get(0));
        } else {
            fromAndToNode.put("toNE",nodes.get(0));
        }

        if(nodes.get(1).getDisplayName().equals(toNE)){
            fromAndToNode.put("toNE", nodes.get(1));
        } else {
            fromAndToNode.put("fromNE", nodes.get(1));
        }

        if(isService) {
             List<Tp> infraTp = tpRepo.findPortsUsingGroupId(grpId);
             String infraSrcPortId = tpRepo.getInfraSrcPort(grpId);
             Map<String, Tp> infraPorts = new HashMap<>();

             if(infraTp.get(0).getTpId().equals(Long.parseLong( infraSrcPortId))){
                 infraPorts.put("srcPort", infraTp.get(0));
             } else {
                 infraPorts.put("sinkPort", infraTp.get(0));
             }

             if(infraTp.get(1).getTpId().equals(Long.parseLong( infraSrcPortId))){
                 infraPorts.put("srcPort", infraTp.get(1));
             } else {
                 infraPorts.put("sinkPort", infraTp.get(1));
             }

            // Build infoForValidation Object
            infoForValidation = InfoForValidation.builder()
                    .connectionNames(connectionNames)
                    .fromAndToNode(fromAndToNode)
                    .fromAndToPort(fromAndToPort)
                    .infraPorts(infraPorts)
                    .build();

        } else {
            // Build infoForValidation Object
            infoForValidation = InfoForValidation.builder()
                    .connectionNames(connectionNames)
                    .fromAndToNode(fromAndToNode)
                    .fromAndToPort(fromAndToPort)
                    .build();
        }


        return infoForValidation;
    }
}
