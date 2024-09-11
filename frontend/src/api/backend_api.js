import axios from "axios";

const backend_api = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    headers: {"Content-Type": "application/json"}
})

export default backend_api;
