package com.connectionHandlerService.service.connection;

import com.connectionHandlerService.model.ConnectionParameters;
import com.connectionHandlerService.model.dto.InfoForValidation;
import com.connectionHandlerService.request.ReqToConnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Validate {

    @Autowired
    ReqToConnService reqToConnService;
    private InfoForValidation infoForValidation;
    private ConnectionParameters connectionParams;

    public  String doBasicValidation(ConnectionParameters connectionParams){
        System.out.println("basic validation...");
        String failReason = "";
        List<String> connectionNames;
        boolean isAlreadyExist;
        this.connectionParams = connectionParams;

        // If its required to add new basic validation then do it here.
        if(connectionParams.getFromNE().equals("") ||
                connectionParams.getToNE().equals("") ||
                connectionParams.getFromPort().equals("") ||
                connectionParams.getToPort().equals("")){
            failReason = "Please fill mandatory fields";
        } else if (connectionParams.getFromNE().equals(connectionParams.getToNE())){
            failReason = "From and To NE shouldn't be same";
        }

        this.intializeAndCheckNewConn();
        connectionNames = infoForValidation.getConnectionNames();
        isAlreadyExist = connectionNames.contains(connectionParams.getConnectionName());

        if (isAlreadyExist) {
            failReason = "Connection name '" + connectionParams.getConnectionName() + "' already exists, please type new one";
        }
        return failReason;
    }

    public  String doBasicConnValidation(String connType){
        /*
         * 1. Throw error if selected ports are either implemented, alternate or connected.
         * 2. check both nes have same technology
         * Port states
            1 -> available
            2 -> implemented
            3 -> Alternate
            4 -> connected
         * */

         String failReason = "";
         Map<String, String> portStates = new HashMap<String, String >(){{
             put("2","Implemented");
             put("3","Alternate");
             put("4","Connected");
         }};

         String fromPortState = infoForValidation.getFromAndToPort().get("fromPort").getTpState();
         String toPortState =  infoForValidation.getFromAndToPort().get("toPort").getTpState();
         String fromNeTech = infoForValidation.getFromAndToNode().get("fromNE").getNeType();
         String toNeTech = infoForValidation.getFromAndToNode().get("toNE").getNeType();
         String fromPortTech = infoForValidation.getFromAndToPort().get("fromPort").getTpType();
         String toPortTech = infoForValidation.getFromAndToPort().get("toPort").getTpType();

         //check port states
         if(portStates.containsKey(fromPortState) || portStates.containsKey(toPortState)){
             failReason = "Selected ports are already implemented";
         } else if (!fromNeTech.equals(toNeTech) || !fromPortTech.equals(toPortTech)){
             failReason = "From and To NE technologies aren't same";
         }

         // validate infra or service
        if(failReason.equals("")){
            if(connType.equals("INFRA"))
                failReason = doInfraConnValidation();
            else if(connType.equals("SERVICE"))
                failReason = doServiceConnValidation();
            }
        return failReason;
    }

    public  String doInfraConnValidation(){
        /*
         * validation
         * ----------
         * 1.compare end ports are same or not. if not show error
         * 2.check both the nes have lower order capability
         *
         * */
        //

        System.out.println("infra conn validation...");
        String failReason = "";
        String fromPort = infoForValidation.getFromAndToPort().get("fromPort").getTpNativeName();
        String toPort = infoForValidation.getFromAndToPort().get("toPort").getTpNativeName();
        String fromNELO = infoForValidation.getFromAndToNode().get("fromNE").getNeCapability();
        String toNELO = infoForValidation.getFromAndToNode().get("toNE").getNeCapability();

        if( !(  fromPort.substring(fromPort.lastIndexOf("-")+1 ).equals(toPort.substring(toPort.lastIndexOf("-")+1 ) )  )  ) {
            failReason = "From and to ports end value should be equal";
        } else if ( ! (fromNELO.equals("LO") &&  toNELO.equals("LO"))){
            failReason = "Both the NE should have Lower order capability";
        }
        return failReason;
    }

    public String doServiceConnValidation(){

        /*
         * validation
         * ----------
         * 1.Port Range should be outside the dl.
         *
         * */
        //
        System.out.println("service conn validation...");
        String failReason="";

        String fromPort = infoForValidation.getFromAndToPort().get("fromPort").getTpNativeName();
        String toPort = infoForValidation.getFromAndToPort().get("toPort").getTpNativeName();
        String infraSrcPort = infoForValidation.getInfraPorts().get("srcPort").getTpNativeName();
        String infraSinkPort  = infoForValidation.getInfraPorts().get("sinkPort").getTpNativeName();

        if((infraSrcPort.charAt(8)) == (fromPort.charAt(8)) || (infraSinkPort.charAt(8)) == (toPort.charAt(8))) {
            failReason = "Please select service ports outside the DL ports range";
        }
        return failReason;
    }


    public void intializeAndCheckNewConn() {
        if(infoForValidation == null ||
                    !((infoForValidation.getFromAndToNode().get("fromNE").getDisplayName()).equals(connectionParams.getFromNE()) &&
                            (infoForValidation.getFromAndToNode().get("toNE").getDisplayName()).equals(connectionParams.getToNE()) &&
                            (infoForValidation.getFromAndToPort().get("fromPort").getTpNativeName()).equals(connectionParams.getFromPort()) &&
                            (infoForValidation.getFromAndToPort().get("toPort").getTpNativeName()).equals(connectionParams.getToPort())

                    )
            ) {

            infoForValidation = reqToConnService.getRequiredInfoForValidation(connectionParams);
        }
    }

}


