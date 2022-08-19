import React from "react";
import Navbar from "./Components/Navbar";
import AddPatient from "./Components/Patient/AddPatient";
import AddHospital from "./Components/Hospital/AddHospital";
import Patient from "./Components/Patient/AllPatients";
import { ToastContainer, toast } from "react-toastify";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Menus from "./Components/Menus";
import HospitalNames from "./Components/Hospital/HospitalNames";
import PatientById from "./Components/Patient/PatientById";
import HospitalById from "./Components/Hospital/HospitalById";
import Home from "./Components/Home";

import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";

import "./App.css";
import "react-toastify/dist/ReactToastify.css";
import { Card } from "react-bootstrap";
import HospitalDetails from "./Components/Hospital/HospitalDetails";


function App() {
  const notify = () => {
    toast.success("operation done sucessfully!", {
      //  position: top-center
    });
  };
  return (
    <>
    <Router>
      <ToastContainer />
      <Navbar />
      <Container className="mt-5 pt-4">
        <Row>
          <Col md={4}><Menus/> </Col>
          <Col md={8}>
          <Card className="p-4 justify-content-center ">
          <Routes>
              <Route path="/" element={<Home/>} exact/>
              <Route path="/patient/add" element={<AddPatient />} exact />
              <Route path="/hospital/add" element={<AddHospital />} exact/>
              <Route path="/patient/all" element={<Patient />} exact/>
              <Route path="/hospital/names" element={<HospitalNames />} exact/>
              <Route path="/patient/id" element={<PatientById />} exact/>
              <Route path="/hospital/id" element={<HospitalById />} exact/>
              <Route path="/hospital/hospitalDetails" element={<HospitalDetails />} exact/>
            </Routes>
            </Card>
          </Col>
        </Row>
      </Container>
      </Router>
    </>
  );
}

export default App;
