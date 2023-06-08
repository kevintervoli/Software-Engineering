<?php
    session_start();
    require("config.php");

    if (!isset($_SESSION['auser'])) {
        header("location:index.php");
        exit();  // It's good practice to stop script execution after redirect.
    }
    
    $query = mysqli_query($con, "select * from about");
    $aboutList = mysqli_fetch_all($query, MYSQLI_ASSOC);
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>LM Homes | About</title>
    
    <!-- Stylesheets -->
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/feathericon.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
    
    <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <!-- Main Wrapper -->
    <!-- Header -->
    <?php include("header.php"); ?>
    <!-- /Sidebar -->

    <!-- Page Wrapper -->
    <div class="page-wrapper">
        <div class="content container-fluid">
            <!-- Page Header -->
            <?php include('page-header.php'); ?>
            <!-- /Page Header -->
            
            <div class="row">
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">List Of About</h4>
                            <?php 
                                if (isset($_GET['msg'])) { 
                                    echo $_GET['msg']; 
                                }
                            ?>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-stripped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Title</th>
                                            <th>Content</th>
                                            <th>Image</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php foreach ($aboutList as $index => $about) : ?>
                                            <tr>
                                                <td><?php echo $index + 1; ?></td>
                                                <td><?php echo $about['title']; ?></td>
                                                <td><?php echo $about['content']; ?></td>
                                                <td><img src="upload/<?php echo $about['image']; ?>" height="200px" width="200px"></td>
                                                <td>
                                                    <a href="aboutedit.php?id=<?php echo $about['id']; ?>">
                                                        <button class="btn btn-info">Edit</button>
                                                    </a>
                                                    <a href="aboutdelete.php?id=<?php echo $about['id']; ?>">
                                                        <button class="btn btn-danger">Delete</button>
                                                    </a>
                                                </td>
                                            </tr>
                                        <?php endforeach; ?>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>      
    </div>
    <!-- /Main Wrapper -->

    <!-- Scripts -->
    <script src="assets/js/jquery-3.2.1.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/js/script.js"></script>
</body>
</html>
