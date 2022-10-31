import React from 'react'
import { useNavigate } from 'react-router-dom';

const Item = ({item, deleteItem}) => {

    const navigate = useNavigate();


  return (
    <tr  key={item.id}>
                    <td className="text-left px-6 pv-4 whitespace-nowrap">
                        <div className="text-sm text-gray-500">{item.name}</div>
                    </td>

                    <td className="text-left px-6 pv-4 whitespace-nowrap">
                        <div className="text-sm text-gray-500">{item.desc}</div>
                    </td>
                    <td className="text-left px-6 pv-4 whitespace-nowrap">
                        <div className="text-sm text-gray-500">{item.price}</div>
                    </td>

                    <td className="text-right px-6 pv-4 whitespace-nowrap font-medium text-sm">

                        <a 
                        onClick={(e, id) => deleteItem(e, item.id)}
                        className="text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer">
                            Delete
                            </a>
                    </td>
                </tr>
  )
}

export default Item