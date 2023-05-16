<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/css/admin.css">
    <title>Admin Dashboard</title>
</head>

<body>
    <div class="container" id="top">
        <aside class="sidebar-wrapper">
            <div class="sidebar-header">
                <img src="../assets/images/logo-icon.png" alt="Logo">
                <h4>rocker</h4>
                <div class="close-menu">
                    <i class="fas fa-chevron-left"></i>
                </div>
            </div>
            <nav>
                <ul>
                    <li><a href="#">
                            <i class="fas fa-users"></i>
                            <div class="title">customers</div>
                        </a></li>
                    <li><a href="#">
                            <i class="fas fa-tasks"></i>
                            <div class="title">Properties</div>
                        </a></li>
                    <li><a href="#">
                            <i class="far fa-user-circle"></i>
                            <div class="title">Agents</div>
                        </a></li>
                    <li><a href="#">
                            <i class="far fa-question-circle"></i>
                            <div class="title">FAQ</div>
                        </a></li>
                    <li><a href="#">
                            <i class="fas fa-headset"></i>
                            <div class="title">support</div>
                        </a></li>
                </ul>
            </nav>
        </aside>
        <main class="content">
            <header>
                <div class="header-wrapper">
                    <div class="header-left">
                        <div class="toggle-icon">
                            <i class="fas fa-bars"></i>
                        </div>
                        <i class="fas fa-search"></i>
                        <input type="text" placeholder="Search...">
                    </div>
                    <div class="header-right">
                        <div class="star-container">
                            <div class="star-left">
                                <i class="far fa-star"></i>
                                <span>star</span>
                            </div>
                            <div class="star-right">
                                327
                            </div>
                        </div>
                        <img src="../assets/images/avatar-2.png" alt="avatar">
                    </div>
                </div>
            </header>
            <section class="main">
                <div class="box box-1">
                    <div class="box-left">
                        <h4>Congratulations <?php echo $_SESSION['username']; ?>!</h4>
                        <p>You have done 72% more sales today. Check your new badge in your profile.</p>
                    </div>
                </div>
                <div class="box box-2">
                    <div class="box-details">
                        <h4>54</h4>
                        <span>customers</span>
                    </div>
                    <div class="box-icon">
                        <i class="fas fa-users"></i>
                    </div>
                </div>
                <div class="box box-3">
                    <div class="box-details">
                        <h4>79</h4>
                        <span>projects</span>
                    </div>
                    <div class="box-icon">
                        <i class="fas fa-tasks"></i>
                    </div>
                </div>
                <div class="box box-4">
                    <div class="box-details">
                        <h4>124</h4>
                        <span>orders</span>
                    </div>
                    <div class="box-icon">
                        <i class="fas fa-shopping-bag"></i>
                    </div>
                </div>
                <div class="box box-5">
                    <div class="box-details">
                        <h4>$6k</h4>
                        <span>income</span>
                    </div>
                    <div class="box-icon">
                        <i class="fas fa-wallet"></i>
                    </div>
                </div>
                <div class="box box-6">
                    <div class="box-header">
                        <h4>recent orders</h4>
                        <a href="#">see all</a>
                    </div>
                    <div class="box-body">
                        <table>
                            <thead>
                                <tr>
                                    <th>product</th>
                                    <th>product id</th>
                                    <th>status</th>
                                    <th>amount</th>
                                    <th>date</th>
                                    <th>shipping</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Iphone 5</td>
                                    <td>#9405822</td>
                                    <td><span class="badge badge-1">paid</span></td>
                                    <td>$1250.00</td>
                                    <td>03 Feb 2020</td>
                                    <td>
                                        <div class="progress">
                                            <div class="progress-bar bar-1" style="width: 100%;"></div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Earphone GL</td>
                                    <td>#8304620</td>
                                    <td><span class="badge badge-2">pending</span></td>
                                    <td>$1500.00</td>
                                    <td>05 Feb 2020</td>
                                    <td>
                                        <div class="progress">
                                            <div class="progress-bar bar-2" style="width: 60%;"></div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>HD Hand Camera</td>
                                    <td>#4736890</td>
                                    <td><span class="badge badge-3">failled</span></td>
                                    <td>$1400.00</td>
                                    <td>06 Feb 2020</td>
                                    <td>
                                        <div class="progress">
                                            <div class="progress-bar bar-3" style="width: 70%;"></div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Clasic Shoes</td>
                                    <td>#8543765</td>
                                    <td><span class="badge badge-1">paid</span></td>
                                    <td>$1200.00</td>
                                    <td>14 Feb 2020</td>
                                    <td>
                                        <div class="progress">
                                            <div class="progress-bar bar-1" style="width:100%;"></div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Sitting Chair</td>
                                    <td>#9629240</td>
                                    <td><span class="badge badge-2">pending</span></td>
                                    <td>$1500.00</td>
                                    <td>18 Feb 2020</td>
                                    <td>
                                        <div class="progress">
                                            <div class="progress-bar bar-2" style="width: 60%;"></div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Hand Watch</td>
                                    <td>#8506790</td>
                                    <td><span class="badge badge-3">failled</span></td>
                                    <td>$1800.00</td>
                                    <td>21 Feb 2020</td>
                                    <td>
                                        <div class="progress">
                                            <div class="progress-bar bar-3" style="width:70% ;"></div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="box box-7">
                    <div class="box-header">
                        <h4>new customers</h4>
                        <a href="#">see all</a>
                    </div>
                    <div class="box-container">
                        <div class="customer">
                            <img src="img/avatar-2.png" alt="avatar">
                            <div class="info">
                                <h5>pauline seitz</h5>
                            </div>
                            <div class="contacts">
                                <i class="fas fa-user-circle"></i>
                                <i class="far fa-comment-alt"></i>
                                <i class="fas fa-phone-alt"></i>
                            </div>
                        </div>
                        <div class="customer">
                            <img src="img/avatar-2.png" alt="avatar">
                            <div class="info">
                                <h5>pauline seitz</h5>
                            </div>
                            <div class="contacts">
                                <i class="fas fa-user-circle"></i>
                                <i class="far fa-comment-alt"></i>
                                <i class="fas fa-phone-alt"></i>
                            </div>
                        </div>
                        <div class="customer">
                            <img src="img/avatar-2.png" alt="avatar">
                            <div class="info">
                                <h5>pauline seitz</h5>
                            </div>
                            <div class="contacts">
                                <i class="fas fa-user-circle"></i>
                                <i class="far fa-comment-alt"></i>
                                <i class="fas fa-phone-alt"></i>
                            </div>
                        </div>
                        <div class="customer">
                            <img src="img/avatar-2.png" alt="avatar">
                            <div class="info">
                                <h5>pauline seitz</h5>
                            </div>
                            <div class="contacts">
                                <i class="fas fa-user-circle"></i>
                                <i class="far fa-comment-alt"></i>
                                <i class="fas fa-phone-alt"></i>
                            </div>
                        </div>
                        <div class="customer">
                            <img src="img/avatar-2.png" alt="avatar">
                            <div class="info">
                                <h5>pauline seitz</h5>
                            </div>
                            <div class="contacts">
                                <i class="fas fa-user-circle"></i>
                                <i class="far fa-comment-alt"></i>
                                <i class="fas fa-phone-alt"></i>
                            </div>
                        </div>
                        <div class="customer">
                            <img src="img/avatar-2.png" alt="avatar">
                            <div class="info">
                                <h5>pauline seitz</h5>
                            </div>
                            <div class="contacts">
                                <i class="fas fa-user-circle"></i>
                                <i class="far fa-comment-alt"></i>
                                <i class="fas fa-phone-alt"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box box-8">
                    <div class="box-left">
                        <h4>profile report</h4>
                        <span class="year">year 2021</span>
                        <span class="percentage">^68.2%</span>
                        <h3>$84,686k</h3>
                    </div>
                    <div class="box-right">
                        <i class="fas fa-chart-line"></i>
                    </div>
                </div>
                <div class="box box-9">
                    <div class="box-header">
                        <h4>
                            Orders Summary
                        </h4>
                    </div>
                    <div class="circle">
                        <div class="inner-circle">Weekly</div>
                    </div>
                    <div class="box-bottom">
                        <div class="list">
                            <span>completed</span>
                            <span>25</span>
                        </div>
                        <div class="list">
                            <span>pending</span>
                            <span>10</span>
                        </div>
                        <div class="list">
                            <span>process</span>
                            <span>65</span>
                        </div>
                        <div class="list">
                            <span>failled</span>
                            <span>5</span>
                        </div>
                    </div>
                </div>
                <div class="box box-10">
                    <div class="box-header">
                        <h4>last projects</h4>
                    </div>
                    <div class="box-body">
                        <div class="project">
                            <div class="project-left">
                                <h5>project 1</h5>
                                <span>department 1</span>
                            </div>
                            <div class="project-right">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                        <div class="project">
                            <div class="project-left">
                                <h5>project 2</h5>
                                <span>department 2</span>
                            </div>
                            <div class="project-right">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                        <div class="project">
                            <div class="project-left">
                                <h5>project 3</h5>
                                <span>department 3</span>
                            </div>
                            <div class="project-right">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                        <div class="project">
                            <div class="project-left">
                                <h5>project 4</h5>
                                <span>department 4</span>
                            </div>
                            <div class="project-right">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                        <div class="project">
                            <div class="project-left">
                                <h5>project 5</h5>
                                <span>department 5</span>
                            </div>
                            <div class="project-right">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                        <div class="project">
                            <div class="project-left">
                                <h5>project 6</h5>
                                <span>department 6</span>
                            </div>
                            <div class="project-right">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                        <div class="project">
                            <div class="project-left">
                                <h5>project 7</h5>
                                <span>department 7</span>
                            </div>
                            <div class="project-right">
                                <i class="far fa-eye"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <footer>
                <div class="copyright">
                    copyright &copy; 2022. all right reserved.
                </div>
            </footer>
        </main>
    </div>
    <div class="switcher-container">
        <div class="switcher-icon">
            <i class="fas fa-cog"></i>
        </div>
        <div class="switcher-close">
            <i class="fas fa-times"></i>
        </div>
        <div class="switcher-header">
            <h3>theme customizer</h3>
            <h4>theme styles</h4>
        </div>
        <div class="switcher-body">
            <ul>
                <li data-color="#f7f7f7" class="active"></li>
                <li data-color="#212529"></li>
            </ul>
        </div>
    </div>
    <a href="#top" class="scroll-top">
        <i class="fas fa-arrow-up"></i>
    </a>
    <script src="./assets/js/admin.js"></script>
    <script src="https://kit.fontawesome.com/9e5ba2e3f5.js" crossorigin="anonymous"></script>
</body>

</html>