package com.qcells.jersey_server.modules;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class DataGenerate {

    private FileWriter file;

    private void File_Writer_test(JSONObject combined_data) {
        try {
            file = new FileWriter("/home/yh/workspace/jersey_server/src/main/resources/data.json");
            file.write(combined_data.toJSONString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }

    public JSONObject DataGenerate_toJson(){
        JSONObject combined_data=new JSONObject();
        combined_data.put("GateWay",Make_Gateway());
        combined_data.put("Inverters",Make_Inverters());
        File_Writer_test(combined_data);
        return combined_data;
    }

    private JSONObject Make_Gateway(){
        /**
         * Gateway :
         *  devices :
         *      startTs
         *      endTs
         *      mac
         *      id
         *      sn
         *      software:
         *          version
         *      ioboard:
         *          firmware
         */
		JSONArray devices_info=new JSONArray();
		devices_info.add(new JSONObject(new HashMap<String,Long>(){{
			put("startTs",new Long("1588749171462"));
		}}));
		devices_info.add(new JSONObject(new HashMap<String,Long>(){{
			put("endTs",new Long("1588749264681"));
		}}));
		devices_info.add(new JSONObject(new HashMap<String,String>(){{
			put("mac","66:33:ad:7f:3b:75");
		}}));
		devices_info.add(new JSONObject(new HashMap<String,String>(){{
			put("id","2239");
		}}));
		devices_info.add(new JSONObject(new HashMap<String,String>(){{
			put("sn","7.2.002239");
		}}));
		devices_info.add(new JSONObject(new JSONObject( new HashMap<String,JSONObject>(){{
			put("software",new JSONObject( new HashMap<String,String>(){{
				put("version","1.0.0");
			}}
			));
		}}
		)));
		devices_info.add(new JSONObject(new JSONObject( new HashMap<String,JSONObject>(){{
			put("ioboard",new JSONObject( new HashMap<String,String>(){{
				put("firmware","3");
			}}
			));
		}}
        )));
        JSONObject devices=new JSONObject(new HashMap<String,JSONArray>(){{
            put("devices",devices_info);
        }});
        return devices;
    }
    private JSONObject Make_Inverters(){
        JSONObject inverters_info=new JSONObject();
        inverters_info.put("startTS",new Long("1588749264547"));
        inverters_info.put("endTS",new Long("1588749264563"));
        inverters_info.put("error",null);
        inverters_info.put("devices",new JSONArray());


        return inverters_info;
         
    }

}