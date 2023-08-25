import styled from "styled-components";

export const GalleryContainer = styled.div`
  display: flex;
  box-sizing: border-box;
  flex-wrap: wrap;
  justify-content: initial;
`;

export const ImageContainer = styled.div`
  padding: 10px 5px;
  position: relative;
  text-align: center;
  transition: all 0.3s ease;
  border: 1px solid black;
  border-radius: 15px;
  margin: 5px;

  &:hover {
    background-color: rgba(121, 121, 121, 0.59);
    transition: all 0.3s ease;
  }
`;

export const Image = styled.img`
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
`;