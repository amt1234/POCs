import React from "react";
import ListGroup from "react-bootstrap/ListGroup";
import { Link } from "react-router-dom";
import "./menu.css";
import "../index.css";

function Menus() {
  return (
    <>
      <ListGroup>
        <Link to="/">
          <ListGroup.Item>Home</ListGroup.Item>
        </Link>
        <Link to="/patient/add">
          <ListGroup.Item>Add Patient</ListGroup.Item>
        </Link>
        <Link to="/hospital/add">
          <ListGroup.Item>Add Hospital</ListGroup.Item>
        </Link>
        <Link to="/patient/all">
          <ListGroup.Item>Get all patients</ListGroup.Item>
        </Link>
        <Link to="/hospital/names">
          <ListGroup.Item>Get all hospitals names</ListGroup.Item>
        </Link>
        <Link to="/patient/id">
          <ListGroup.Item>Get patient by id</ListGroup.Item>
        </Link>
        <Link to="/hospital/id">
          <ListGroup.Item>Get hospital by id</ListGroup.Item>
        </Link>
        <Link to="/hospital/hospitalDetails">
          <ListGroup.Item>Get Hospital Details</ListGroup.Item>
        </Link>
      </ListGroup>
    </>
  );
}

export default Menus;
