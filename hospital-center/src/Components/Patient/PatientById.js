import React, { useState, useEffect } from "react";
import base_url from "../../API/Config";
import axios from "axios";
import { Container } from "react-bootstrap";
import Patient from "./Patient";
import { toast } from "react-toastify";

function PatientById() {
  const [id, setId] = useState(0);
  const [patient, setPatientDetails] = useState([]);
  //function to call server
  const getPatientById = async (id) => {
    console.log(id);
    await axios.get(`${base_url}/patient/id/${id}`).then(
      (response) => {
        console.log(response.data);
        setPatientDetails(response.data);
        console.log("patient length", patient);
      },
      (error) => {
        //error
        console.log(error);
        toast.error(error.response.data);
      }
    );
  };
  const handleForm = (e) => {
    getPatientById(id);
    e.preventDefault();
  };
  return (
    <div>
      <form onSubmit={handleForm}>
        <div className="mb-3 col-6">
          <label htmlFor="getById" className="form-label">
            Patient Id:
          </label>
          <input
            type="text"
            className="form-control"
            id="getById"
            aria-describedby="getById"
            placeholder="Enter Patient Id which you want to get"
            onChange={(e) => {
              setId(e.target.value);
            }}
            required
          />
        </div>

        <Container>
          <button type="submit" className="btn btn-success m-3">
            Submit
          </button>
          <button type="reset" className="btn btn-warning">
            Clear
          </button>
        </Container>
      </form>
      {patient != null > 0 ? (
        <Patient patient={patient} />
      ) : (
        "No records to display"
      )}
    </div>
  );
}
export default PatientById;
