package com.code.system.service.impl;

import java.util.List;

import com.code.system.domain.SystemUserAccount;
import com.code.system.mapper.SystemUserAccountMapper;
import com.code.system.service.ISystemUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息Service业务层处理
 */
@Service
public class SystemUserAccountServiceImpl implements ISystemUserAccountService {
    @Autowired
    private SystemUserAccountMapper systemUserAccountMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户信息主键
     * @return 用户信息
     */
    @Override
    public SystemUserAccount selectSystemUserAccountById(Long id) {
        return systemUserAccountMapper.selectSystemUserAccountById(id);
    }

    /**
     * 查询用户信息列表
     *
     * @param systemUserAccount 用户信息
     * @return 用户信息
     */
    @Override
    public List<SystemUserAccount> selectSystemUserAccountList(SystemUserAccount systemUserAccount) {
        return systemUserAccountMapper.selectSystemUserAccountList(systemUserAccount);
    }

    /**
     * 新增用户信息
     *
     * @param systemUserAccount 用户信息
     * @return 结果
     */
    @Override
    public int insertSystemUserAccount(SystemUserAccount systemUserAccount) {
        return systemUserAccountMapper.insertSystemUserAccount(systemUserAccount);
    }

    /**
     * 修改用户信息
     *
     * @param systemUserAccount 用户信息
     * @return 结果
     */
    @Override
    public int updateSystemUserAccount(SystemUserAccount systemUserAccount) {
        return systemUserAccountMapper.updateSystemUserAccount(systemUserAccount);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSystemUserAccountByIds(Long[] ids) {
        return systemUserAccountMapper.deleteSystemUserAccountByIds(ids);
    }

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSystemUserAccountById(Long id) {
        return systemUserAccountMapper.deleteSystemUserAccountById(id);
    }
}
