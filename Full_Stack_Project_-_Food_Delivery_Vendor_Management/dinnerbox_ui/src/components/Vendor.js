import React from 'react'
import { useNavigate } from 'react-router-dom';

const Vendor = ({vendor, deleteVendor}) => {

    const navigate = useNavigate();

    const menu = (e, id) => {
        e.preventDefault();
        navigate('/addMenu/' + id,{state:{id}});


    }

  return (
    <tr  key={vendor.id}>
                    <td className="text-left px-6 pv-4 whitespace-nowrap">
                        <div className="text-sm text-gray-500">{vendor.name}</div>
                    </td>
                    <td className="text-left px-6 pv-4 whitespace-nowrap">
                        <div className="text-sm text-gray-500">{vendor.emailId}</div>
                    </td>
                    <td className="text-left px-6 pv-4 whitespace-nowrap">
                        <div className="text-sm text-gray-500">{vendor.cuisine}</div>
                    </td>
                    {vendor.license != null && (
                    <td className="text-left px-6 pv-4 whitespace-nowrap">
                        <div className="text-sm text-gray-500">License Uploaded</div>
                    </td>
                    )}

                    <td className="text-right px-6 pv-4 whitespace-nowrap font-medium text-sm">
                    <a 
                        onClick={(e, id) => menu(e, vendor.id)}
                        className="text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer">
                            Menu
                            </a>
                        <a 
                        onClick={(e, id) => deleteVendor(e, vendor.id)}
                        className="text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer">
                            Delete
                            </a>
                    </td>
                </tr>
  )
}

export default Vendor