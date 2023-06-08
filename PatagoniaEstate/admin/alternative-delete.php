<?php

class Database
{
    private $con;

    public function __construct($config)
    {
        $this->con = new mysqli($config['hostname'], $config['username'], $config['password'], $config['database']);

        if ($this->con->connect_error) {
            throw new Exception("Failed to connect to MySQL: " . $this->con->connect_error);
        }
    }

    public function getConnection()
    {
        return $this->con;
    }
}

class User
{
    private $id;
    private $image;
    private $db;

    public function __construct($id, Database $db)
    {
        $this->id = $id;
        $this->db = $db;
    }

    public function loadUser()
    {
        $con = $this->db->getConnection();
        $stmt = $con->prepare("SELECT * FROM user WHERE uid = ?");
        $stmt->bind_param("i", $this->id);
        $stmt->execute();
        $result = $stmt->get_result();
        $user = $result->fetch_assoc();

        if ($user === null) {
            throw new Exception("User not found with id: {$this->id}");
        }

        $this->image = $user["uimage"];
    }

    public function deleteImage()
    {
        if (unlink('user/'.$this->image)) {
            return true;
        } else {
            throw new Exception("Image could not be deleted");
        }
    }

    public function deleteUser()
    {
        $con = $this->db->getConnection();
        $stmt = $con->prepare("DELETE FROM user WHERE uid = ?");
        $stmt->bind_param("i", $this->id);
        $result = $stmt->execute();

        if ($result === false) {
            throw new Exception("Could not delete user");
        }
    }
}

class Alert
{
    public static function success($message)
    {
        return "<p class='alert alert-success'>{$message}</p>";
    }

    public static function warning($message)
    {
        return "<p class='alert alert-warning'>{$message}</p>";
    }
}

try {
    $config = include("config.php");
    $database = new Database($config);
    $uid = $_GET['id'];
    $user = new User($uid, $database);
    $user->loadUser();
    $user->deleteImage();
    $user->deleteUser();
    $msg = Alert::success("Agent Deleted");
} catch (Exception $e) {
    $msg = Alert::warning($e->getMessage());
}

header("Location:useragent.php?msg=$msg");
?>
