<?php

require_once ('BaseDTO.php');
interface CRUDManager
{
    function create(BaseDTO $entity);
    function read();
    function update(BaseDTO $entity);
    function delete($id);
    function isStored(BaseDTO $entity);
}