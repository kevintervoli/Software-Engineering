<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
    <h1>New User</h1> 
    <form method="post">
        <div class="row mb-1">
            <label for="name" class="col-sm-1 col-form-label">Name</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="age" class="col-sm-1 col-form-label">Age</label>
            <div class="col-sm-6">
                <input type="number" class="form-control" id="age" name="age" placeholder="Age" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="email" class="col-sm-1 col-form-label">Email</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="email" name="email" placeholder="Email" required pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
            </div>
        </div>
        <div class="row mb-3">
            <label for="address" class="col-sm-1 col-form-label">Address</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="address" name="address" placeholder="Address" required >
            </div>
        </div>
        <div class="row mb-3">
            <label for="username" class="col-sm-1 col-form-label">Username</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="password" class="col-sm-1 col-form-label">Password</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
        </div>
        <div class="row mb-3">
            <label for="confirm_password" class="col-sm-1 col-form-label">User Status</label>
            <div class="col-sm-6">
                <input type="number" class="form-control" id="status" name="status" placeholder="User Status" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="credit_score" class="col-sm-1 col-form-label">Credit Score</label>
            <div class="col-sm-6">
                <input type="number" class="form-control" id="creditscore" name="creditscore" placeholder="Credit Score" required>
            </div>
        </div>
        <br>
        <div class="row mb-3">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <input type="submit" class="btn btn-primary" name="add" value="Add User">
            </div>
        </div>
    </form>
    <?php
    // add user
    if (isset($_POST['add'])) {
        // Retrieve the token from the session
        if (!isset($_SESSION['token'])) {
            echo "Token is missing. Please authenticate first.";
            exit();
        }
        
        $token = $_SESSION['token'];

        // Rest of your code for adding the user
        $data = array(
            "name" => $_POST['name'],
            "age" => $_POST['age'],
            "email" => $_POST['email'],
            "address" => $_POST['address'],
            "username" => $_POST['username'],
            "password" => $_POST['password'],
            "status" => $_POST['status'],
            "creditScore" => strval($_POST['creditscore']),
            "enabled" => true
        );

        $url = "http://localhost:8449/secured/createUser";

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_POST, true);
        curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: application/json', 'Authorization: Bearer ' . $token));

        // Execute the request and fetch the response
         $response = curl_exec($curl);
            if ($response === false) {
                $error = curl_error($curl);
                echo "cURL Error: " . $error;
            } else {
                // Decode the response
                $responseData = json_decode($response, TRUE);
                // Print the date from the response
                echo "User added successfully";
            }

        // Close cURL resource
        curl_close($curl);
    }
    ?>

</body>
</html>