<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en">


<head>
    <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>

<body>
    <form id="userForm" method="GET" action=".">
        <h1>User Table</h1>
        Click here to <a href="../view-php/admin.php">go back</a>
        <br>
        <input class="btn btn-primary btn-sm" type="submit" name="fillButton" value="FILL TABLE" />
        <input class="btn btn-primary btn-sm" type="submit" name="add" value="ADD" />
        <input type="text" name="id" placeholder="ID" />
        <input type="text" name="username" placeholder="USERNAME" />
        <input class='btn btn-danger btn-sm' type='submit' name='deleteButton' value='Delete' />
        <br>
        <br>
    </form>
    <form id="part2" method="POST">
        <input type="number" name="id_2" placeholder="ID" />
        <input type="text" name="name" placeholder="Name" />
        <input type="text" name="surname" placeholder="Surname" />
        <input type="number" name="age" placeholder="Age" />
        <input type="text" name="email" placeholder="Email" pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$" />
        <input type="text" name="address" placeholder="Address" />
        <input type="text" name="username_2" placeholder="Username" />
        <input type="text" name="password" placeholder="Password" />
        <input type="number" name="status" placeholder="Status" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" />
        <input class='btn btn-primary btn-sm' type='submit' name='edit' value='EDIT' />
        <a class='btn btn-primary btn-sm' href="../index.php">GO HOME</a>
        <br>
        <br>
    </form>
    <section>
        <table class="table">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Username</th>
                <th>Age</th>
                <th>Email</th>
                <th>Address</th>
                <th>Status</th>
                <th>Credit Score</th>
            </tr>
            <!-- <?php
            
            // if (isset($_POST['fillButton'])) {
            //     // print that button is clicked
            //     echo "<h1>Button is clicked</h1>";
            //     $url = "http://localhost:8449/secured/getAllUsers";
            //     $curl = curl_init();
            //     curl_setopt($curl, CURLOPT_URL, $url);
            //     curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
            //     curl_setopt($curl, CURLOPT_POST, true);
            //     curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
            //     curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
            //     $response = curl_exec($curl);
            //     $result = json_decode($response, true);
            //     var_dump($result);
            //     curl_close($curl);
            //     // foreach ($result as $key => $value) {
            //     //     echo "<tr>";
            //     //     echo "<td>" . $value['id'] . "</td>";
            //     //     echo "<td>" . $value['name'] . "</td>";
            //     //     echo "<td>" . $value['surname'] . "</td>";
            //     //     echo "<td>" . $value['age'] . "</td>";
            //     //     echo "<td>" . $value['email'] . "</td>";
            //     //     echo "<td>" . $value['address'] . "</td>";
            //     //     echo "<td>" . $value['username'] . "</td>";
            //     //     echo "<td>" . $value['password'] . "</td>";
            //     //     echo "<td>" . $value['status'] . "</td>";
            //     //     echo "</tr>";
            //     // }
            // }
            // }
            ?> -->
        </table>
    </section>
    <script src="../assets/js/admin-api.js"></script>
</body>

</html>