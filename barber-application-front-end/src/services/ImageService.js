import RestUrl from "../url/rest/restUrl";
import BaseService from "./BaseService";

class ImageService extends BaseService {
    static instanceImageService;
    url;
    constructor() {
        super();
        this.url = RestUrl.getInstance()
    }
    static getInstance() {
        if (!ImageService.instanceImageService) {
            ImageService.instanceImageService = new ImageService();
        }
        return ImageService.instanceImageService;
    }

    async getImageById(imageId) {
        return await this.get(this.url.image.getById(imageId));
    }

    async getImageByName(imageName) {
        return await this.get(this.url.image.getByName(imageName));
    }

    async getAllImages() {
        return await this.get(this.url.image.getAll());
    }

    async createImage(image) {
        return await this.post(this.url.image.create(), {data: image});
    }

    async updateImage(image) {
        return await this.patch(this.url.image.update(), {data: image});
    }

    async deleteImage(image) {
        return await this.delete(this.url.image.delete(), {data: image});
    }

    async isUniqueImage(id, name) {
        return await this.get(this.url.image.isUniqueImage(id, name));
    }
}

export default ImageService;
