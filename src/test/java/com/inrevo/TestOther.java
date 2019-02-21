package com.inrevo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.inrevo.model.CheckUp;
import com.inrevo.model.Info;
import com.inrevo.model.MonitorInfo;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestOther {
    public static void main(String[] args) {
        String infos = "[{\n" +
                "\t\"lineAreaDescription\": \"D09-1F衝壓南二通道-01\",\n" +
                "\t\"lineAreaBuild\": \"D09\",\n" +
                "\t\"lineAreaLine\": \"M3生產4線\",\n" +
                "\t\"lineAreaProcess\": \"stamping\",\n" +
                "\t\"lineAreaId\": 3,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 3,\n" +
                "\t\t\"monitorId\": 3,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.17\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.30.155\",\n" +
                "\t\t\"monitorDescription\": \"D09-1F衝壓南二通道-01\",\n" +
                "\t\t\"monitorCh\": 15,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch15/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D09-1F衝壓南二通道-02\",\n" +
                "\t\"lineAreaBuild\": \"D09\",\n" +
                "\t\"lineAreaLine\": \"M3生產4線\",\n" +
                "\t\"lineAreaProcess\": \"stamping\",\n" +
                "\t\"lineAreaId\": 4,\n" +
                "\t\"lineAreaName\": \"2\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 4,\n" +
                "\t\t\"monitorId\": 4,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.17\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.30.152\",\n" +
                "\t\t\"monitorDescription\": \"D09-1F衝壓南二通道-02\",\n" +
                "\t\t\"monitorCh\": 12,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch12/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D09-1F衝壓南二通道-03\",\n" +
                "\t\"lineAreaBuild\": \"D09\",\n" +
                "\t\"lineAreaLine\": \"M3生產4線\",\n" +
                "\t\"lineAreaProcess\": \"stamping\",\n" +
                "\t\"lineAreaId\": 5,\n" +
                "\t\"lineAreaName\": \"3\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 5,\n" +
                "\t\t\"monitorId\": 5,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.17\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.30.154\",\n" +
                "\t\t\"monitorDescription\": \"D09-1F衝壓南二通道-03\",\n" +
                "\t\t\"monitorCh\": 14,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch14/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D09-1F衝壓北二通道-02\",\n" +
                "\t\"lineAreaBuild\": \"D09\",\n" +
                "\t\"lineAreaLine\": \"M3生產6線\",\n" +
                "\t\"lineAreaProcess\": \"stamping\",\n" +
                "\t\"lineAreaId\": 6,\n" +
                "\t\"lineAreaName\": \"2\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 6,\n" +
                "\t\t\"monitorId\": 6,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.17\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.30.146\",\n" +
                "\t\t\"monitorDescription\": \"D09-1F衝壓北二通道-02\",\n" +
                "\t\t\"monitorCh\": 6,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch6/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D09-1F衝壓南一通道-04\",\n" +
                "\t\"lineAreaBuild\": \"D09\",\n" +
                "\t\"lineAreaLine\": \"Stand生產10線\",\n" +
                "\t\"lineAreaProcess\": \"stamping\",\n" +
                "\t\"lineAreaId\": 7,\n" +
                "\t\"lineAreaName\": \"4\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 7,\n" +
                "\t\t\"monitorId\": 7,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.17\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.30.143\",\n" +
                "\t\t\"monitorDescription\": \"D09-1F衝壓南一通道-04\",\n" +
                "\t\t\"monitorCh\": 3,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch3/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D05-2FCNC1-4自動化\",\n" +
                "\t\"lineAreaBuild\": \"D05\",\n" +
                "\t\"lineAreaLine\": \"自動化1線\",\n" +
                "\t\"lineAreaProcess\": \"CNC\",\n" +
                "\t\"lineAreaId\": 8,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 8,\n" +
                "\t\t\"monitorId\": 8,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.15\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.25.18\",\n" +
                "\t\t\"monitorDescription\": \"D05-2FCNC1-4自動化\",\n" +
                "\t\t\"monitorCh\": 8,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.15/H.264/ch8/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"2\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D05-2F清洗線-02\",\n" +
                "\t\"lineAreaBuild\": \"D05\",\n" +
                "\t\"lineAreaLine\": \"清洗線\",\n" +
                "\t\"lineAreaProcess\": \"CNC\",\n" +
                "\t\"lineAreaId\": 9,\n" +
                "\t\"lineAreaName\": \"2\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 9,\n" +
                "\t\t\"monitorId\": 9,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.15\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.25.19\",\n" +
                "\t\t\"monitorDescription\": \"D05-2F清洗線-02\",\n" +
                "\t\t\"monitorCh\": 9,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.15/H.264/ch9/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"2\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D06-1F清洗線-01\",\n" +
                "\t\"lineAreaBuild\": \"D06\",\n" +
                "\t\"lineAreaLine\": \"清洗線\",\n" +
                "\t\"lineAreaProcess\": \"CNC\",\n" +
                "\t\"lineAreaId\": 10,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 10,\n" +
                "\t\t\"monitorId\": 10,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.15\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.25.141\",\n" +
                "\t\t\"monitorDescription\": \"D06-1F清洗線-01\",\n" +
                "\t\t\"monitorCh\": 10,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.15/H.264/ch10/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D06-1F清洗線-03\",\n" +
                "\t\"lineAreaBuild\": \"D06\",\n" +
                "\t\"lineAreaLine\": \"清洗線\",\n" +
                "\t\"lineAreaProcess\": \"CNC\",\n" +
                "\t\"lineAreaId\": 11,\n" +
                "\t\"lineAreaName\": \"3\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 11,\n" +
                "\t\t\"monitorId\": 11,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.15\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.25.143\",\n" +
                "\t\t\"monitorDescription\": \"D06-1F清洗線-03\",\n" +
                "\t\t\"monitorCh\": 12,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.15/H.264/ch12/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D10-1F-1#（南）陽極線-東南-01\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"陽極線\",\n" +
                "\t\"lineAreaProcess\": \"finishing\",\n" +
                "\t\"lineAreaId\": 12,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 12,\n" +
                "\t\t\"monitorId\": 12,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.31.20\",\n" +
                "\t\t\"monitorDescription\": \"D10-1F-1#（南）陽極線-東南-01\",\n" +
                "\t\t\"monitorCh\": 19,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch19/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D10-1F-1#（南）陽極線-東北-01\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"陽極線\",\n" +
                "\t\"lineAreaProcess\": \"finishing\",\n" +
                "\t\"lineAreaId\": 13,\n" +
                "\t\"lineAreaName\": \"2\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 13,\n" +
                "\t\t\"monitorId\": 13,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.31.21\",\n" +
                "\t\t\"monitorDescription\": \"D10-1F-1#（南）陽極線-東北-01\",\n" +
                "\t\t\"monitorCh\": 20,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch20/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D10-1F-1#（南）陽極線-西南-01\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"陽極線\",\n" +
                "\t\"lineAreaProcess\": \"finishing\",\n" +
                "\t\"lineAreaId\": 14,\n" +
                "\t\"lineAreaName\": \"3\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 14,\n" +
                "\t\t\"monitorId\": 14,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.31.16\",\n" +
                "\t\t\"monitorDescription\": \"D10-1F-1#（南）陽極線-西南-01\",\n" +
                "\t\t\"monitorCh\": 15,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch15/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"D10-1F-1#（南）陽極線-西北-01\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"陽極線\",\n" +
                "\t\"lineAreaProcess\": \"finishing\",\n" +
                "\t\"lineAreaId\": 15,\n" +
                "\t\"lineAreaName\": \"4\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 15,\n" +
                "\t\t\"monitorId\": 15,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.31.17\",\n" +
                "\t\t\"monitorDescription\": \"D10-1F-1#（南）陽極線-西北-01\",\n" +
                "\t\t\"monitorCh\": 16,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch16/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-K2(D11-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D11\",\n" +
                "\t\"lineAreaLine\": \"K2\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 16,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 16,\n" +
                "\t\t\"monitorId\": 16,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.101\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.151\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-K2(D11-3F)\",\n" +
                "\t\t\"monitorCh\": 2,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.101/H.264/ch2/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-K5(D11-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D11\",\n" +
                "\t\"lineAreaLine\": \"K5\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 17,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 17,\n" +
                "\t\t\"monitorId\": 17,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.100\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.140\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-K5(D11-3F)\",\n" +
                "\t\t\"monitorCh\": 13,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.100/H.264/ch13/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-K1-01(D11-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D11\",\n" +
                "\t\"lineAreaLine\": \"K1\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 18,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 18,\n" +
                "\t\t\"monitorId\": 18,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.101\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.159\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-K1-01(D11-3F)\",\n" +
                "\t\t\"monitorCh\": 10,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.101/H.264/ch10/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-K1-02(D11-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D11\",\n" +
                "\t\"lineAreaLine\": \"K1\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 19,\n" +
                "\t\"lineAreaName\": \"2\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 19,\n" +
                "\t\t\"monitorId\": 19,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.101\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.150\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-K1-02(D11-3F)\",\n" +
                "\t\t\"monitorCh\": 1,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.101/H.264/ch1/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-K3(D11-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D11\",\n" +
                "\t\"lineAreaLine\": \"K3\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 20,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 20,\n" +
                "\t\t\"monitorId\": 20,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.101\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.165\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-K3(D11-3F)\",\n" +
                "\t\t\"monitorCh\": 16,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.101/H.264/ch16/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-N1(D10-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"N1\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 21,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 21,\n" +
                "\t\t\"monitorId\": 21,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.100\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.108\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-N1(D10-3F)\",\n" +
                "\t\t\"monitorCh\": 11,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.100/H.264/ch11/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-N5(D10-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"N5\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 22,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 22,\n" +
                "\t\t\"monitorId\": 22,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.100\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.80\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-N5(D10-3F)\",\n" +
                "\t\t\"monitorCh\": 15,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.100/H.264/ch15/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-N3(D10-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"N3\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 23,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 23,\n" +
                "\t\t\"monitorId\": 23,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.100\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.84\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-N3(D10-3F)\",\n" +
                "\t\t\"monitorCh\": 19,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.100/H.264/ch19/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"東側車間產線-N2(D10-3F)\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"N2\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 24,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 24,\n" +
                "\t\t\"monitorId\": 24,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.100\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.93\",\n" +
                "\t\t\"monitorDescription\": \"東側車間產線-N2(D10-3F)\",\n" +
                "\t\t\"monitorCh\": 18,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.100/H.264/ch18/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"3\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"車間產線-L9(D10-2F）\",\n" +
                "\t\"lineAreaBuild\": \"D10\",\n" +
                "\t\"lineAreaLine\": \"L9\",\n" +
                "\t\"lineAreaProcess\": \"Assy\",\n" +
                "\t\"lineAreaId\": 25,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 25,\n" +
                "\t\t\"monitorId\": 25,\n" +
                "\t\t\"monitorNvrIp\": \"10.248.177.100\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.26.103\",\n" +
                "\t\t\"monitorDescription\": \"車間產線-L9(D10-2F）\",\n" +
                "\t\t\"monitorCh\": 6,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.248.177.100/H.264/ch6/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"2\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"C08-1F噴砂-東北01\",\n" +
                "\t\"lineAreaBuild\": \"C08\",\n" +
                "\t\"lineAreaLine\": \"噴砂線\",\n" +
                "\t\"lineAreaProcess\": \"ST\",\n" +
                "\t\"lineAreaId\": 26,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 26,\n" +
                "\t\t\"monitorId\": 26,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.23.146\",\n" +
                "\t\t\"monitorDescription\": \"C08-1F噴砂-東北01\",\n" +
                "\t\t\"monitorCh\": 2,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch2/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"C08-1F噴砂-西北01\",\n" +
                "\t\"lineAreaBuild\": \"C08\",\n" +
                "\t\"lineAreaLine\": \"噴砂線\",\n" +
                "\t\"lineAreaProcess\": \"ST\",\n" +
                "\t\"lineAreaId\": 27,\n" +
                "\t\"lineAreaName\": \"2\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 27,\n" +
                "\t\t\"monitorId\": 27,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.23.141\",\n" +
                "\t\t\"monitorDescription\": \"C08-1F噴砂-西北01\",\n" +
                "\t\t\"monitorCh\": 1,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch1/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"C08-1F東北清洗線01\",\n" +
                "\t\"lineAreaBuild\": \"C08\",\n" +
                "\t\"lineAreaLine\": \"清洗線\",\n" +
                "\t\"lineAreaProcess\": \"ST\",\n" +
                "\t\"lineAreaId\": 28,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 28,\n" +
                "\t\t\"monitorId\": 28,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.23.142\",\n" +
                "\t\t\"monitorDescription\": \"C08-1F東北清洗線01\",\n" +
                "\t\t\"monitorCh\": 6,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch6/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"C08-1F東北清洗線02\",\n" +
                "\t\"lineAreaBuild\": \"C08\",\n" +
                "\t\"lineAreaLine\": \"清洗線\",\n" +
                "\t\"lineAreaProcess\": \"ST\",\n" +
                "\t\"lineAreaId\": 29,\n" +
                "\t\"lineAreaName\": \"2\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 29,\n" +
                "\t\t\"monitorId\": 29,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.23.145\",\n" +
                "\t\t\"monitorDescription\": \"C08-1F東北清洗線02\",\n" +
                "\t\t\"monitorCh\": 5,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch5/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"C08-1F-2#（北）-陽極線-東南01\",\n" +
                "\t\"lineAreaBuild\": \"C08\",\n" +
                "\t\"lineAreaLine\": \"陽極線\",\n" +
                "\t\"lineAreaProcess\": \"ST\",\n" +
                "\t\"lineAreaId\": 30,\n" +
                "\t\"lineAreaName\": \"1\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 30,\n" +
                "\t\t\"monitorId\": 30,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.23.151\",\n" +
                "\t\t\"monitorDescription\": \"C08-1F-2#（北）-陽極線-東南01\",\n" +
                "\t\t\"monitorCh\": 9,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch9/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"C08-1F-2#（北）-陽極線-東北01\",\n" +
                "\t\"lineAreaBuild\": \"C08\",\n" +
                "\t\"lineAreaLine\": \"陽極線\",\n" +
                "\t\"lineAreaProcess\": \"ST\",\n" +
                "\t\"lineAreaId\": 31,\n" +
                "\t\"lineAreaName\": \"2\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 31,\n" +
                "\t\t\"monitorId\": 31,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.23.154\",\n" +
                "\t\t\"monitorDescription\": \"C08-1F-2#（北）-陽極線-東北01\",\n" +
                "\t\t\"monitorCh\": 10,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch10/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"C08-1F-2#（北）-陽極線-西南01\",\n" +
                "\t\"lineAreaBuild\": \"C08\",\n" +
                "\t\"lineAreaLine\": \"陽極線\",\n" +
                "\t\"lineAreaProcess\": \"ST\",\n" +
                "\t\"lineAreaId\": 32,\n" +
                "\t\"lineAreaName\": \"3\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 32,\n" +
                "\t\t\"monitorId\": 32,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.18.77\",\n" +
                "\t\t\"monitorDescription\": \"C08-1F-2#（北）-陽極線-西南01\",\n" +
                "\t\t\"monitorCh\": 14,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch14/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}, {\n" +
                "\t\"lineAreaDescription\": \"C08-1F-2#（北）-陽極線-西北01\",\n" +
                "\t\"lineAreaBuild\": \"C08\",\n" +
                "\t\"lineAreaLine\": \"陽極線\",\n" +
                "\t\"lineAreaProcess\": \"ST\",\n" +
                "\t\"lineAreaId\": 33,\n" +
                "\t\"lineAreaName\": \"4\",\n" +
                "\t\"listInfo\": [{\n" +
                "\t\t\"monitorNvrUser\": \"admin\",\n" +
                "\t\t\"monitorAreaId\": 33,\n" +
                "\t\t\"monitorId\": 33,\n" +
                "\t\t\"monitorNvrIp\": \"10.249.25.16\",\n" +
                "\t\t\"monitorCameraIp\": \"10.249.18.76\",\n" +
                "\t\t\"monitorDescription\": \"C08-1F-2#（北）-陽極線-西北01\",\n" +
                "\t\t\"monitorCh\": 13,\n" +
                "\t\t\"monitorNvrPassword\": \"one752*cdzx\",\n" +
                "\t\t\"monitorPlay\": \"rtsp://admin:one752*cdzx@10.249.25.16/H.264/ch13/main/av_stream\"\n" +
                "\t}],\n" +
                "\t\"lineAreaF\": \"1\"\n" +
                "}]";
        List<MonitorInfo> monitorInfos = JSONArray.parseArray(infos, MonitorInfo.class);
        for (MonitorInfo monitorInfo : monitorInfos) {
            System.out.println(JSON.toJSONString(monitorInfo));
            System.out.println(JSON.toJSONString(monitorInfo.getListInfo().get(0).getMonitorPlay()));
        }
        Map<String, List<MonitorInfo>> monitorInfoMap = monitorInfos.stream().collect(Collectors.groupingBy(MonitorInfo::getLineAreaProcess));
        System.out.println(JSON.toJSONString(monitorInfoMap));
        System.out.println(JSON.toJSONString(monitorInfoMap.get("finishing")));
        List<MonitorInfo> monitorInfoList = monitorInfoMap.get("finishing");
        List<Info> infoList = new ArrayList<>();
        monitorInfoList.forEach(monitorInfo -> infoList.addAll(monitorInfo.getListInfo()));
        System.out.println(JSON.toJSONString(infoList));
        infoList.forEach(info -> {
            System.out.println(info.getMonitorPlay());
        });
        System.out.println("------------------------");
        infoList.forEach(info -> {
            System.out.println(info.getMonitorPlay());
            if (true) {
                return;
            }
        });
        System.out.println("****************");
        for (Info s : infoList) {
            System.out.println(s.getMonitorPlay());
            break;
        }
    }

    @Test
    public void testList() {

    }

    @Test
    public void testMap() {
        String s = "2019-02-18 15:55:14:frame=  317 fps= 27 q=-1.0 size=     365kB time=00:00:13.16 bitrate= 226.8kbits/s speed=1.12x ";
        String[] infos = s.split("bitrate=");
        String info = infos[1].split("kbits/s")[0];
        Double number = Double.parseDouble(info.trim());
        System.out.println(number);

    }
}
