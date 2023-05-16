<?php
if (isset($_POST)) {

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
                $_SESSION['username'] = $username;
                // header("Location: ../view-php/admin.php");
                $token = $responseData['content'][0]['token'];
                var_dump($token);
            } else {
                // Authentication failed
                echo "Username or password is incorrect";
            }
        }

        // Close cURL resource
        curl_close($curl);
    }
