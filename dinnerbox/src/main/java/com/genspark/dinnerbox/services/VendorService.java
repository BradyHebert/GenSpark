package com.genspark.dinnerbox.services;

import com.genspark.dinnerbox.model.Vendor;

import java.util.List;

public interface VendorService {
    Vendor createVendor(Vendor vendor) throws Exception;

    List<Vendor> getAllVendors();

    boolean deleteVendor(Long id);

    Vendor getVendorById(Long id);

    Vendor updateVendor(Long id, Vendor vendor);
}
