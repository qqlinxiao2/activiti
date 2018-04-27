package com.xxx.helloworld;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class HelloWorld {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
/**
 * 部署流程定义
 */
@Test
public void deploymentProcessDefinition(){

    //获取流程定义与部署相关Service
    Deployment deployment = processEngine.getRepositoryService()
            .createDeployment()     //创建一个部署对象
            .name("helloworld入门程序")
            .addClasspathResource("diagrams/helloworld.bpmn")//加载资源文件
            .deploy();//完成部署
    System.out.println(deployment.getId());
    System.out.println(deployment.getName());
}

    /**
     * 启动流程实例
     */
    @Test
    public void startProcessInstance(){
        //获取与正在执行的流程示例和执行对象相关的Service
        ProcessInstance processInstance = processEngine.getRuntimeService()
                //使用流程定义的key启动实例，key对应bpmn文件中id的属性值，默认按照最新版本流程启动
                .startProcessInstanceByKey("helloworld");
        System.out.println(processInstance.getId());
        System.out.println(processInstance.getProcessDefinitionId());
    }

    /**
     * 查询当前的个人任务
     */
    @Test
    public void findPersonalTask(){
        //与正在执行的任务相关的Service
        List<Task> list = processEngine.getTaskService()
                .createTaskQuery()  //创建查询任务对象
                .taskAssignee("王五")     //指定个人任务查询，指定办理人
                .list();
        if(list != null && list.size() > 0){
            for(Task task : list){
                System.out.println(task.getId());
                System.out.println(task.getName());
                System.out.println(task.getCreateTime());
                System.out.println(task.getAssignee());
                System.out.println(task.getProcessInstanceId());
                System.out.println(task.getExecutionId());
                System.out.println(task.getProcessDefinitionId());
            }
        }
    }

    /**
     * 完成我的任务
     */
    @Test
    public void completePersonalTask(){
        processEngine.getTaskService()
                .complete("7502");
    }
}
