package com.springboot.datasource.common.datasource.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Component
public class TransactionUtils {
    private static final Logger logger = LogManager.getLogger(TransactionUtils.class);

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * begin transactional
     *
     * @param transactional
     * @return TransactionStatus
     */
    public TransactionStatus begin(Transactional transactional) {
        DefaultTransactionAttribute txAttribute = new DefaultTransactionAttribute();

        txAttribute.setQualifier(transactional.value());
        txAttribute.setReadOnly(transactional.readOnly());
        txAttribute.setTimeout(transactional.timeout());
        txAttribute.setIsolationLevel(transactional.isolation().value());
        txAttribute.setPropagationBehavior(transactional.propagation().value());
        //注意: 注解 Transactional.rollbackFor() 返回的是一个继承 Throwable 的 Class 对象数组
        //而下面方法只能调置单个继承 Throwable 的 Class 对象,且只对 RuntimeException 和 Error 异常有效
//        txAttribute.rollbackOn()

        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(txAttribute);
        logger.info("begin transactional");
        return transactionStatus;
    }

    /**
     * commit transactional
     *
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.commit(transactionStatus);
        logger.info("commit transactional");
    }

    /**
     * rollback transactional
     *
     * @param transactionStatus
     */
    public void rollback(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.rollback(transactionStatus);
        logger.info("rollback transactional");
    }
}
