async function getPropertiesAPI() {
  try {
    const response = await fetch("../assets/js/get_token.php");
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const dat = await response.json();
    const token = dat.token;
    const requestBody = {
      pagination: {
        pageSize: 5,
        pageNumber: 1,
        sortByProperty: "id",
        sortByAsc: true,
      },
      searchCriteriaList: [],
    };
    return fetch("http://localhost:8449/secured/getAllUsers", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify(requestBody),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        return data;
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  } catch (error) {
    console.error("Error:", error);
  }
}

function fillTable(tableElement, data) {
  // Clear the table
  tableElement.innerHTML = "";
  const row = document.createElement("tr");
  const id = document.createElement("td");
  id.textContent = "ID";
  row.appendChild(id);
  const name = document.createElement("td");
  name.textContent = "Name";
  row.appendChild(name);
  const username = document.createElement("td");
  username.textContent = "Username";
  row.appendChild(username);
  const age = document.createElement("td");
  age.textContent = "Age";
  row.appendChild(age);
  const email = document.createElement("td");
  email.textContent = "Email";
  row.appendChild(email);
  const address = document.createElement("td");
  address.textContent = "Address";
  row.appendChild(address);
  const status = document.createElement("td");
  status.textContent = "Status";
  row.appendChild(status);
  const creditScore = document.createElement("td");
  creditScore.textContent = "Credit Score";
  row.appendChild(creditScore);
  tableElement.appendChild(row);
  
  // Iterate over the data array
  data.forEach((item) => {
    // Create a new row
    const row = document.createElement("tr");

    // Add id
    const idCell = document.createElement("td");
    idCell.textContent = item.id;
    row.appendChild(idCell);

    // Add name
    const nameCell = document.createElement("td");
    nameCell.textContent = item.name;
    row.appendChild(nameCell);

    // Add username
    const usernameCell = document.createElement("td");
    usernameCell.textContent = item.username;
    row.appendChild(usernameCell);

    // Add age
    const ageCell = document.createElement("td");
    ageCell.textContent = item.age;
    row.appendChild(ageCell);

    // Add email
    const emailCell = document.createElement("td");
    emailCell.textContent = item.email;
    row.appendChild(emailCell);

    // Add address
    const addressCell = document.createElement("td");
    addressCell.textContent = item.address;
    row.appendChild(addressCell);

    // Add status
    const statusCell = document.createElement("td");
    statusCell.textContent = item.status;
    row.appendChild(statusCell);

    // Add credit score
    const creditScoreCell = document.createElement("td");
    creditScoreCell.textContent = item.creditScore;
    row.appendChild(creditScoreCell);

    // Append the row to the table
    tableElement.appendChild(row);
  });
}

async function deletePropertyAPI(data) {
  let url =
    "http://localhost/PatagoniaEstate/API/users?id=" +
    data["id"] +
    "&username=" +
    data["username"];
  return fetch(url, {
    method: "DELETE",
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      return data;
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}
document
  .querySelector("#userForm")
  .addEventListener("submit", async (event) => {
    event.preventDefault();
    // get the button name from event
    const buttonName = event.submitter.name;
    if (buttonName === "fillButton") {
      try {
        const formData = new FormData(event.target);
        const data = Object.fromEntries(formData.entries());
        // clear the table
        document.querySelector("tbody").innerHTML = "";
        const responseData = await getPropertiesAPI();
        console.log(responseData);
        fillTable(document.querySelector("tbody"), responseData.content);
      } catch (error) {
        console.error("Error:", error);
      }
    } else if (buttonName === "deleteButton") {
      try {
        const formData = new FormData(event.target);
        const data = Object.fromEntries(formData.entries());
        const responseData = await deletePropertyAPI(data);
        console.log(responseData);
      } catch (error) {
        console.error("Error:", error);
      }
    } else if (buttonName === "add") {
      window.location.href = "./add_user.php";
    }
  });
