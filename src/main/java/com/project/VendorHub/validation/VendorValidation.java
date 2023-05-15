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
        validateAddressLine1(vendor.getAddressLine1());
        validateAddressLine2(vendor.getAddressLine2());
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

        if (bankAccountNo.length() > Constants.MAX_BANK_ACCOUNT_NO_LENGTH) {
            throw new IllegalArgumentException(Constants.BANK_ACCOUNT_NUMBER_LENGTH);
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

        if (zipCode.length() > Constants.MAX_ZIP_CODE_LENGTH) {
            throw new IllegalArgumentException(Constants.ZIP_CODE_LENGTH);
        }
    }

    private void validateAddressLine1(String addressLine1) {
        if (StringUtils.isNotBlank(addressLine1) && addressLine1.length() > Constants.MAX_ADDRESS_LENGTH) {
            throw new IllegalArgumentException(Constants.ADDRESS_LINE1_LENGTH);
        }
    }

    private void validateAddressLine2(String addressLine2) {
        if (StringUtils.isNotBlank(addressLine2) && addressLine2.length() > Constants.MAX_ADDRESS_LENGTH) {
            throw new IllegalArgumentException(Constants.ADDRESS_LINE2_LENGTH);
        }
    }

    private void validateCity(String city) {
        if (StringUtils.isNotBlank(city) && city.length() > Constants.MAX_CITY_COUNTRY_LENGTH) {
            throw new IllegalArgumentException(Constants.CITY_LENGTH);
        }
    }

    private void validateCountry(String country) {
        if (StringUtils.isNotBlank(country) && country.length() > Constants.MAX_CITY_COUNTRY_LENGTH) {
            throw new IllegalArgumentException(Constants.COUNTRY_LENGTH);
        }
    }
}
