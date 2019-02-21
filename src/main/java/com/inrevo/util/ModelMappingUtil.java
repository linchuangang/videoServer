package com.inrevo.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

//model time area tag1 tag2 tag3 tag4 field1 field2 field3 field4 field5(double) field6(double)
public class ModelMappingUtil {

    public static Map<String, Map<String, String>> getMapping() {
        Map<String, Map<String, String>> map = new HashMap<>();

        Map<String, String> activationMap = new HashMap<>();
        activationMap.put("inUseNumber", "field1");
        activationMap.put("breakdownNunber", "field2");
        activationMap.put("inIdleNumber", "field3");
        map.put("Activation", activationMap);

        Map<String, String> PersonMap = new HashMap<>();
        PersonMap.put("department", "tag1");
        PersonMap.put("personType", "tag2");
        PersonMap.put("isOnJobNumber", "field1");
        PersonMap.put("outForWorkNumber", "field2");
        map.put("Person", PersonMap);

        Map<String, String> RepertoryMap = new HashMap<>();
        RepertoryMap.put("quality", "tag1");
        RepertoryMap.put("partName", "tag2");
        RepertoryMap.put("day", "field5");
        map.put("Repertory", RepertoryMap);

        Map<String, String> ShipmentMap = new HashMap<>();
        ShipmentMap.put("partName", "tag1");
        ShipmentMap.put("planValue", "field1");
        ShipmentMap.put("actualValue", "field2");
        map.put("Shipment", ShipmentMap);

        Map<String, String> YieldMap = new HashMap<>();
        YieldMap.put("partName", "tag1");
//        YieldMap.put("fixture", "tag2");
        YieldMap.put("yield", "field6");
//        YieldMap.put("actualValue", "field6");
        map.put("Yield", YieldMap);

        Map<String, String> PulicSecurityMap = new HashMap<>();
        PulicSecurityMap.put("hiddenDangerNumber", "field1");
        PulicSecurityMap.put("rectificationNumber", "field2");
//        PulicSecurityMap.put("rectificationRate", "field5");
        map.put("PulicSecurity", PulicSecurityMap);


        //------------------------------------------------------------
        Map<String, String> departmentMap = new HashMap<>();
        departmentMap.put("name", "tag1");
        departmentMap.put("productionValue", "field1");
        departmentMap.put("upph", "field2");
        departmentMap.put("materialExpense", "field3");
        departmentMap.put("depreciationCost", "field4");
        map.put("Department", departmentMap);

        Map<String, String> DeviceConditionMap = new HashMap<>();
        DeviceConditionMap.put("deviceType", "tag1");
        DeviceConditionMap.put("totalNumber", "field1");
        DeviceConditionMap.put("planNumber", "field2");
        DeviceConditionMap.put("actualNumber", "field3");
        map.put("DeviceCondition", DeviceConditionMap);

        Map<String, String> OEEMap = new HashMap<>();
        OEEMap.put("deviceType", "tag1");
        OEEMap.put("oee", "field5");
        map.put("OEE", OEEMap);


        Map<String, String> PlanMap = new HashMap<>();
        PlanMap.put("partName", "tag1");
        PlanMap.put("planValue", "field1");
        PlanMap.put("actualValue", "field2");
        map.put("Plan", PlanMap);

        Map<String, String> ProductionMap = new HashMap<>();
        ProductionMap.put("partName", "tag1");
        ProductionMap.put("planValue", "field1");
        ProductionMap.put("actualValue", "field2");
        map.put("Production", ProductionMap);

        Map<String, String> ProductionLineMap = new HashMap<>();
        ProductionLineMap.put("manufactureProcedure", "tag1");
        ProductionLineMap.put("totalNumber", "field1");
        ProductionLineMap.put("inUseNUmber", "field2");
        map.put("ProductionLine", ProductionLineMap);

        Map<String, String> RejectRatioMap = new HashMap<>();
        RejectRatioMap.put("partName", "tag1");
        RejectRatioMap.put("reason", "tag2");
        RejectRatioMap.put("number", "field1");
        map.put("RejectRatio", RejectRatioMap);


        Map<String, String> ScrapMap = new HashMap<>();
        ScrapMap.put("partName", "tag1");
        ScrapMap.put("craft", "tag2");
        ScrapMap.put("planValue", "field1");
        ScrapMap.put("actualValue", "field2");
        map.put("Scrap", ScrapMap);

        Map<String, String> ScrapBadMap = new HashMap<>();
        ScrapBadMap.put("partName", "tag1");
        ScrapBadMap.put("reason", "tag2");
        ScrapBadMap.put("number", "field1");
        map.put("ScrapBad", ScrapBadMap);


        Map<String, String> WipMap = new HashMap<>();
        WipMap.put("partName", "tag1");
        WipMap.put("manufactureProcedure", "tag2");
        WipMap.put("planValue", "field1");
        WipMap.put("actualValue", "field2");
        map.put("Wip", WipMap);


        return map;
    }

    public static void main(String[] args) {
        Map<String, Map<String, String>> mappingMap = getMapping();
        Map<String, Map<String, String>> result = new HashMap<>();
        for (String s : mappingMap.keySet()) {
            Map<String, String> newModelMap = new HashMap<>();
            Map<String, String> modelMap = mappingMap.get(s);
            for (String key : modelMap.keySet()) {
                newModelMap.put(modelMap.get(key), key);
            }
            result.put(s, newModelMap);
        }
        System.out.println(JSON.toJSONString(result));
    }
}
