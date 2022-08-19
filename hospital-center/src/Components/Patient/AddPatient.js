import axios from "axios";
import React, { useState } from "react";
import base_url from "../../API/Config";
import { toast } from "react-toastify";
import "./Patient.css";

export default function AddPatient() {
  const [patient, setPatient] = useState({});

  //on form submition
  const handleForm = (e) => {
    console.log(patient);
    postDataToServer(patient);
    e.preventDefault();
  };

  //creating function to post data on server
  const postDataToServer = (data) => {
    axios.post(`${base_url}/patient/add`, data).then(
      (response) => {
        console.log(response);
        toast.success("Patient added sucessfully!");
        console.log("success");
      },
      (error) => {
        console.log(error);
        console.log("error");
      }
    );
  };
  return (
    <form onSubmit={handleForm}>
      <h3>Add Patient Details</h3>
      <div className="mb-3">
        <label htmlFor="patientName" className="form-label">
          Patient Name :
        </label>
        <input
          type="text"
          className="form-control"
          id="patientName"
          aria-describedby="patientName"
          placeholder="Enter Patient Name"
          onChange={(e) => {
            setPatient({ ...patient, patientName: e.target.value });
          }}
          required
        />
      </div>
      <div className="row">
        <div className="mb-3 col-6">
          <label htmlFor="patientDisease" className="form-label">
            Diseases :
          </label>
          <input
            type="text"
            className="form-control"
            id="patientDisease"
            placeholder="Enter Disease"
            onChange={(e) => {
              setPatient({ ...patient, patientDisease: e.target.value });
            }}
            required
          />
        </div>
        <div className="mb-3 col-6">
          <label htmlFor="patientAge" className="form-label">
            Age :
          </label>
          <input
            type="number"
            className="form-control"
            id="patientAge"
            placeholder="Enter your age"
            onChange={(e) => {
              setPatient({ ...patient, patientAge: e.target.value });
            }}
            required
          />
        </div>
      </div>
      <div className="row">
        <div className="mb-3 col-6">
          <label htmlFor="contactNumber" className="form-label">
            Contact Number :
          </label>
          <input
            type="text"
            className="form-control"
            id="contactNumber"
            placeholder="Enter your Contact number"
            pattern="[1-9]{1}[0-9]{9}"
            title="Enter the valid number"
            onChange={(e) => {
              setPatient({ ...patient, contactNumber: e.target.value });
            }}
            required
          />
        </div>
        <div className="mb-3 col-6">
          <label htmlFor="hospitalId" className="form-label">
            Hospital Id :
          </label>
          <select
            className="form-select"
            size="1"
            aria-label="choose hospital to register"
            id="hospitalId"
            placeholder="Enter your hospital Id"
            onChange={(e) => {
              setPatient({ ...patient, hospitalId: e.target.value });
            }}
            required
          >
            <option defaultValue>Select hospital...</option>
            <option value="1">PKC Hospital</option>
            <option value="2">Apollo Hospitals</option>
            <option value="3">LT hospital</option>
          </select>
        </div>
      </div>
      <div className="text-center">
        <button type="submit" className="btn btn-success me-4">
          Submit
        </button>
        <button type="reset" className="btn btn-warning me-4">
          Clear
        </button>
      </div>
    </form>
  );
}
