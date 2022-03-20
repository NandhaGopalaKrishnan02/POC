package com.connectionService.repo;

import com.connectionService.model.dao.NcComponent;
import com.connectionService.model.dao.Node;
import com.connectionService.model.dao.Tp;
import com.connectionService.model.dto.InfraPorts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TpRepoTest {

    @Autowired
    private TpRepo tpRepo;

    @Test
    public void saveTpAndNode(){
        Node node1 = Node.builder()
                .activityState(1)
                .commLostTime(new Date())
                .commState(1)
                .commUpTime(new Date())
                .displayName("LUNITE-A")
                .neCapability("LO")
                .neType("SDH")
                .ne_alias("LUNITE-A")
                .build();

        Node node2 = Node.builder()
                .activityState(1)
                .commLostTime(new Date())
                .commState(1)
                .commUpTime(new Date())
                .displayName("LUNITE-B")
                .neCapability("LO")
                .neType("SDH")
                .ne_alias("LUNITE-B")
                .build();


        Tp tp11 = Tp.builder()
                .ptpId(12331)
                .tpPrimaryRate("1120")
                .tpNativeName("1-1-#-#-1-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1221)
                .node(node1)
                .build();

        Tp tp12 = Tp.builder()
                .ptpId(12332)
                .tpPrimaryRate("1120")
                .tpNativeName("1-1-#-#-2-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1221)
                .node(node1)
                .build();

        Tp tp13 = Tp.builder()
                .ptpId(123311)
                .tpPrimaryRate("1130")
                .tpNativeName("1-1-#-#-1-1-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1222)
                .node(node1)
                .build();


        Tp tp14 = Tp.builder()
                .ptpId(123312)
                .tpPrimaryRate("1130")
                .tpNativeName("1-1-#-#-1-1-2")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1222)
                .node(node1)
                .build();

        Tp tp15 = Tp.builder()
                .ptpId(1233111)
                .tpPrimaryRate("1165")
                .tpNativeName("1-1-#-#-1-1-1-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1223)
                .node(node1)
                .build();

        Tp tp16 = Tp.builder()
                .ptpId(1233112)
                .tpPrimaryRate("1165")
                .tpNativeName("1-1-#-#-1-1-1-2")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1223)
                .node(node1)
                .build();


        Tp tp21 = Tp.builder()
                .ptpId(12333)
                .tpPrimaryRate("1120")
                .tpNativeName("1-1-#-#-4-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1231)
                .node(node2)
                .build();

        Tp tp22 = Tp.builder()
                .ptpId(12334)
                .tpPrimaryRate("1120")
                .tpNativeName("1-1-#-#-4-2")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1231)
                .node(node2)
                .build();

        Tp tp23 = Tp.builder()
                .ptpId(123331)
                .tpPrimaryRate("1130")
                .tpNativeName("1-1-#-#-4-1-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1232)
                .node(node2)
                .build();

        Tp tp24 = Tp.builder()
                .ptpId(123332)
                .tpPrimaryRate("1130")
                .tpNativeName("1-1-#-#-4-1-2")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1232)
                .node(node2)
                .build();

        Tp tp25 = Tp.builder()
                .ptpId(1233311)
                .tpPrimaryRate("1165")
                .tpNativeName("1-1-#-#-4-1-1-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1233)
                .node(node2)
                .build();


        Tp tp26 = Tp.builder()
                .ptpId(1233312)
                .tpPrimaryRate("1165")
                .tpNativeName("1-1-#-#-4-1-1-2")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1233)
                .node(node2)
                .build();

        List<Tp> tps = new ArrayList<>(
                Arrays.asList(
                        tp11,tp21,tp12,tp13,tp14,tp15,tp16,
                        tp22,tp23,tp24,tp25,tp26
                )
        );

//        tpRepo.save(tp11);
//        tpRepo.save(tp21);

//        tpRepo.save(tp12);

//        tpRepo.save(tp13);
//        tpRepo.save(tp14);
//        tpRepo.save(tp15);
//        tpRepo.save(tp16);
//
//        tpRepo.save(tp22);
//        tpRepo.save(tp23);
//        tpRepo.save(tp24);
//        tpRepo.save(tp25);
//        tpRepo.save(tp26);
//
        tpRepo.saveAll(tps);
    }

    @Transactional
    @Test
    public void  saveTpAndNodeTestWrongCases(){
        Node node1 = Node.builder()
                .activityState(1)
                .commLostTime(new Date())
                .commState(1)
                .commUpTime(new Date())
                .displayName("LUNITE-C")
                .neCapability("LO")
                .neType("SDH")
                .ne_alias("LUNITE-A")
                .build();


        Tp tp11 = Tp.builder()
                .ptpId(12431)
                .tpPrimaryRate("1120")
                .tpNativeName("1-1-#-#-3-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1231)
                .node(node1)
                .build();

        Tp tp12 = Tp.builder()
                .ptpId(12432)
                .tpPrimaryRate("1120")
                .tpNativeName("1-1-#-#-5-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1231)
                .node(node1)
                .build();

        Tp tp13 = Tp.builder()
                .ptpId(124311)
                .tpPrimaryRate("1130")
                .tpNativeName("1-1-#-#-3-1-1")
                .tpState("1")
                .tpType("SDH")
                .tpGroupId(1232)
                .node(node1)
                .build();


        tpRepo.save(tp11);// this is one transaction
        tpRepo.save(tp12); // this is second transaction
        tpRepo.save(tp13); // this is third transaction

        /*
         * 1.If we save object one by one then it will cause detached entity exception if we use cascade child to parent.
         * 2.In other words, once 1st transaction is saved parent will be created. But hibernate will try to
         *   create already exist parent again in next transaction so it will cause detached entity exception
         * check this blog: https://vladmihalcea.com/a-beginners-guide-to-jpa-hibernate-entity-state-transitions/
         * Solution
         * -------
         * save it single transaction like this tpRepo.saveAll(tpsList)
         * Note: Try to cascade parent to child instead of child to parent
         * */
    }

    @Test
    public void getAllPortsForNe(){
        List<Tp> tps = tpRepo.findAllPortsForNode(1001L);
        for(Tp l : tps){
            System.out.println("l = " + l);
        }
        System.out.println("tps = " + tps);
        
    }

    @Test
    public void getAllPortsForPhysical(){
        List<Tp> tps = tpRepo.findPortsForPhysical(1001L);
        for(Tp l : tps){
            System.out.println("l = " + l);
        }
        System.out.println("tps = " + tps);

    }

    @Test
    public void getAllPortsForInfra(){
        List<Tp> tps = tpRepo.findPortsForInfra(1001L);
        for(Tp l : tps){
            System.out.println("l = " + l);
        }
        System.out.println("tps = " + tps);

    }


    @Test
    public void fromPortToPort(){
        List<Tp> tps = tpRepo.findFromAndToPort("1-1-#-#-1-1","1-1-#-#-4-1");
        System.out.println("tps = " + tps);
    }

    @Test
    public void getPortsByGrpId(){
        List<Tp> tps = tpRepo.findPortsUsingGroupId("1243");
        System.out.println("tps = " + tps);
    }


    @Test
    public void getInfraSrcPorts(){
        String tp = tpRepo.getInfraSrcPort("1243");
        System.out.println("tp = " + tp);
    }
    
}