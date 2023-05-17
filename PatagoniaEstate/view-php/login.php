<?php
// check if user is logged in by checking if session variable is set
session_start();
if (isset($_SESSION['username'])) {
    if ($_SESSION['status'] == "ADMIN")
        header('Location: ../view-php/admin.php');
    else
        header('Location: ../view-php/client.php');
}

error_reporting(0);
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/css/login.css">
    <title>Login</title>
</head>

<body>
    <div class="center">
        <h1>Login</h1>
        <form method="post">
            <div class="txt_field">
                <input type="text" name="username" required>
                <span></span>
                <label>Username</label>
            </div>
            <div class="txt_field">
                <input type="password" name="password" required>
                <span></span>
                <label>Password</label>
            </div>
            <div class="pass">Forgot Password?</div>
            <input type="submit" name="login" value="Login">
            <div class="signup_link">
                Not a member? <a href="signup.php">Signup</a>
            </div>
        </form>
    </div>

    <?php
    if (isset($_POST["login"])) {
        $data = array(
            "username" => $_POST['username'],
            "password" => $_POST['password']
        );

        $url = "http://localhost:8449/auth/authenticate";

        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_POST, true);
        curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
        curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

        // Execute the request and fetch the response
        $response = curl_exec($curl);
        if ($response === false) {
            $error = curl_error($curl);
            echo "cURL Error: " . $error;
        } else {
            // Decode the JSON response into an associative array
            $responseData = json_decode($response, true);

            // Check if authentication was successful
            if (isset($responseData['status']) && $responseData['status'] === true) {
                // Authentication successful
                session_start();
                $_SESSION['username'] = $_POST['username'];
                $_SESSION['status'] = $responseData['content'][0]['status'];
                $_SESSION['token'] = $responseData['content'][0]['token']; 
                $token = $responseData['content'][0]['token'];
                header("Location: ../view-php/admin.php");
                exit();
            } else {
                // Authentication failed
                echo "Username or password is incorrect";
            }
        }

        // Close cURL resource
        curl_close($curl);
    }
    ?>
</body>

</html>
