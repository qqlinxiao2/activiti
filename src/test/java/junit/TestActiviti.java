package junit;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

public class TestActiviti {
    /**
     * 使用代码创建工作流使用的23张表
     */
    @Test
    public void createTable(){
        //创建引擎配置类
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://192.168.27.14:3306/activiti");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");

        //不自动创建表，需要表存在 DB_SCHEMA_UPDATE_FALSE = "false";
        //先删除表，再创建表 DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";
        //如果表不存在，先创建表 DB_SCHEMA_UPDATE_TRUE = "true";
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //创建工作流核心对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println(processEngine);
    }

    @Test
    public void createTable2(){
        ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
    }


    @Test
    public void createTable3(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    }

    @Test
    public void getService(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //管理流程定义
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //执行管理，包括启动、推进、删除流程实例等
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //任务管理
        TaskService taskService = processEngine.getTaskService();
        //历史管理（执行完的数据的管理
        HistoryService historyService = processEngine.getHistoryService();
        //组织机构管理
        IdentityService identityService = processEngine.getIdentityService();
        //可选服务，任务表单管理
        FormService formService = processEngine.getFormService();

        ManagementService managementService = processEngine.getManagementService();
    }

    @Test
    public void testRespositoryService(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //可产生DeploymentBuilder用来定义流程部署的相关参数
        DeploymentBuilder deployment = repositoryService.createDeployment();
        //删除流程定义
        repositoryService.deleteDeployment("deploymentId");
    }



}
