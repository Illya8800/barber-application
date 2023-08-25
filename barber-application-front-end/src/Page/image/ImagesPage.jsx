import React, {useEffect, useState} from 'react';
// import {Container} from "../PageElements";
import LoadWrapper from "../../Component/Loader/LoadWrapper";
import {useDispatch, useSelector} from "react-redux";
import {setIsShowModalPopupAddImage} from "../../redux/image/action";
import {Card, CardActionArea, CardContent, CardMedia, Grid, Typography} from "@mui/material";
import {Container} from "../PageElements";
import ImageService from "../../services/ImageService";

function ImagesPage() {
    const dispatch = useDispatch();
    const {isOpenAddPopup} = useSelector(state => state.image);


    const [imageList, setImageList] = useState([]);
    // const [noImage, setNoImage] = useState({name: "a", image:{}});
    const [isLoading, setIsLoading] = useState(true);


    useEffect(() => {
        const call = async () => {
            const barberService = ImageService.getInstance();
            const images = await barberService.getAllImages();


            console.log(images.content)

            // const noImage = await basicService.get("http://localhost:8080/images/name/no_image");
            // setNoImage(noImage);
            setImageList(images.content);
            setIsLoading(false);
        };
        call();
        }, []);

    useEffect(() => {
        dispatch(setIsShowModalPopupAddImage(true))
    }, [isOpenAddPopup])

    return (
        <Container>
            <LoadWrapper isLoading={isLoading}>
                <Grid container spacing={2}>
                    {imageList?.map(imageDto => (
                        <Card key={imageDto.id}>
                            <CardActionArea>
                                <CardMedia
                                    component="img"
                                    width={139}
                                    height={139}
                                    image={`data:image/jpeg;base64,${imageDto.image.bytes}`}
                                    alt="txt"
                                />
                                <CardContent>
                                    <Typography gutterBottom variant="h5" component="div">
                                        Lizard
                                    </Typography>
                                    <Typography variant="body2" color="text.secondary">
                                        dwdw
                                    </Typography>
                                </CardContent>
                            </CardActionArea>
                        </Card>
                    ))}


                    {/*<Pagination count={10} color="secondary"/>*/}


                    {/*<ImagePopup isShow={isOpenAddPopup}/>*/}
                </Grid>
            </LoadWrapper>

        </Container>
    );
}

export default ImagesPage;