import axios from "axios";

const ITEM_API_BASE_URL = "http://localhost:8080/api/v1/item";

class MenuService{

    saveItem(item){
        return axios.post(ITEM_API_BASE_URL, item);
    }

    getItem(id){
        return axios.get(ITEM_API_BASE_URL);
    }

    deleteItem(id){
        return axios.delete(ITEM_API_BASE_URL + "/" + id);
    }

    getItemById(id){
        return axios.get(ITEM_API_BASE_URL + "/" + id);
    }


}

export default new MenuService();

