import styled from "styled-components";

export const PopupContainer = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.8);
  top: 0;
  left: 0;
  //opacity: 0;
  //visibility: hidden;
  overflow-y: auto;
  overflow-x: hidden;
  transition: all 0.8s ease 0s;
`;

export const PopupBody = styled.div`
  min-height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 30px 10px;
`;

export const PopupContent = styled.div`
background-color: #fff;
  color: black;
  max-width: 800px;
  padding: 30px;
  position: relative;
  transition: all 0.3s ease 0s;
`;

export const PopupTitle = styled.div`
  font-size: 40px;
  margin: 0 0 1em 0;
`;

export const PopupClose = styled.div`
  position: absolute;
  right: 10px;
  top: 10px;
  color: black;
`;