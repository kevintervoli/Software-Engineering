<?php

class Config
{
    public static function getDbConnection()
    {
        include("config.php");
        return $con;
    }
}

class User
{
    private $id;
    private $image;

    public function __construct($id)
    {
        $this->id = $id;
    }

    public function loadUser()
    {
        $con = Config::getDbConnection();
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
        $con = Config::getDbConnection();
        $stmt = $con->prepare("DELETE FROM user WHERE uid = ?");
        $stmt->bind_param("i", $this->id);
        $result = $stmt->execute();

        if ($result === false) {
            throw new Exception("Could not delete user");
        }
    }
}

header("Location:useragent.php?msg=$msg");
?>
