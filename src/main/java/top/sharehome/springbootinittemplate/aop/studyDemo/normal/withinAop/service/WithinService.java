package top.sharehome.springbootinittemplate.aop.studyDemo.normal.withinAop.service;

/**
 * 针对切点参数为within型的切面类的服务接口
 *
 * @author AntonyCheng
 */
public interface WithinService {

    public void doMethod1();

    public String doMethod2();

    public void doMethod3() throws Exception;

    public void doMethod4(String param);

}