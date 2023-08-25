export default class RestUrl {
    private static instance: RestUrl;

    private constructor() {
    }

    public static getInstance(): RestUrl {
        if (!RestUrl.instance) {
            RestUrl.instance = new RestUrl();
        }
        return RestUrl.instance;
    }
    private PROTOCOL = "http";
    private API_URL = `${this.PROTOCOL}://localhost:8080`;
    private BARBERS = "barbers";
    private CHECKS = "checks";
    private CLIENTS = "clients";
    private HAIRCUTS = "haircuts";
    private HAIRCUT_ORDERS = "orders";
    private IMAGES = "images";
    private PAYMENTS = "payments";
    getApiUrl = () => this.API_URL

    barber = {
        getById: (id: number): string => `${this.API_URL}/${this.BARBERS}/${id}`,
        getAll: (): string => `${this.API_URL}/${this.BARBERS}`,
        create: (): string => `${this.API_URL}/${this.BARBERS}`,
        update: (): string => `${this.API_URL}/${this.BARBERS}`,
        delete: (): string => `${this.API_URL}/${this.BARBERS}`
    };
    check = {
        getById: (id: number): string => `${this.API_URL}/${this.CHECKS}/${id}`,
        getAll: (): string => `${this.API_URL}/${this.CHECKS}`,
        create: (): string => `${this.API_URL}/${this.CHECKS}`,
        update: (): string => `${this.API_URL}/${this.CHECKS}`,
        delete: (id: number): string => `${this.API_URL}/${this.CHECKS}/${id}`
    };
    client = {
        getById: (id: number): string => `${this.API_URL}/${this.CLIENTS}/${id}`,
        getByPhoneNumber: (phoneNumber: string): string => `${this.API_URL}/${this.CLIENTS}/phone/${phoneNumber}`,
        getAll: (): string => `${this.API_URL}/${this.CLIENTS}`,
        create: (): string => `${this.API_URL}/${this.CLIENTS}`,
        update: (): string => `${this.API_URL}/${this.CLIENTS}`,
        delete: (id: number): string => `${this.API_URL}/${this.CLIENTS}/${id}`,
        isUnique: (id: number, phoneNumber: string) => `${this.API_URL}/${this.CLIENTS}/unique?id=${id}&phoneNumber=${phoneNumber}`
    };
    haircut = {
        getById: (id: number): string => `${this.API_URL}/${this.HAIRCUTS}/${id}`,
        getByName: (phoneNumber: string): string => `${this.API_URL}/${this.HAIRCUTS}/name/${phoneNumber}`,
        getAll: (): string => `${this.API_URL}/${this.HAIRCUTS}`,
        create: (): string => `${this.API_URL}/${this.HAIRCUTS}`,
        update: (): string => `${this.API_URL}/${this.HAIRCUTS}`,
        delete: (): string => `${this.API_URL}/${this.HAIRCUTS}`,
        isUnique: (id: number, name: string) => `${this.API_URL}/${this.HAIRCUTS}/unique?id=${id}&name=${name}`
    };
    order = {
        getById: (id: number): string => `${this.API_URL}/${this.HAIRCUT_ORDERS}/${id}`,
        getAll: (): string => `${this.API_URL}/${this.HAIRCUT_ORDERS}`,
        create: (): string => `${this.API_URL}/${this.HAIRCUT_ORDERS}`,
        update: (): string => `${this.API_URL}/${this.HAIRCUT_ORDERS}`,
        delete: (id: number): string => `${this.API_URL}/${this.HAIRCUT_ORDERS}/${id}`
    };
    image = {
        getById: (id: number): string => `${this.API_URL}/${this.IMAGES}/${id}`,
        getByName: (phoneNumber: string): string => `${this.API_URL}/${this.IMAGES}/name/${phoneNumber}`,
        getAll: (): string => `${this.API_URL}/${this.IMAGES}`,
        create: (): string => `${this.API_URL}/${this.IMAGES}`,
        update: (): string => `${this.API_URL}/${this.IMAGES}`,
        delete: (id: number): string => `${this.API_URL}/${this.IMAGES}/${id}`,
        isUnique: (id: number, name: string) => `${this.API_URL}/${this.IMAGES}/unique?id=${id}&name=${name}`

    };
    payment = {
        getById: (id: number): string => `${this.API_URL}/${this.PAYMENTS}/${id}`,
        getAll: (): string => `${this.API_URL}/${this.PAYMENTS}`,
        create: (): string => `${this.API_URL}/${this.PAYMENTS}`,
        update: (): string => `${this.API_URL}/${this.PAYMENTS}`,
        delete: (id: number): string => `${this.API_URL}/${this.PAYMENTS}/${id}`
    };
}