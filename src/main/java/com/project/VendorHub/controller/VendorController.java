package com.project.VendorHub.controller;

import com.project.VendorHub.constants.Constants;
import com.project.VendorHub.entity.Vendor;
import com.project.VendorHub.model.Response;
import com.project.VendorHub.service.VendorService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = Constants.FRONTEND_ORIGIN)
@RequestMapping("/vendors")
public class VendorController{
    private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

    private final VendorService vendorService;



    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity < Response > createVendor(@RequestBody Vendor vendor) {
        logger.info("Request received to create a vendor: {}", vendor);

        try {
            vendorService.createVendor(vendor);

            logger.info("Vendor has been created successfully: {}", vendor.toString());

            return new ResponseEntity < > (new Response("Vendor has been created successfully."), HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            logger.error("Failed to create vendor: {}", ex.getMessage());

            return new ResponseEntity < > (new Response(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity < Page < Vendor >> getAllVendors(Pageable pageable) {
        logger.info("Request received to fetch all vendors.");

        try {
            Page<Vendor> vendors = vendorService.getAllVendors(pageable);

            logger.info("All vendors has been fetched successfully.");
            return ResponseEntity.ok(vendors);
        } catch (Exception ex) {
            logger.error("Failed to fetch all vendors: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable("id") Long id) {
        logger.info("Request received to fetch a vendor with id: {}", id);

        try {
            Vendor vendor = vendorService.getVendorById(id);

            logger.info("Vendor has been successfully fetched by ID: {}", vendor.toString());
            return ResponseEntity.ok(vendor);
        } catch (EntityNotFoundException ex) {
            logger.error("Vendor not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(ex.getMessage()));
        } catch (Exception ex) {
            logger.error("Failed to fetch vendor: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity < Response > updateVendor(@PathVariable("id") Long id, @RequestBody Vendor vendorDetails) {
        logger.info("Request received to update a vendor with id {}: {}", id, vendorDetails);

        try {
            vendorService.updateVendor(id,vendorDetails);

            return new ResponseEntity < > (new Response("Vendor has been updated successfully"), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            logger.error("Vendor validation has been failed with ID {} : {}", id, ex.getMessage());

            return new ResponseEntity < > (new Response(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException ex) {
            logger.error("Vendor not found by ID {} : {}", id, ex.getMessage());

            return new ResponseEntity < > (new Response(ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("Failed to update vendor by ID {} : {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < Response > deleteVendor(@PathVariable("id") Long id) {
        logger.info("Request received to delete a vendor with id: {}", id);

        try {
            vendorService.deleteVendor(id);

            return new ResponseEntity < > (new Response("Vendor has been deleted successfully"), HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException ex) {
            logger.error("Vendor not found by ID {} : {}", id, ex.getMessage());

            return new ResponseEntity < > (new Response(ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("Failed to delete vendor by ID {} : {}", id, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}