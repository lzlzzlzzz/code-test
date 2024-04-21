package com.code.system.service;

import java.util.List;

import com.code.system.domain.SystemUserAccount;

/**
 * 用户信息Service接口
 */
public interface ISystemUserAccountService {
    /**
     * 查询用户信息
     *
     * @param id 用户信息主键
     * @return 用户信息
     */
    public SystemUserAccount selectSystemUserAccountById(Long id);

    /**
     * 查询用户信息列表
     *
     * @param systemUserAccount 用户信息
     * @return 用户信息集合
     */
    public List<SystemUserAccount> selectSystemUserAccountList(SystemUserAccount systemUserAccount);

    /**
     * 新增用户信息
     *
     * @param systemUserAccount 用户信息
     * @return 结果
     */
    public int insertSystemUserAccount(SystemUserAccount systemUserAccount);

    /**
     * 修改用户信息
     *
     * @param systemUserAccount 用户信息
     * @return 结果
     */
    public int updateSystemUserAccount(SystemUserAccount systemUserAccount);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteSystemUserAccountByIds(Long[] ids);

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息主键
     * @return 结果
     */
    public int deleteSystemUserAccountById(Long id);
}
