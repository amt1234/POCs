import { useEffect } from "react";
import { Card } from "reactstrap";
import AddPatient from "./Patient/AddPatient";

export default function CardTempate(props) {
  useEffect(() => {
    <h1>Hospital Names</h1>
  }, []);
  return (
    <div className="card" style={{width: 345}}>
    <ul className="list-group list-group-flush">
    <li className="list-group-item"><h5>{props.hospital}</h5></li>
  </ul>
</div>
 
  );
}
