package com.project.VendorHub.service;

import com.project.VendorHub.entity.Vendor;
import com.project.VendorHub.repository.VendorRepository;
import com.project.VendorHub.validation.VendorValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;
   private final VendorValidation vendorValidation;

    public VendorService(VendorRepository vendorRepository, VendorValidation vendorValidation) {
        this.vendorRepository = vendorRepository;
        this.vendorValidation = vendorValidation;
    }

    /**
     * Create a new vendor.
     *
     * @param vendor The vendor object to be created.
     */
    public void createVendor(Vendor vendor) {
        vendorValidation.validationVendor(vendor);

        vendorRepository.save(vendor);
    }

    /**
     * Get all vendors with pagination.
     *
     * @param pageable The pageable object for pagination.
     * @return A page of Vendor objects.
     */
    public Page<Vendor> getAllVendors(Pageable pageable) {
        return vendorRepository.findAll(pageable);
    }

    /**
     * Get a vendor by its ID.
     *
     * @param id The ID of the vendor to retrieve.
     * @return The Vendor object.
     * @throws EntityNotFoundException If the vendor with the given ID is not found.
     */
    public Vendor getVendorById(Long id)  {
        return vendorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vendor not found"));
    }

    /**
     * Update an existing vendor.
     *
     * @param id             The ID of the vendor to update.
     * @param vendorDetails The updated vendor details.
     * @throws EntityNotFoundException If the vendor with the given ID is not found.
     */
    public void updateVendor(Long id, Vendor vendorDetails)  {
        Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vendor not found"));

        vendorValidation.validationVendor(vendorDetails);

        String name = vendorDetails.getName();
        String bankAccountNo = vendorDetails.getBankAccountNo();
        String bankName = vendorDetails.getBankName();
        String addressLine1 = vendorDetails.getAddressLine1();
        String addressLine2 = vendorDetails.getAddressLine2();
        String city = vendorDetails.getCity();
        String country = vendorDetails.getCountry();
        String zipCode = vendorDetails.getZipCode();

        vendor.setName(name);
        vendor.setBankAccountNo(bankAccountNo);
        vendor.setBankName(bankName);
        vendor.setAddressLine1(addressLine1);
        vendor.setAddressLine2(addressLine2);
        vendor.setCity(city);
        vendor.setCountry(country);
        vendor.setZipCode(zipCode);

        vendorRepository.save(vendor);
    }

    /**
     * Delete a vendor by its ID.
     *
     * @param id The ID of the vendor to delete.
     * @throws EntityNotFoundException If the vendor with the given ID is not found.
     */
    public void deleteVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vendor not found"));
        vendorRepository.deleteById(id);
    }
}
