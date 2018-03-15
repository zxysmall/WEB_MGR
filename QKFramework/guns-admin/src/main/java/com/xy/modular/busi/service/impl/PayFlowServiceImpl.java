package com.stylefeng.guns.modular.busi.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.common.api.FlowableObjectNotFoundException;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.common.persistence.dao.PayFlowMapper;
import com.stylefeng.guns.common.persistence.model.PayFlow;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.modular.busi.service.IPayFlowService;

/**
 * <p>
 * 支付流程跟踪 服务实现类
 * </p>
 *
 * @author tomzhou123
 * @since 2018-03-13
 */
@Service
public class PayFlowServiceImpl extends ServiceImpl<PayFlowMapper, PayFlow> implements IPayFlowService {
	 @Autowired
	    private RuntimeService runtimeService;

	    @Autowired
	    private TaskService taskService;

	    @Autowired
	    private RepositoryService repositoryService;

	    @Autowired
	    private ProcessEngine processEngine;

	    @Override
	    @Transactional(rollbackFor = Exception.class)
	    public void add(PayFlow flow) {
	        try {
				//启动流程
				HashMap<String, Object> map = new HashMap<>();
				map.put("status", 1);
				ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("PAYProcess", map);
				flow.setProcessId(Integer.valueOf(processInstance.getId()));
				//保存业务数据
				this.insert(flow);
			} catch (FlowableObjectNotFoundException e) {
				// TODO Auto-generated catch block
				this.deploy();
				this.add(flow);
			}
	    }
	    
	    @Override
	    public void printProcessImage(Integer seq) throws IOException {
	    	PayFlow flow = this.selectOne(new EntityWrapper<PayFlow>().eq("seq", seq));
	    	Integer processId = flow.getProcessId();
	        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId+"").singleResult();

	        //流程走完的不显示图
	        if (pi == null) {
	            return;
	        }

	        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

	        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
	        String InstanceId = task.getProcessInstanceId();
	        List<Execution> executions = runtimeService
	                .createExecutionQuery()
	                .processInstanceId(InstanceId)
	                .list();

	        //得到正在执行的Activity的Id
	        List<String> activityIds = new ArrayList<>();
	        List<String> flows = new ArrayList<>();
	        for (Execution exe : executions) {
	            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
	            activityIds.addAll(ids);
	        }

	        //获取流程图
	        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
	        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
	        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
	        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0);
	        OutputStream out = null;
	        byte[] buf = new byte[1024];
	        int legth = 0;
	        try {
	            out = HttpKit.getResponse().getOutputStream();
	            while ((legth = in.read(buf)) != -1) {
	                out.write(buf, 0, legth);
	            }
	        } finally {
	            if (in != null) {
	                in.close();
	            }
	            if (out != null) {
	                out.close();
	            }
	        }
	    }
	    
	    @Override
	    @Transactional(rollbackFor = Exception.class)
	    public void pass(String insId) {
	    	PayFlow flow = this.selectOne(new EntityWrapper<PayFlow>().eq("processId", insId));

	    	Task task  = taskService.createTaskQuery().processInstanceId(insId).singleResult();
	    	int status = flow.getStatus();
	    	if(0 == status){
	    		status = -1;
	    	}else{
	    		status = (flow.getStatus() + 1)>3?0:flow.getStatus() + 1;
	    	}
	        //通过审核
	        HashMap<String, Object> map = new HashMap<>();
	        map.put("status", status);
	        taskService.complete(task.getId(), map);

//	        //判断流程是否结束,结束之后修改状态
	        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
	                .processInstanceId(task.getProcessInstanceId())//使用流程实例ID查询
	                .singleResult();
//	        //审核通过修改为通过状态
	        if (pi != null) {
	        	flow.setStatus(status);
	        	flow.update(new EntityWrapper<PayFlow>().eq("processId", insId));
	        }else{
	        	flow.delete(new EntityWrapper<PayFlow>().eq("processId", insId));
	        }
	    }
	    
	    /**
	     * 如果没有发布，先发布流程
	     */
	    private void deploy() {
//	        RepositoryService repositoryService = processEngine.getRepositoryService();
	        Deployment deployment = repositoryService.createDeployment()
	                .addClasspathResource("processes/PAYProcess.bpmn20.xml")
	                .deploy();

	        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
	                .deploymentId(deployment.getId())
	                .singleResult();
	        System.out.println("Found process definition : " + processDefinition.getName());
	    }
}
