package com.project.VendorHub.validation;

import com.project.VendorHub.constants.Constants;
import com.project.VendorHub.entity.Vendor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class VendorValidation {

    public void validationVendor(Vendor vendor) {
        validateName(vendor.getName());
        validateBankAccountNo(vendor.getBankAccountNo());
        validateBankName(vendor.getBankName());
        validateZipCode(vendor.getZipCode());
    }

    private void validateName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException(Constants.NAME_EMPTY);
        }
    }

    private void validateBankAccountNo(String bankAccountNo) {
        if (StringUtils.isBlank(bankAccountNo)) {
            throw new IllegalArgumentException(Constants.BANK_ACCOUNT_NUMBER_EMPTY);
        }

        if (!StringUtils.isNumeric(bankAccountNo)) {
            throw new IllegalArgumentException(Constants.BANK_ACCOUNT_NUMBER_DIGITS);
        }
    }

    private void validateBankName(String bankName) {
        if (StringUtils.isBlank(bankName)) {
            throw new IllegalArgumentException(Constants.BANK_NAME_EMPTY);
        }
    }

    private void validateZipCode(String zipCode) {
        if (!StringUtils.isNumeric(zipCode)) {
            throw new IllegalArgumentException(Constants.ZIP_CODE_DIGITS);
        }
    }
}
