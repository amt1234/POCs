import { Card, CardBody, CardText, CardSubtitle } from "reactstrap";

export default function Patient(props){
  console.log(props);
    return(
        <Card>
          <CardBody>
            <CardSubtitle className="font-weight-bold">
             Patient Name: {props.patient.patientName}
            </CardSubtitle>
            <CardText>
            Patient Id : {props.patient.patientId}
              <br />
              Patient Disease: {props.patient.patientDisease}
              <br/>
              Patient Age: {props.patient.patientAge}
              <br/>
              Patient Contact number: {props.patient.contactNumber}
              <br/>
              Patient hospital Id:{props.patient.hospitalId}
            </CardText>
          </CardBody>
        </Card>
    )
}