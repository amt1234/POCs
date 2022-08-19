import React, { useState, useEffect } from "react";
import base_url from "../../API/Config";
import axios from "axios";
import DataTable from "react-data-table-component";
import ReactPaginate from "react-paginate";

function Patient() {
  const [patients, setPatients] = useState([]);
  const [pageCount, setpageCount] = useState(0);
  let limit = 10;

  //function to call server
  const getAllPatients = async (page) => {
    console.log("pages ", page);
    await axios
      .get(`${base_url}/patient/all/${page.number}/${page.limit}`)
      .then(
        (response) => {
          //success
          setPatients(response.data);
          console.log("data length" + response.data.length);
          let count = Math.ceil(response.data.length / limit);
          console.log(count);
          setpageCount(20/limit);
        },
        (error) => {
          //error
          console.log(error);
        }
      );
  };

  const handlePageClick = (data) => {
    console.log("handlePageClick");
    console.log(data.selected);
    let currentPage = data.selected;
    const page = { number: currentPage, limit: 10 };
    console.log(page);
    getAllPatients(page);
  };

  useEffect(() => {
    const page = { number: 0, limit: 10 };
    getAllPatients(page);
  }, []);

  const columns = [
    {
      name: "Id",
      selector: (row) => row.patientId,
    },
    {
      name: "Name",
      selector: (row) => row.patientName,
      sortable: true,
    },
    {
      name: "Diseases",
      selector: (row) => row.patientDisease,
    },
    {
      name: "Age",
      selector: (row) => row.patientAge,
    },
    {
      name: "Contact Number",
      selector: (row) => row.contactNumber,
    },
    {
      name: "Resigter to hospital",
      selector: (row) => row.hospitalId,
    },
  ];

  return (
    <>
      <DataTable
        title={<h4>Patient List</h4>}
        columns={columns}
        //   data={patients}  //we need to pass filteredList for search func. other wise we can directly pass list here also
        data={patients}
        fixedHeader
        fixedHeaderScrollHeight="400px"
        selectableRowsHighlight
        highlightOnHover
        selectableRows
      />
      <ReactPaginate
        previousLabel={"previous"}
        nextLabel={"next"}
        breakLabel={"..."}
        pageCount={pageCount}
        marginPagesDisplayed={2}
        pageRangeDisplayed={3}
        onPageChange={handlePageClick}
        containerClassName={"pagination justify-content-center"}
        pageClassName={"page-item"}
        pageLinkClassName={"page-link"}
        previousClassName={"page-item"}
        previousLinkClassName={"page-link"}
        nextClassName={"page-item"}
        nextLinkClassName={"page-link"}
        breakClassName={"page-item"}
        breakLinkClassName={"page-link"}
        activeClassName={"active"}
      />
    </>
  );
}

export default Patient;
