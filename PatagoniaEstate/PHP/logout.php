<?php
session_start();

unset($_SESSION['username']);
unset($_SESSION['status']);

session_destroy();
 // Destroying All Sessions{
header('Location: ../view-php/login.php'); // Redirecting To Home Page

?>