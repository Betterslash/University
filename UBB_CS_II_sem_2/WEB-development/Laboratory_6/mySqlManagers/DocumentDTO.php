<?php

require_once ('BaseDTO.php');
class DocumentDTO implements BaseDTO
{
    private int $id;
    private string $title;
    private int $pageNumber;
    private string $type;
    private string $format;
    private int $authorId;

    /**
     * DocumentDTO constructor.
     * @param int $id
     * @param string $title
     * @param int $pageNumber
     * @param string $type
     * @param string $format
     */
    public function __construct(int $id, string $title, int $pageNumber, string $type, string $format, int $aid)
    {
        $this->id = $id;
        $this->title = $title;
        $this->pageNumber = $pageNumber;
        $this->type = $type;
        $this->format = $format;
        $this->authorId = $aid;
    }
    public function __toString(){
        return "Doc: " .$this->id .
            ", with title:".$this->title .
            ", having ".$this->pageNumber.
            " pages, of type/format " .$this->type ."/" .$this->format;
    }


    function getId(): int
    {
        return $this->id;
    }

    public function updateFromat(): string
    {
        return "title='".$this->title.
            "', page_number=".$this->pageNumber.
            ", type='".$this->type.
            "', format='".$this->format.
            "', author_id=".$this->authorId;
    }

    public function addFormat(): string
    {
        return "(".$this->id.", '".$this->title."', ".$this->pageNumber.", '".$this->type."', '".$this->format."', ".$this->authorId.");";
    }
}