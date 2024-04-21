package com.code.system.controller;

import com.code.system.service.ISystemUserAccountService;
import com.code.common.pojo.CommonResult;
import com.code.system.domain.SystemUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.code.common.pojo.CommonResult.success;

/**
 * 用户信息Controller
 */
@RestController
@RequestMapping("/system/account")
public class SystemUserAccountController {
    @Autowired
    private ISystemUserAccountService systemUserAccountService;

    /**
     * 获取用户信息详细信息
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable("id") Long id) {
        return success(systemUserAccountService.selectSystemUserAccountById(id));
    }

    /**
     * 新增用户信息
     */
    @PostMapping
    public CommonResult add(@RequestBody SystemUserAccount systemUserAccount) {
        return success(systemUserAccountService.insertSystemUserAccount(systemUserAccount));
    }

    /**
     * 修改用户信息
     */
    @PutMapping
    public CommonResult edit(@RequestBody SystemUserAccount systemUserAccount) {
        return success(systemUserAccountService.updateSystemUserAccount(systemUserAccount));
    }

    /**
     * 删除用户信息
     */
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable Long[] ids) {
        return success(systemUserAccountService.deleteSystemUserAccountByIds(ids));
    }
}
