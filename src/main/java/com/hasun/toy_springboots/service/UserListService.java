package com.hasun.toy_springboots.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hasun.toy_springboots.dao.UserListDao;
import com.hasun.toy_springboots.utils.Paginations;

@Service
public class UserListService {
    @Autowired
    UserListDao userListDao;

    @Autowired
    AttachFileService attachFileService;

    public Object getOneWithAttachFiles(Object dataMap){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("attachFiles", attachFileService.getList(dataMap));

        result.putAll((Map<String, Object>)this.getOne(dataMap));
        return result;
    }

    public Object deleteAndGetList(Object dataMap){
        Object result = this.delete(dataMap);
        result = this.getList(dataMap);
        return result;
    }
    
    public Object insertWithFilesAndGetList(Object dataMap){
        Object result = attachFileService.insertMulti(dataMap);
        result = this.insertOne(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    public Object getListWithPagination(Object dataMap){
        Map<String, Object> result = new HashMap<String, Object>();
        int totalCount = (int) this.getTotal(dataMap);
        int currentPage = (int) ((Map<String, Object>) dataMap).get("currentPage");
        Paginations paginations = new Paginations(totalCount, currentPage);
        result.put("paginations", paginations);
        ((Map<String, Object>) dataMap).put("pageBegin", paginations.getPageBegin());
        result.put("resultList", this.getList(dataMap));
        return result;
    }

    public Object getTotal(Object dataMap){
        String sqlMapId = "UserList.selectTotal";

        Object result = userListDao.getOne(sqlMapId, dataMap);
        return result;
    }

    public Object getList(Object dataMap){
        String sqlMapId = "UserList.selectListByUID";
        Object result = userListDao.getList(sqlMapId, dataMap);
        return result;
    }

    public Object getOne(Object dataMap){
        String sqlMapId = "UserList.selectByUID";

        Object result = userListDao.getOne(sqlMapId, dataMap);
        return result;
    }

    public Object updateOne(Object dataMap){
        String sqlMapId = "UserList.updateByUID";

        Object result = userListDao.update(sqlMapId, dataMap);
        return result;    
    }

    public Object insertOne(Object dataMap){
        String sqlMapId = "UserList.insertWithUID";

        Object result = userListDao.insert(sqlMapId, dataMap);
        return result;    
    }

    public Object delete(Object dataMap){
        String sqlMapId = "UserList.deleteByUID";

        Object result = userListDao.delete(sqlMapId, dataMap);
        return result;    
    }

    public Object deleteMulti(Object dataMap){
        String sqlMapId = "UserList.deleteMultiByUIDs";

        Object result = userListDao.delete(sqlMapId, dataMap);
        return result;    
    }
}
