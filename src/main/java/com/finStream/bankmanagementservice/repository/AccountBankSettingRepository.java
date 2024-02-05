package com.finStream.bankmanagementservice.repository;

import com.finStream.bankmanagementservice.entity.accountSetting.AccountSetting;
import com.finStream.bankmanagementservice.entity.accountSetting.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountBankSettingRepository extends JpaRepository<AccountSetting, UUID> {

    List<? extends AccountSetting> findAllAccountsByBankId(UUID bankId);

    List<CheckingAccountsSetting> findAllCheckingAccountsBankSettingByBankId(UUID bankId);

    List<FDAccountsSetting> findAllFDAccountsBankSettingByBankId(UUID bankId);

    List<JointAccountsSetting> findAllJointAccountsBankSettingByBankId(UUID bankId);

    List<MoneyMarketAccountsSetting> findAllMoneyMarketAccountsBankSettingByBankId(UUID bankId);

    List<SavingsAccountsSetting> findAllSavingsAccountsBankSettingByBankId(UUID bankId);

    CheckingAccountsSetting findCheckingAccountsBankSettingById(UUID id);
    FDAccountsSetting findFDAccountsBankSettingById(UUID id);
    JointAccountsSetting findJointAccountsBankSettingById(UUID id);
    MoneyMarketAccountsSetting findMoneyMarketAccountsBankSettingById(UUID id);
    SavingsAccountsSetting findSavingsAccountsBankSettingById(UUID id);
}
