package com.inspur.auto.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inspur.auto.dao.*;
import com.inspur.auto.entity.po.*;
import com.inspur.auto.service.AutoService;
import com.inspur.auto.service.ExpectedResultService;
import com.thoughtworks.xstream.XStream;
import frm.base.MessageBody;
import frm.base.RequestConstruct;
import frm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutoServiceImpl implements AutoService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AutoExpectedResultMapper autoExpectedResultMapper;
    @Autowired
    private AutoRequestMessageMapper autoRequestMessageMapper;
    @Autowired
    private AutoPlatFormMapper autoPlatFormMapper;
    @Autowired
    private AutoResponseMessageMapper autoResponseMessageMapper;
    @Autowired
    private AutoActualResultMapper autoActualResultMapper;
    @Autowired
    private ExpectedResultService expectedResultService;

    /**
     * 根据上传的json和xml写入预期结果和请求报文
     *
     * @param multipartfile
     * @param platFormId
     * @throws IOException
     */
    @Override
    public void insertExpectedResult(MultipartFile multipartfile, String platFormId) throws IOException {
        //获得文件名
        Map<String, Object> requestConstructMap = new HashMap<>();

        String fileName = multipartfile.getOriginalFilename();
        //读取案例编号和请求报文
        if (fileName.endsWith("xml")) {
            requestConstructMap = SoupUIUtil.readSoupUIXml(multipartfile);
        }
        if (fileName.endsWith("json")) {
            try {
                requestConstructMap = JsonReadUtil.readJsonFile(multipartfile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<AutoExpectedResult> autoExpectedResults = expectedResultService.selectExpectedResult(platFormId);
        List<AutoExpectedResult> autoExpectedResultsForInsert = new ArrayList<>();
        List<AutoRequestMessage> autoRequestMessages = new ArrayList<>();
        if (!autoExpectedResults.isEmpty()) {
            autoExpectedResultMapper.deleteBatchByPrimaryKey(autoExpectedResults);
        }
        for (Map.Entry<String, Object> entry : requestConstructMap.entrySet()) {
            RequestConstruct requestConstruct = (RequestConstruct) entry.getValue();
            AutoExpectedResult autoExpectedResult = new AutoExpectedResult();
            autoExpectedResult.setId(IDMaker.uuid19());
            autoExpectedResult.setCaseCode(entry.getKey());
            autoExpectedResult.setCreateTime(DateUtil.getCurrentGMTTimestamp());
            autoExpectedResult.setPlatFormId(platFormId);
            autoExpectedResult.setUpdateTime(DateUtil.getCurrentGMTTimestamp());
            AutoRequestMessage autoRequestMessage = new AutoRequestMessage();
            autoRequestMessage.setId(IDMaker.uuid19());
            autoRequestMessage.setExpectedResultId(autoExpectedResult.getId());
            autoRequestMessage.setEndPoint(requestConstruct.getEndpoint());
            autoRequestMessage.setMessage(requestConstruct.getMessage());
            autoRequestMessages.add(autoRequestMessage);
            autoExpectedResultsForInsert.add(autoExpectedResult);
        }
        //批量写入预期结果表和预期请求报文表
        autoExpectedResultMapper.insertBatchWithPk(autoExpectedResultsForInsert);
        autoRequestMessageMapper.insertBatchWithPk(autoRequestMessages);
        //写入redis
        if (redisUtil.hasKey(platFormId)) {
            redisUtil.del(platFormId);
        } else {
            redisUtil.hmset(platFormId, requestConstructMap);
        }


    }

    /**
     * 上传excel根据platFormId和caseCode批量更新预期结果
     *
     * @param multipartfile
     * @param platFormId
     * @throws IOException
     */
    @Override
    public void updateExpectedResult(MultipartFile multipartfile, String platFormId) throws IOException {
        JSONArray jsonArray = POIUtil.readExcel(multipartfile);
        List<AutoExpectedResult> autoExpectedResults = jsonArray.toJavaList(AutoExpectedResult.class);
        autoExpectedResultMapper.batchUpdateExpectedResult(autoExpectedResults);
    }

    /**
     * 根据平台ID和传进来的执行数据批量执行脚本操作
     * @param platFormId
     * @throws Exception
     */
    @Override
    public void autoExcute(String platFormId,Map<Object, Object> requestConstructMap) throws Exception {
        StringBuffer nowDate = new StringBuffer(DateUtil.getNowyyyyMMdd());
        AutoPlatForm autoPlatForm = autoPlatFormMapper.selectByPrimaryKey(platFormId);
        String messageType = autoPlatForm.getMessageType();
        //把批量还是单个执行脚本放到上一级别
       // Map<Object, Object> requestConstructMap = redisUtil.hmget(platFormId);
        List<AutoActualResult> autoActualResults = new ArrayList<>();
        List<AutoResponseMessage> autoResponseMessages = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : requestConstructMap.entrySet()) {
            RequestConstruct requestConstruct = (RequestConstruct) entry.getValue();
            String messageString = requestConstruct.getMessage();
            String endPoint = requestConstruct.getEndpoint();
            String caseCode = (String) entry.getKey();
            String result = null;
            String rtnCode = null;
            //01是json报文，02是xml报文
            if ("01".equals(messageType)) {
                //对报文进行编码，发送服务端,并解析执行结果
                String encode = URLEncoder.encode(messageString);
                StringBuffer postParm = new StringBuffer("message=");
                postParm.append(encode);
                result = HttpClient.postJSON(postParm, endPoint);
                JSONObject resultJson = JSONObject.parseObject(result);
                List bodyList = resultJson.getObject("bodys", List.class);
                //服务暂且不支持不多个body体
                String body = bodyList.get(0).toString();
                MessageBody messageBody = JSONObject.parseObject(body, MessageBody.class);
                //获取执行结果
                rtnCode = messageBody.getRtnCode();
            }else {
                result = HttpClient.postXML(endPoint, messageString);
                int startIndex = result.indexOf("<bodys>");
                int endIndex = result.indexOf("</bodys>");
                String body = result.substring(startIndex, endIndex);
                XStream xStream = new XStream();
                xStream.alias("bodys", MessageBody.class);
                xStream.alias("exts", Map.class);
                MessageBody messageBody = (MessageBody) xStream.fromXML(body);
                rtnCode = messageBody.getRtnCode();
            }

            //写入实际结果表
            AutoActualResult autoActualResult = new AutoActualResult();
            autoActualResult.setId(IDMaker.uuid19());
            autoActualResult.setBatchId(nowDate.append(IDMaker.uuid19()).toString());
            autoActualResult.setExcuteTime(DateUtil.getCurrentGMTTimestamp());
            autoActualResult.setPlatFormId(platFormId);
            autoActualResult.setActualResult(rtnCode);
            autoActualResults.add(autoActualResult);
            //把执行结果和报文写入数据库
            AutoResponseMessage autoResponseMessage = new AutoResponseMessage();
            autoResponseMessage.setId(IDMaker.uuid19());
            autoResponseMessage.setActualResultId(autoActualResult.getId());
            autoResponseMessage.setMessage(result);
            autoResponseMessages.add(autoResponseMessage);
            /*//把实际执行结果写入数据库3
            redisUtil.set(caseCode, rtnCode, RedisConstants.datebase3);
            //把响应报文存入redis数据库4
            redisUtil.set(caseCode, result, RedisConstants.datebase4);*/

        }
        //批量插入实际执行结果
        autoActualResultMapper.insertBatchWithPk(autoActualResults);
        //批量插入实际执行报文
        autoResponseMessageMapper.insertBatchWithPk(autoResponseMessages);
    }
}
