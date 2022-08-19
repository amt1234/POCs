import React from "react";

export default function Navbar() {
  return (
    <React.Fragment>
        <nav className="navbar navbar-expand-lg bg-dark fixed-top">
          <div className="container-fluid">
            <a className="navbar-brand text-white text-center">
              <h4>Hospital Patient Demo Application</h4>
            </a>
          </div>
        </nav>
      </React.Fragment>
  );
}
