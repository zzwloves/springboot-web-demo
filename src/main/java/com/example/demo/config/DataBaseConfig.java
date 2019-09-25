package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.example.demo.base.ConditionalOnNotProdEnv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 14:18
 */
@Slf4j
@Configuration
public class DataBaseConfig {

    @Autowired
    DataBaseProperties dataBaseProperties;

    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dataBaseProperties.getUrl());
        datasource.setUsername(dataBaseProperties.getUsername());
        datasource.setPassword(dataBaseProperties.getPassword());
        datasource.setDriverClassName(dataBaseProperties.getDriverClassName());

        // configuration
        datasource.setInitialSize(dataBaseProperties.getInitialSize());
        datasource.setMinIdle(dataBaseProperties.getMinIdle());
        datasource.setMaxActive(dataBaseProperties.getMaxActive());
        datasource.setMaxWait(dataBaseProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataBaseProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataBaseProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataBaseProperties.getValidationQuery());
        datasource.setTestWhileIdle(dataBaseProperties.getTestWhileIdle());
        datasource.setTestOnBorrow(dataBaseProperties.getTestOnBorrow());
        datasource.setTestOnReturn(dataBaseProperties.getTestOnReturn());
        datasource.setPoolPreparedStatements(dataBaseProperties.getPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dataBaseProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(dataBaseProperties.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(dataBaseProperties.getConnectionProperties());
        return datasource;
    }

    /**
     * 非生产环境下生效
     * http://127.0.0.1:8080/monitor/druid/login.html
     * 注册一个StatViewServlet 相当于在web.xml中声明了一个servlet
     * @return: ServletRegistrationBean
     */
    @Bean
    @ConditionalOnNotProdEnv
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.setEnabled(true);
        reg.addUrlMappings("/monitor/druid/*");
        // 白名单 
        reg.addInitParameter("allow", "127.0.0.1");
        // IP黑名单(共同存在时，deny优先于allow)
        // reg.addInitParameter("deny", "192.168.2.105");
        // /druid/login.html登录时账号密码 
        reg.addInitParameter("loginUsername", "root");
        reg.addInitParameter("loginPassword", "root");
        // 是否能够重置数据 禁用HTML页面上的“Reset All”功能 
        reg.addInitParameter("resetEnable", "false");
        return reg;
    }

    /**
     * 非生产环境下生效
     * 注册一个：filterRegistrationBean 相当于在web.xml中声明了一个Filter   
     */
    @Bean
    @ConditionalOnNotProdEnv
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean druidStatFilter = new FilterRegistrationBean();
        druidStatFilter.setFilter(new WebStatFilter());
        // 添加过滤规则
        druidStatFilter.addUrlPatterns("/*");
        // 监控选项滤器
        druidStatFilter.addInitParameter("DruidWebStatFilter", "/*");
        // 添加不需要忽略的格式信息
        druidStatFilter.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/monitor/druid/*");
        // 配置profileEnable能够监控单个url调用的sql列表
        druidStatFilter.addInitParameter("profileEnable", "true");
        // 当前的cookie的用户 
        druidStatFilter.addInitParameter("principalCookieName", "USER_COOKIE");
        // 当前的session的用户
        druidStatFilter.addInitParameter("principalSessionName", "USER_SESSION");
        return druidStatFilter;
    }

}
