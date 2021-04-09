<?php

require_once ('Connector.php');
class DBConnector implements Connector {
    private string $servername = 'localhost';
    private string $username =  'root';
    private string $password = '';
    function __construct(){}
    function init($server, $user, $password){
        $this->username = $user;
        $this->password = $password;
        $this->servername = $server;
    }
    public function connect(): PDO|string
    {
        try {
            $conn = new PDO("mysql:host=$this->servername; dbname=doc_manager", $this->username, $this->password);
            $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            echo "Connected successfully;";
            return $conn;
        } catch(PDOException $e) {
            return "Connection failed: " . $e->getMessage();
        }
    }
    function __toString(){
        return "DBConnector";
    }
}

