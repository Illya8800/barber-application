export default class ViewUrl {
    private static instance: ViewUrl;
    private constructor() {
    }

    private BARBERS: string = "/barbers";
    private CHECKS: string = "/checks";
    private CLIENTS: string = "/clients";
    private HAIRCUTS: string = "/haircuts";
    private HAIRCUT_ORDERS: string = "/orders";
    private IMAGES: string = "/images";
    private PAYMENTS: string = "/payments";

    public static getInstance(): ViewUrl {
        if (!ViewUrl.instance) {
            ViewUrl.instance = new ViewUrl();
        }
        return ViewUrl.instance;
    }

    barber = {
        mainEntityPage: (): string => `${this.BARBERS}`,
        createEntityPage: (): string => `${this.BARBERS}/new`,
        editEntityPage: (): string => `${this.BARBERS}/edit`,
    }
    check = {
        mainEntityPage: (): string => `${this.CHECKS}`,
        createEntityPage: (): string => `${this.CHECKS}/new`,
        editEntityPage: (): string => `${this.CHECKS}/edit`,
    }
    client = {
        mainEntityPage: (): string => `${this.CLIENTS}`,
        createEntityPage: (): string => `${this.CLIENTS}/new`,
        editEntityPage: (): string => `${this.CLIENTS}/edit`,
    }
    haircut = {
        mainEntityPage: (): string => `${this.HAIRCUTS}`,
        createEntityPage: (): string => `${this.HAIRCUTS}/new`,
        editEntityPage: (): string => `${this.HAIRCUTS}/edit`,
    }
    haircut_order = {
        mainEntityPage: (): string => `${this.HAIRCUT_ORDERS}`,
        createEntityPage: (): string => `${this.HAIRCUT_ORDERS}/new`,
        editEntityPage: (): string => `${this.HAIRCUT_ORDERS}/edit`,
    }
    image = {
        mainEntityPage: (): string => `${this.IMAGES}`,
        createEntityPage: (): string => `${this.IMAGES}/new`,
        editEntityPage: (): string => `${this.IMAGES}/edit`,
    }
    payment = {
        mainEntityPage: (): string => `${this.PAYMENTS}`,
        createEntityPage: (): string => `${this.PAYMENTS}/new`,
        editEntityPage: (): string => `${this.PAYMENTS}/edit`,
    }
}