import React, { useState, useEffect } from "react";
import base_url from "../../API/Config";
import axios from "axios";
import CardTempate from "../CardTemplate";


function HospitalNames() {
  const [hospitals, setHospitals] = useState([]);

  //function to call server
  const getAllHospitalsName = () => {
    axios.get(`${base_url}/hospital/names`).then(
      (response) => {
        //success
        console.log(response);
        setHospitals(response.data);
      },
      (error) => {
        //error
        console.log(error);
      }
    );
  };

  useEffect(() => {
    getAllHospitalsName();
  }, []);

  return (
    <>
      {hospitals.length > 0
        ? hospitals.map((item, i) => <CardTempate hospital={item} key={i} />)
        : "No records to display"}
    </>
  );
}

export default HospitalNames;
