package com.wust.spring.boot.multi.tenant.bean.service.impl;

import com.wust.spring.boot.multi.tenant.bean.api.Clock;
import com.wust.spring.boot.multi.tenant.bean.contant.AccountStatus;
import com.wust.spring.boot.multi.tenant.bean.contant.AccountType;
import com.wust.spring.boot.multi.tenant.bean.contant.IamConstants;
import com.wust.spring.boot.multi.tenant.bean.contant.TenantMessageCodes;
import com.wust.spring.boot.multi.tenant.bean.entity.AccountEntity;
import com.wust.spring.boot.multi.tenant.bean.error.TenantException;
import com.wust.spring.boot.multi.tenant.bean.mapper.AccountMapper;
import com.wust.spring.boot.multi.tenant.bean.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Primary
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private Clock clock;

    @Override
    public void createRootAccount(long rootId) {
        if (accountMapper.selectById(rootId) != null) {
            log.warn("Root Account exists, {}", rootId);
            return;
        }
        //create root user account
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(rootId);
        accountEntity.setOwnedById(accountEntity.getId());//root account own itself
        accountEntity.setLoginName("root");
        accountEntity.setStatus(AccountStatus.NORMAL);
        accountEntity.setAccountType(AccountType.USER);
        accountEntity.setEffectiveDate(clock.now());
        if (accountMapper.insert(accountEntity) != 1) {
            throw new TenantException(TenantMessageCodes.CREATE_TABLE_FAILED);
        }
    }
}