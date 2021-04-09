<?php

use JetBrains\PhpStorm\Pure;

require_once ('DocumentDTO.php');
require_once ('ClassCreator.php');
class DocumentCreator implements ClassCreator
{

    #[Pure] function create($body) : DocumentDTO
    {
        return new DocumentDTO($body['id'],
            $body['title'],
            $body['page_number'],
            $body['type'],
            $body['format'],
            $body['author_id']);
    }
}