package com.finStream.bankmanagementservice.service.interfaces;

import com.finStream.bankmanagementservice.dto.BankSettingDto;
import com.finStream.bankmanagementservice.entity.bankSetting.SavingsAccountsBankSetting;

public interface IAccountSettingService {

    BankSettingDto createAccountSetting(BankSettingDto bankSettingDto);

    BankSettingDto updateAccountSetting(BankSettingDto bankSettingDto);
}
