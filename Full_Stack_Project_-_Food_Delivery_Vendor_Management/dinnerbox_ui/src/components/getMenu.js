import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import VendorService from '../services/VendorService';


const getMenu = ({vendor}) => {

    const navigate = useNavigate();

    const [loading, setLoading] = useState(true);
    const [vendors, setVendors] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try{
                const response = await VendorService.getVendorById(vendor);
                setVendors(response.data);
            } catch (error){
                console.log(error);
            }

            setLoading(false);
        };
        fetchData();
    }, []);

  return (
    <div className="container mx-auto my-8">
        <div className="h-12">
            <button 
            onClick={() => navigate("/addVendor")}
            className="rounded bg-slate-600 text-white px-6 py-2 font-semibold"> Add Vendor</button>
        </div>
    <div className="flex shadow border-b">
        <table className="min-w-full">
            <thead className="bg-gray-50">
                <tr>
                    <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">Name</th>
                    <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">Contact</th>
                    <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">Cuisine</th>
                    <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">License</th>
                    <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">Actions</th>
                </tr>
            </thead>

        </table>
    </div>
    </div>
  )
}

export default getMenu