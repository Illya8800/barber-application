import BaseService from './BaseService';
import RestUrl from "../url/rest/restUrl";

class BarberService extends BaseService {
    static instanceBarberService;
    url;
    constructor() {
        super();
        this.url = RestUrl.getInstance()
    }
    static getInstance() {
        if (!BarberService.instanceBarberService) {
            console.log("I've created");
            BarberService.instanceBarberService = new BarberService();
        }
        return BarberService.instanceBarberService;
    }

    async getBarberById(barberId) {
        return await this.get(this.url.barber.getById(barberId));
    }

    async getAllBarbers() {
        return await this.get(this.url.barber.getAll());
    }

    async createBarber(barber) {
        return await this.post(this.url.barber.create(), {data: barber});
    }

    async updateBarber(barber) {
        return await this.patch(this.url.barber.update(), {data: barber});
    }

    async deleteBarber(barber) {
        return await this.delete(this.url.barber.delete(), {data: barber});
    }
}

export default BarberService;
