<?php
    require_once ('CRUDManager.php');
    require_once ('DBConnector.php');
    class ManagerImpl implements CRUDManager
    {
        private string|PDO $conn;
        private string $entityName;
        function __construct($entityName)
        {
            $this->entityName =$entityName;
            $driver = new DBConnector();
            $this->conn = $driver->connect();
        }

        function create($entity)
        {
            $command = "INSERT INTO " . $this->entityName . " VALUES " . $entity->addFormat();
            $data = $this->conn->prepare($command);
            $data->execute();
            echo "Succesfully added entity ".$entity.";";
        }

        function read(): array
        {
            $command = "SELECT * FROM ".$this->entityName;
            $data = $this->conn->query($command);
            return $data->fetchAll();
        }

        function update($entity)
        {
            $command = "UPDATE ".$this->entityName." SET ".$entity->updateFromat()." WHERE id=".$entity->getId().";";
            $data = $this->conn->prepare($command);
            $data->execute();
            echo "Succesfully updated ".$entity;
        }

        function delete($id)
        {
            $command = "DELETE FROM ".$this->entityName." WHERE id=".$id.";";
            $data = $this->conn->prepare($command);
            $data->execute();
            echo "Succesfully deleted ".$this->entityName." with id ".$id;
        }

        function __toString()
        {
            return "Manager";
        }

}