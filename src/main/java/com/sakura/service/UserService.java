package com.sakura.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.sakura.dao.NameDao;
import com.sakura.dao.SexDao;
import com.sakura.entity.Name;
import com.sakura.entity.Sex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource
    private NameDao nameDao;
    @Resource
    private SexDao sexDao;

//    @Resource
//    private DruidDataSource dataSource;


    /**
     * 1.添加事务注解
     * 使用propagation 指定事务的传播行为，即当前的事务方法被另外一个事务方法调用时如何使用事务。
     * 默认取值为REQUIRED，即使用调用方法的事务
     * REQUIRES_NEW：使用自己的事务，调用的事务方法的事务被挂起。
     *
     * 2.使用isolation 指定事务的隔离级别，最常用的取值为READ_COMMITTED
     * 3.默认情况下 Spring 的声明式事务对所有的运行时异常进行回滚，也可以通过对应的属性进行设置。通常情况下，默认值即可。
     * 4.使用readOnly 指定事务是否为只读。 表示这个事务只读取数据但不更新数据，这样可以帮助数据库引擎优化事务。若真的是一个只读取数据库值得方法，应设置readOnly=true
     * 5.使用timeOut 指定强制回滚之前事务可以占用的时间。
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public void updateUser(){
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource);
//        DefaultTransactionDefinition transDef = new DefaultTransactionDefinition(); // 定义事务属性
//        transDef.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED); // 设置传播行为属性
//        TransactionStatus status = dataSourceTransactionManager.getTransaction(transDef); // 获得事务状态
            Name name = new Name();
            name.setID(1);
            name.setName("LMF");
            nameDao.updateName(name);
            Sex sex = new Sex();
            sex.setID(1);
            sex.setSex("男");
            sexDao.updateSex(sex);
            sexDao.findByKey(1);

//            dataSourceTransactionManager.rollback(status);

//        dataSourceTransactionManager.commit(status);

    }
}
