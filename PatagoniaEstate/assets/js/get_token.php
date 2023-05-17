<?php
session_start();

// Check if the token exists in the session
if (isset($_SESSION['token'])) {
    $token = $_SESSION['token'];

    // Return the token as a JSON response
    header('Content-Type: application/json');
    echo json_encode(['token' => $token]);
} else {
    // Token doesn't exist in the session
    echo "Token not found";
}
?>
