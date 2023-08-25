import axios from 'axios';
import RestUrl from "../url/rest/restUrl";

class BaseService {
    static instance;

    constructor(baseURL = RestUrl.getInstance().getApiUrl()) {
        this.axiosInstance = axios.create({
            baseURL,
        });

        this.axiosInstance.interceptors.response.use(
            this.handleResponse,
            this.handleError
        );
    }

    static getInstance() {
        if (!BaseService.instance) {
            BaseService.instance = new BaseService();
        }
        return BaseService.instance;
    }

    handleResponse(response) {
        return response;
    }

    handleError(error) {
        console.log(`ERROR --> ${error}`)
        throw error;
    }

    async get(url, config){
        const axiosResponse = await this.axiosInstance.get(url, config);
        return axiosResponse.data;
    }

    async post(url, data, config) {
        return await this.axiosInstance.post(url, data, config);
    }

    async patch(url, data, config){
        return await this.axiosInstance.patch(url, data, config);
    }

    async delete(url, config){
        return await this.axiosInstance.delete(url, config);
    }
}

export default BaseService;
