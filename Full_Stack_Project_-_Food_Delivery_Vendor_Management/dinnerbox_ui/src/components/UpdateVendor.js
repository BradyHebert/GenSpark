import React, {useEffect, useState} from 'react';
import { Navigate, useLocation, useNavigate, useParams } from 'react-router-dom';
import MenuService from '../services/MenuService';
import Item from './Item';

const UpdateVendor = () => {

    const {id} = useParams();
    
    const navigate =useNavigate();
    const [item, setItem] = useState({
        id: id,
        item:"",
        desc:"",
        price:"",
    });

    const handleChange = (e) => {
        const value = e.target.value;
        setItem({...item, [e.target.name] : value});
    }


    const addItem = (e) => {
        e.preventDefault();
        MenuService.saveItem(item).then((response) => {navigate("/vendorList")}).catch((error) => {console.log(error);})
    };

    const [loading, setLoading] = useState(true);
    const [items, setItems] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try{
                const response = await MenuService.getItem(id);
                setItems(response.data);
            } catch (error){
                console.log(error);
            }

            setLoading(false);
        };
        fetchData();
    }, []);

    const deleteItem = (e, id) => {
        e.preventDefault();
        MenuService.deleteItem(id).then((res) => {
            if(items){
                setItems((prevElement) => {
                    return prevElement.filter((item) => item.id !== id);
                })
            }
        });
    }

  return (
    <>
    <div className="flex max-w-2xl mx-auto shadow border-b">
        <div className="px-8 py-8">
            <div className="font-thin text-2xl tracking-wider">
                <h1>{item.id}</h1>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">Menu Item</label>
                <input 
                type="text" 
                name="item"
                value={item.item}
                onChange={(e) => handleChange(e)}
                className="h-10 w-96 border mt-2 px-2 py-2"></input>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">Description</label>
                <input 
                type="text" 
                name="desc"
                value={item.desc}
                onChange={(e) => handleChange(e)}
                className="h-10 w-96 border mt-2 px-2 py-2"></input>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">Price</label>
                <input 
                type="text" 
                name="price"
                value={item.price}
                onChange={(e) => handleChange(e)}
                className="h-10 w-96 border mt-2 px-2 py-2"></input>
            </div>
            <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
                <button onClick={addItem} 
                className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6">
                    Update
                    </button>
                <button onClick={() => navigate("/vendorList")} 
                className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6">
                    Cancel
                    </button>
            </div>
        </div>
        
    </div>
    <div className="container mx-auto my-8">
            <div className="flex shadow border-b">
                <table className="min-w-full">
                    <thead className="bg-gray-50">
                        <tr>
                            <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">Item</th>

                            <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">Decription</th>
                            <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">Price</th>
                            <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">Actions</th>
                        </tr>
                    </thead>
                    {!loading && (
                    <tbody className="bg-white">
                        {items.map((item) => (
                            <Item 
                            item = {item}
                            deleteItem={deleteItem}
                            key={item.id}>
                            </Item>
                        ))}
                    </tbody>
                    )}
                </table>
            </div>
        </div>
    </>
  )
}

export default UpdateVendor