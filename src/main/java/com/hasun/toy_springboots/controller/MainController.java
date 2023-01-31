package com.hasun.toy_springboots.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hasun.toy_springboots.service.UserListService;
import com.hasun.toy_springboots.utils.CommonUtils;



@Controller
public class MainController {
    @Autowired
    UserListService userListService;

    @Autowired
    CommonUtils commonUtils;

    @RequestMapping(value = {"/main", "/", ""}, method = RequestMethod.GET)
    public ModelAndView main(ModelAndView modelAndView) {
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = {"/userlist"}, method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {
        Object resultMap = userListService.getList(params);
        modelAndView.addObject("resultMap",resultMap);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = {"/insert"}, method = RequestMethod.POST)
    public ModelAndView insert(MultipartHttpServletRequest multipartHttpServletRequest,
    @RequestParam Map<String, Object> params, ModelAndView modelAndView) throws IOException{
        String registerSeq = multipartHttpServletRequest.getParameter("REGISTER_SEQ");
        
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file_first");
        String fileName = multipartFile.getOriginalFilename();
        
        String relativePath="src\\main\\resources\\static\\files\\";
        // file저장
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(relativePath+fileName));
        bufferedWriter.write(new String(multipartFile.getBytes()));
        bufferedWriter.flush();

        userListService.insertOne(params);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = { "/updateMulti" }, method = RequestMethod.POST)
    public ModelAndView updateMulti(MultipartHttpServletRequest multipartHttpServletRequest
            , @RequestParam Map<String, Object> params
            , ModelAndView modelAndView) throws IOException {

                Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();

                while(fileNames.hasNext()){
                    String value = (String)params.get(fileNames.next());
                    System.out.println(value);  // DB 저장이 되어 있다.
                    if(value != null){
                        // originalFilename와 있는지 여부 확인
                    }
                }
                modelAndView.setViewName("userlist");
                return modelAndView;
    }
    
    @RequestMapping(value = { "/insertMulti" }, method = RequestMethod.POST)
    public ModelAndView insertMulti(MultipartHttpServletRequest multipartHttpServletRequest
            , @RequestParam Map<String, Object> params
            , ModelAndView modelAndView) throws IOException {

        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
        String absolutePath = commonUtils.getRelativeToAbsolutePath("src/main/resources/static/files");

        Map attachfile = null;
        List attachfiles = new ArrayList();
        String physicalFileName = commonUtils.getUniqueSequence();
        String storePath = absolutePath + physicalFileName + File.separator ;
        File newPath = new File(storePath);
        newPath.mkdir();        // create directory
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(fileName);
            String originalFilename = multipartFile.getOriginalFilename();

            if(originalFilename != null && multipartFile.getSize() > 0){
                String storePathFileName = storePath + originalFilename;
                multipartFile.transferTo(new File(storePathFileName));
                
                // add SOURCE_UNIQUE_SEQ, ORGINALFILE_NAME, PHYSICALFILE_NAME in HashMap
                attachfile = new HashMap<>();
                attachfile.put("ATTACHFILE_SEQ", commonUtils.getUniqueSequence());
                attachfile.put("SOURCE_UNIQUE_SEQ", params.get("COMMON_CODE_ID") );
                attachfile.put("ORGINALFILE_NAME", originalFilename);
                attachfile.put("PHYSICALFILE_NAME", physicalFileName);
                attachfile.put("REGISTER_SEQ", params.get("REGISTER_SEQ"));
                attachfile.put("MODIFIER_SEQ", params.get("MODIFIER_SEQ"));
                
                attachfiles.add(attachfile);
            }
        }
        params.put("attachfiles", attachfiles);

        Object resultMap = userListService.insertWithFilesAndGetList(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = {"/form"}, method = RequestMethod.GET)
    public ModelAndView form(@RequestParam Map<String, Object> params, ModelAndView modelAndView){
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = {"/formMulti"}, method = RequestMethod.GET)
    public ModelAndView formMulti(@RequestParam Map<String, Object> params, ModelAndView modelAndView){
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = {"/delete/{uniqueId}"}, method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam Map<String, Object> params,@PathVariable String uniqueId, ModelAndView modelAndView){
        params.put("COMMON_CODE_ID", uniqueId);
        Object resultMap = userListService.deleteAndGetList(params);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.setViewName("userlist");
        return modelAndView;
    } 

    @RequestMapping(value = {"/deleteMulti"}, method = RequestMethod.POST)
    public ModelAndView deleteMulti(HttpServletRequest httpServletRequest, @RequestParam Map<String, Object> params, ModelAndView modelAndView){
        // modelAndView.addObject("resultMap", resultMap);
        String[] deleteMultis = httpServletRequest.getParameterMap().get("COMMON_CODE_ID");
        params.put("deleteMultis", deleteMultis);
        Object resultMap = userListService.deleteMulti(params);
        modelAndView.setViewName("userlist");
        return modelAndView;
    } 

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public ModelAndView update(@RequestParam Map<String, Object> params, ModelAndView modelAndView){
        Object resultMap = userListService.updateOne(params);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = { "/listPagination/{currentPage}" }, method = RequestMethod.GET)
    public ModelAndView listPagination(@RequestParam Map<String, Object> params
            , @PathVariable String currentPage, ModelAndView modelAndView) {
        params.put("currentPage", Integer.parseInt(currentPage));
        params.put("pageScale", 10);
        Object resultMap = userListService.getListWithPagination(params);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = {"/edit/{uniqueId}"}, method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam Map<String, Object> params,@PathVariable String uniqueId, ModelAndView modelAndView){
        params.put("COMMON_CODE_ID", uniqueId);
        Object resultMap = userListService.getOne(params);
        modelAndView.addObject("resultMap",resultMap);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }

    @RequestMapping(value = { "/editMulti/{uniqueId}" }, method = RequestMethod.GET)
    public ModelAndView editMulti(@RequestParam Map<String, Object> params, @PathVariable String uniqueId,
            ModelAndView modelAndView) {
        params.put("COMMON_CODE_ID", uniqueId);
        params.put("SOURCE_UNIQUE_SEQ", uniqueId);
        Object resultMap = userListService.getOneWithAttachFiles(params);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.setViewName("userlist");
        return modelAndView;
    }
}
