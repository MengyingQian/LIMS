package com.ddxq.employee.kuaidi.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface KuaiDiService {
	public boolean SendMessage(JSONObject obj);
	public Map getinfo(String id);
}
