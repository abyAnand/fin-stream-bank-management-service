package com.finStream.bankmanagementservice.repository;

import com.finStream.bankmanagementservice.entity.AccountBankSetting;
import com.finStream.bankmanagementservice.entity.bankSetting.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountBankSettingRepository extends JpaRepository<AccountBankSetting, UUID> {

    List<? extends AccountBankSetting> findAllAccountsByBankId(UUID bankId);

    List<CheckingAccountsBankSetting> findAllCheckingAccountsBankSettingByBankId(UUID bankId);

    List<FDAccountsBankSetting> findAllFDAccountsBankSettingByBankId(UUID bankId);

    List<JointAccountsBankSetting> findAllJointAccountsBankSettingByBankId(UUID bankId);

    List<MoneyMarketAccountsBankSetting> findAllMoneyMarketAccountsBankSettingByBankId(UUID bankId);

    List<SavingsAccountsBankSetting> findAllSavingsAccountsBankSettingByBankId(UUID bankId);

    CheckingAccountsBankSetting findCheckingAccountsBankSettingById(UUID id);
    FDAccountsBankSetting findFDAccountsBankSettingById(UUID id);
    JointAccountsBankSetting findJointAccountsBankSettingById(UUID id);
    MoneyMarketAccountsBankSetting findMoneyMarketAccountsBankSettingById(UUID id);
    SavingsAccountsBankSetting findSavingsAccountsBankSettingById(UUID id);
}
