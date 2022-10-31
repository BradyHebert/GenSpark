import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import VendorService from '../services/VendorService';


const AddVendor = () => {
    const VENDOR_API_BASE_URL = "http://localhost:8080/api/v1/vendor";
    const [fileToPost, setFileToPost] = useState(null);
    const [valid, setValid] = useState(false);

    const [vendor, setVendor] = useState({
        id:"",
        restaraunt:"",
        emailId:"",
        cuisine:"",
    })

    const navigate = useNavigate();

    const handleChange = (e) => {
        const value = e.target.value;
        setVendor({...vendor, [e.target.name] : value});
    }

    const handleFile = (e) => {
        setValid(true);
        const reader = new FileReader();
        if (e.target.files[0]){
            reader.readAsDataURL(e.target.files[0]);
            reader.onload = (e) => {
                setFileToPost(e.target.result);
            }
        }
        
    }

    const saveVendor = (e) => {
        e.preventDefault();

        if (valid == false) return;
        const formData = new FormData();

        formData.append("file", fileToPost);
        console.log("license: " + fileToPost);
        formData.append("name", vendor.restaraunt);
        formData.append("email", vendor.emailId);
        formData.append("cuisine", vendor.cuisine);

        axios
            .post(VENDOR_API_BASE_URL, formData, {
                headers: { Accept: "application/json"},
            })
            .then((response) => {
                console.log(response);
                navigate("/vendorList")
            }).catch((error) => {
                console.log(error);
            })
    }

    const reset = (e) => {
        e.preventDefault();
        setVendor({
            id:"",
            restaraunt:"",
            emailId:"",
            cuisine:"",

        })
    }
    
  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
        <div className="px-8 py-8">
            <div className="font-thin text-2xl tracking-wider">
                <h1>Register New Restaraunt</h1>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">Restaurant Name</label>
                <input 
                type="text" 
                name="restaraunt"
                value={vendor.restaraunt}
                onChange={(e) => handleChange(e)}
                className="h-10 w-96 border mt-2 px-2 py-2"></input>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">Restaurant email</label>
                <input 
                type="text" 
                name="emailId"
                value={vendor.emailId}
                onChange={(e) => handleChange(e)}
                className="h-10 w-96 border mt-2 px-2 py-2"></input>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">Cuisine Type</label>
                <input 
                type="text" 
                name="cuisine"
                value={vendor.cuisine}
                onChange={(e) => handleChange(e)}
                className="h-10 w-96 border mt-2 px-2 py-2"></input>
            </div>
            <div className="file-card">
                <div className="file-inputs items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">Food and Saftey License</label>
                <input 
                type="file" 
                accept="application/pdf, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                onChange={(e) => handleFile(e)}
                className="py-2"/>

            </div>

            <p className=" text-gray-600 text-sm font-normal ">Supported files</p>
            <p className="info">DOC, PDF</p>

            </div>
            <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">

                <button onClick={saveVendor} className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6">
                    Save
                    </button>
                <button onClick={reset} className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6">
                    Clear
                    </button>
            </div>
        </div>
    </div>
  )
}

export default AddVendor