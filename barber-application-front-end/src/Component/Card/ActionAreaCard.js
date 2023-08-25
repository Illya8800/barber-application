import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import noImage from "../../images/add_image.png";

const ActionAreaCard = ({imageDto}) => {
    return (
        <Card sx={{width: 151, height: 221, borderRadius: 5}}>
            <CardActionArea>
                <CardMedia
                    component="img"
                    width={139}
                    height={139}
                    image={noImage}
                    alt="txt"
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                        Lizard
                    </Typography>
                    <Typography variant="body2" color="text.secondary">dwdw
                    </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    );
}

export default ActionAreaCard;
