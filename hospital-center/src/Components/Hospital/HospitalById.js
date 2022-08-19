import React, { useState} from "react";
import base_url from "../../API/Config";
import axios from "axios";
import { Container } from "react-bootstrap";
import Hospital from "./Hospital";
import { toast } from "react-toastify";

function HospitalById() {
  const [id, setId] = useState(0);
  const [hospital, seHospitalDetails] = useState([]);
  //function to call server
  const getHospitalById = async (id) => {
    await axios.get(`${base_url}/hospital/id/${id}`).then(
      (response) => {
        console.log(response.data);
        seHospitalDetails(response.data);
      },
      (error) => {
        //error
        console.log(error.response.data);
        toast.error(error.response.data);
      }
    );
  };

  const handleForm = (e) => {
    getHospitalById(id);
    e.preventDefault();
  };
  return (
    <div>
      <form onSubmit={handleForm}>
        <div className="mb-3 col-6">
          <label htmlFor="getById" className="form-label">
            hospital Id:
          </label>
          <input
            type="text"
            className="form-control"
            id="getById"
            aria-describedby="getById"
            placeholder="Enter hospital Id which you want to get"
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
            clear
          </button>
        </Container>
      </form>
      {hospital != null > 0 ? (
        <Hospital hospital={hospital} />
      ) : (
        "No records to display"
      )}
    </div>
  );
}
export default HospitalById;
