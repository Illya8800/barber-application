import styled from "styled-components";
import {Link} from "react-router-dom";

export const AddImageButton = styled.button`
  width: 100%;
  height: 35px;
  margin: 5px 0 15px 0;
  border-radius: 15px;
  font-size: 25px;
  background: #818181;
  white-space: nowrap;
  outline: none;
  border: none;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  text-decoration: none;

  &:hover {
    transition: all 0.2s ease-in-out;
    background: #fff;
    color: #010606;
  }
`;

export const DeleteImageButton = styled.button`
  padding: 5px;
  &:hover {
    transition: all 0.2s ease-in-out;
    color: rgba(255, 33, 131, 0.92);
  }
`;

export const ButtonGroupContainer = styled.div`
  display: flex;
  
  button, a {
    color: white;
    background-color: #000;
    border: none;
    white-space: nowrap;
    text-decoration: none;
    cursor: pointer;
    transition: all 0.2s ease-in-out;
    text-align: center;

    &:hover {
      transition: all 0.2s ease-in-out;
    }
    
    &:not(:last-child) {
      width: 100%;
    }

    &:first-child {
      border-bottom-left-radius: 15px;
    }

    &:last-child {
      border-bottom-right-radius: 15px;
    }
  }
`;

export const TextContainer = styled.div`
  display: flex;
  color: white;
  background-color: #000;
  border: none;
  white-space: nowrap;
  text-decoration: none;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  text-align: center;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  align-content: center;
  justify-content: center;
  align-items: center;
`;


export const Text = styled.div`
  text-align: center;
  padding: 8px;
  font-size: 18px;
  height: 43px;

  &:hover {
    color: aqua;
    transition: all 0.2s ease-in-out;
  }
`;


export const EditImageLink = styled(Link)`
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  padding: 5px;
  
  &:hover {
    color: rgba(36, 255, 41, 0.92);
  }
`;