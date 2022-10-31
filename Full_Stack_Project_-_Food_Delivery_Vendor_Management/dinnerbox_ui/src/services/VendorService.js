import axios from "axios";

const VENDOR_API_BASE_URL = "http://localhost:8080/api/v1/vendor";

class VendorService{

    saveVendor(vendor){
        return axios.post(VENDOR_API_BASE_URL, vendor);
    }

    getVendors(){
        return axios.get(VENDOR_API_BASE_URL);
    }

    deleteVendor(id){
        return axios.delete(VENDOR_API_BASE_URL + "/" + id);
    }

    getVendorById(id){
        return axios.get(VENDOR_API_BASE_URL + "/" + id);
    }

    updateVendor(vendor, id){
        return axios.put(VENDOR_API_BASE_URL + "/" + id, vendor);
    }
}

export default new VendorService();

