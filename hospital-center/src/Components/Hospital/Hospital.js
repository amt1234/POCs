import { Card, CardBody, CardText, CardSubtitle } from "reactstrap";

export default function Hospital(props){
    return(
        <Card>
          <CardBody>
            <CardSubtitle className="font-weight-bold">
            Hospital Name: {props.hospital.hospitalName}
            </CardSubtitle>
            <CardText>
            Hospital Id : {props.hospital.hospitalId}
              <br />
              Hospital Adress: {props.hospital.hospitalAddress}
            </CardText>
          </CardBody>
        </Card>
    )
}