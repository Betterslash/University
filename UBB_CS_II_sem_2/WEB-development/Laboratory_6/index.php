
<?php
require_once('mySqlManagers/ManagerImpl.php');
require_once ('mySqlManagers/KeyConstants.php');
require_once ('mySqlManagers/DocumentDTO.php');
require_once ('mySqlManagers/DocumentCreator.php');
$doc_conn = new ManagerImpl(KeyConstants::DOCUMENT_SIGNATURE);
$doc_creator = new DocumentCreator();
if (isset($_POST["id"]) && $_POST["id"]
    && isset($_POST["title"]) && $_POST["title"]
        && isset($_POST["page-number"]) && $_POST["page-number"]
            && isset($_POST["type"]) && $_POST["type"]
                && isset($_POST["format"]) && $_POST["format"]
                    && isset($_POST["author"]) && $_POST["author"]) {
    $entity = new DocumentDTO($_POST["id"],$_POST["title"],$_POST["page-number"],$_POST["type"],$_POST["format"],$_POST["author"]);
    $doc_conn->create($entity);
}
if((isset($_GET['fun']) && $_GET['fun']) && $_GET['fun']=="'getDocs'"){
    $arr = $doc_conn->read();
    echo $_GET['fun'].";";
    foreach ($arr as $el){
         echo $doc_creator->create($el).";\n";
    }
}
if((isset($_GET['fun']) && $_GET['fun']) && $_GET['fun']=="'deleteDoc'"){
    $doc_conn->delete($_GET['id']);
}

