import axios from "axios";
const ELECTRIC_API_URL = "http://localhost:8080/public-api/v1.0.0/electric-water"

class VehicleService{
    getAllElectricWater(){
        return axios.get(ELECTRIC_API_URL + '/list');
    }
    createElectricWater(electric){
        return axios.post(ELECTRIC_API_URL + '/create', electric)
    }

}

export default new VehicleService();