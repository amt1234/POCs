import axios from "axios";
import React, { useState } from "react";
import base_url from "../../API/Config";
import { toast } from "react-toastify";

export default function AddHospital() {
  const [hospital, setHospital] = useState({});

  //on form submition
  const handleForm = (e) => {
    console.log(hospital);
    postDataToServer(hospital);
    e.preventDefault();
  };

  //creating function to post data on server
  const postDataToServer = (data) => {
    axios.post(`${base_url}/hospital/add`, data).then(
      (response) => {
        console.log(response);
        toast.success("hospital added sucessfully!");
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
      <h3>Add Hospital Details</h3>
      <div className="mb-3 col-5">
        <label htmlFor="hospitalId" className="form-label">
          Hospital Id :
        </label>
        <input
          type="number"
          className="form-control"
          id="hospitalId"
          placeholder="Enter your hospital Id"
          onChange={(e) => {
            setHospital({ ...hospital, hospitalId: e.target.value });
          }}
          required
        />
      </div>
      <div className="mb-3 col-5">
        <label htmlFor="hospitalName" className="form-label">
          Hospital Name :
        </label>
        <input
          type="text"
          className="form-control"
          id="hospitalName"
          aria-describedby="hospitalName"
          placeholder="Enter hospital Name"
          onChange={(e) => {
            setHospital({ ...hospital, hospitalName: e.target.value });
          }}
          required
        />
      </div>
      <div className="mb-3 col-5">
        <label htmlFor="hospitalAddress" className="form-label">
          Hospital Address :
        </label>
        <input
          type="text"
          className="form-control"
          id="hospitalAddress"
          placeholder="Enter hospital address"
          onChange={(e) => {
            setHospital({ ...hospital, hospitalAddress: e.target.value });
          }}
          required
        />
      </div>

      <div>
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
