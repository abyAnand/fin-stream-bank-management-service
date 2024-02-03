package com.finStream.bankmanagementservice.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    SAVINGS("Savings Account", "Savings accounts are designed for individuals to save money over time. They usually offer a modest interest rate, making them suitable for long-term savings goals, emergency funds, or general savings."),
    CHECKING("Checking Account", "Checking accounts are transactional accounts that provide easy access to funds. They are suitable for everyday spending, bill payments, and day-to-day financial transactions. Checking accounts often come with features like checks, debit cards, and online banking."),
    FD("Fixed Deposit Account (FD)", "FDs are time deposits with fixed terms and interest rates. They are suitable for individuals looking to save money for a specific period without needing immediate access to the funds. Early withdrawals may result in penalties."),
    MONEY_MARKET("Money Market Account", "Money market accounts combine features of both savings and checking accounts. They typically offer higher interest rates than regular savings accounts and allow a limited number of checks or debit card transactions per month. Money market accounts are suitable for those seeking a higher interest rate with some liquidity."),
    IRA("Individual Retirement Account (IRA)", "IRAs are long-term savings accounts designed for retirement. They offer tax advantages, and there are two main types: Traditional IRA (contributions may be tax-deductible) and Roth IRA (contributions are made with after-tax dollars, but withdrawals are tax-free."),
    JOINT("Joint Account", "Joint accounts are shared by two or more individuals. They are commonly used by couples or family members for shared expenses and financial management."),
    BUSINESS("Business Account", "Business accounts are designed for businesses to manage their finances. They may include features such as business loans, merchant services, and business-specific tools. Different types of business accounts are available, such as business savings and business checking."),
    STUDENT("Student Account", "Student accounts are tailored for students. They often come with lower fees and special features to help students manage their finances while in school. Some accounts may also offer overdraft protection."),
    TRUST("Trust Account", "Trust accounts are established for the purpose of managing and distributing funds according to the terms of a trust agreement. They are used for estate planning and wealth management."),
    CUSTODIAL("Custodial Account", "Custodial accounts are set up for a minor, managed by a custodian (usually a parent or guardian). They can be used for the child's benefit, such as education expenses.");

    private final String displayName;
    private final String useCase;

    AccountType(String displayName, String useCase) {
        this.displayName = displayName;
        this.useCase = useCase;
    }

}