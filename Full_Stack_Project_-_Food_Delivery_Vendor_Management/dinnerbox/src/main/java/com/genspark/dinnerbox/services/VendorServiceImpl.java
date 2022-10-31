package com.genspark.dinnerbox.services;

import com.genspark.dinnerbox.entity.VendorEntity;
import com.genspark.dinnerbox.model.Vendor;
import com.genspark.dinnerbox.repository.VendorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) throws Exception {
        try{
            VendorEntity vendorEntity = new VendorEntity();
            BeanUtils.copyProperties(vendor, vendorEntity);
            if (vendor.getFile() != null && !vendor.getFile().equalsIgnoreCase("null"))
                vendorEntity.setFile(vendor.getFile());
            else
                vendorEntity.setFile(null);

            vendorEntity = vendorRepository.save(vendorEntity);

            vendor.setId(vendorEntity.getId());
            vendor.setFile(null);
            vendor.setLicense(vendorEntity.getFile());

        } catch (Exception e) {
            throw new Exception("Could not save vendor: " + e);
        }

        return vendor;

    }
    @Override
    public List<Vendor> getAllVendors(){
        List<VendorEntity> vendorEntities = vendorRepository.findAll();

        List<Vendor> vendors = vendorEntities.stream().map(ven ->
                Vendor.builder()
                        .id(ven.getId())
                        .emailId(ven.getEmailId())
                        .name(ven.getName())
                        .cuisine(ven.getCuisine())
                        .license(ven.getFile())
                        .build()
        ).collect(Collectors.toList());
        return vendors;
    }

    @Override
    public boolean deleteVendor(Long id) {
        VendorEntity vendor = vendorRepository.findById(id).get();
        vendorRepository.delete(vendor);
        return true;
    }

    @Override
    public Vendor getVendorById(Long id) {
        VendorEntity vendorEntity = vendorRepository.findById(id).get();
        Vendor vendor = new Vendor();
        BeanUtils.copyProperties(vendorEntity, vendor);
        return vendor;
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        VendorEntity vendorEntity = vendorRepository.findById(id).get();
        vendorEntity.setEmailId(vendor.getEmailId());
        vendorEntity.setName(vendor.getName());
        vendorEntity.setCuisine(vendor.getCuisine());

        vendorRepository.save(vendorEntity);
        return vendor;
    }
}
