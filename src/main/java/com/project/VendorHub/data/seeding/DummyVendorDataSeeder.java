package com.project.VendorHub.data.seeding;

import com.project.VendorHub.constants.Constants;
import com.project.VendorHub.controller.VendorController;
import com.project.VendorHub.entity.Vendor;
import com.project.VendorHub.repository.VendorRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import java.util.Random;

@Component
public class DummyVendorDataSeeder {
    private static final Logger logger = LoggerFactory.getLogger(DummyVendorDataSeeder.class);

    private final VendorRepository vendorRepository;

    @Autowired
    public DummyVendorDataSeeder(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    // Generate dummy vendors only if no vendors exist in the database
    @PostConstruct
    public void seedDummyVendors() {
        if (vendorRepository.count() == 0) {
            logger.info("[STARTED] Generating dummy vendors data for testing purpose.");

            for (int i = 1; i <= Constants.DUMMY_DATA_LENGTH; i++) {
                Vendor vendor = createDummyVendor(i);
                vendorRepository.save(vendor);

                logger.info("[IN-PROGRESS] Generated {} dummy vendor data for testing purpose.", i);
            }

            logger.info("[FINISHED] Generated {} dummy vendors data for testing purpose.", Constants.DUMMY_DATA_LENGTH);
        }
    }

    private Vendor createDummyVendor(int index) {
        Faker faker = new Faker();

        Vendor vendor = new Vendor();
        vendor.setName(faker.company().name());
        vendor.setBankAccountNo(generateRandomBankAccountNo());
        vendor.setBankName(faker.company().name());
        vendor.setAddressLine1(faker.address().streetAddress());
        vendor.setAddressLine2(faker.address().secondaryAddress());
        vendor.setCity(faker.address().city());
        vendor.setCountry(faker.address().country());
        vendor.setZipCode(faker.address().zipCode());
        return vendor;
    }

    private String generateRandomBankAccountNo() {
        Random random = new Random();
        StringBuilder accountNoBuilder = new StringBuilder();

        for (int i = 0; i < Constants.ACCOUNT_NUMBER_DIGIT; i++) {
            int digit = random.nextInt(Constants.ACCOUNT_NUMBER_DIGIT);
            accountNoBuilder.append(digit);
        }

        return accountNoBuilder.toString();
    }

}
