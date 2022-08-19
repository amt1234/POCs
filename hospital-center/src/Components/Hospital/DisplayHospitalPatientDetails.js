import { Card, CardBody, CardText, CardSubtitle } from "reactstrap";
import Patient from "../Patient/Patient";
import { toast } from "react-toastify";
import base_url from "../../API/Config";
import axios from "axios";

export default function DisplayHospitalPatientDetails(props) {

  const deleteHospital = (id) =>{
    console.log(id);
    axios.delete(`${base_url}/hospital/delete/${id}`).then(
      (response) => {
        console.log(response.data);
        toast.success(response.data);
      },
      (error) => {
        //error
        console.log(error);
        toast.error(error.response.data);
      }
    );
  }
  return (
    <Card>
      <CardBody>
        <CardSubtitle className="font-weight-bold">
          <b>Hospital Details :</b>
        </CardSubtitle>
        <CardText>
          <div className="row">
            <div className="col-8">
              Hospital Name: {props.data.hospital.hospitalName}
              <br />
              Hospital Id : {props.data.hospital.hospitalId}
              <br />
              Hospital Adress: {props.data.hospital.hospitalAddress}
              <br />
            </div>
            <div className="col-3">
              <button type="button" className="btn btn-danger" onClick={() => {deleteHospital(props.data.hospital.hospitalId)}}>
                Delete
              </button>
            </div>
          </div>
          <div>
            <b>Patient registered to this hospitals:</b>
            {props.data.patients.map((patient, i) => (
              <Patient patient={patient} key={i} />
            ))}
          </div>
        </CardText>
      </CardBody>
    </Card>
  );
}
