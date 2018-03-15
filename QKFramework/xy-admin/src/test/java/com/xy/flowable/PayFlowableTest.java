package com.xy.flowable;

import java.util.List;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.Before;
import org.junit.Test;

/**
 * 工作流的业务测试
 *
 * @author tom
 * @date 2017-12-02 20:37
 */
public class PayFlowableTest {

    ProcessEngine processEngine;

    @Before
    public void init() {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://127.0.0.1:3306/xy_flowable?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        processEngine = cfg.buildProcessEngine();
    }

    /**
     * 发布流程
     */
    @Test
    public void deploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("PAYProcess.bpmn20.xml")
                .deploy();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());
    }

    /**
     * 查看发布
     */
    @Test
    public void findDeploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for (Deployment deployment : list) {
            System.out.println(deployment.getCategory());
            System.out.println(deployment.getName());
            System.out.println("deploy = " + deployment.getId());
            System.out.println("key = " + deployment.getKey());
        }
    }

}
