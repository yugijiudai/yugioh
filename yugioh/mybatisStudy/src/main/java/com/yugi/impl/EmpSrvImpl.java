package com.yugi.impl;

import com.yugi.dao.AccountMapper;
import com.yugi.entity.Emp;
import com.yugi.logger.LogType;
import com.yugi.service.EmpSrv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
/**
 * Created by Administrator on 2016/4/8.
 */
@Service
public class EmpSrvImpl implements EmpSrv {

    @Resource
    private AccountMapper accountMapper;

    private Logger logger = LogManager.getLogger(LogType.SQL);

    @Override
    public List<Emp> queryForAll() {
        logger.debug("sql");
        return accountMapper.findAllEmp();
    }
}
