
<?php
require_once('managers/ManagerImpl.php');
require_once('managers/KeyConstants.php');
require_once('managers/DocumentDTO.php');
require_once('managers/DocumentCreator.php');
$doc_conn = new ManagerImpl(KeyConstants::DOCUMENT_SIGNATURE);
$doc_creator = new DocumentCreator();
if (isset($_POST["id"]) && $_POST["id"]
    && isset($_POST["title"]) && $_POST["title"]
        && isset($_POST["page-number"]) && $_POST["page-number"]
            && isset($_POST["type"]) && $_POST["type"]
                && isset($_POST["format"]) && $_POST["format"]
                    && isset($_POST["author"]) && $_POST["author"]) {
    $entity = new DocumentDTO($_POST["id"],$_POST["title"],$_POST["page-number"],$_POST["type"],$_POST["format"],$_POST["author"]);
    if($doc_conn->isStored($entity) > 0){
        $doc_conn->update($entity);
    }else {
        $doc_conn->create($entity);
    }
}
if((isset($_GET['fun']) && $_GET['fun']) && $_GET['fun']=="'getDocs'"){
    $arr = $doc_conn->read();
    foreach ($arr as $el){
         echo $doc_creator->create($el).";\n";
    }
}
if((isset($_GET['fun']) && $_GET['fun']) && $_GET['fun']=="'filterFormat'"){
    $arr = $doc_conn->getByFormat($_GET['format']);
    foreach ($arr as $el){
        echo $doc_creator->create($el).";\n";
    }
}
if((isset($_GET['fun']) && $_GET['fun']) && $_GET['fun']=="'filterType'"){
    $arr = $doc_conn->getByType($_GET['type']);
    foreach ($arr as $el){
        echo $doc_creator->create($el).";\n";
    }
}
if((isset($_GET['fun']) && $_GET['fun']) && $_GET['fun']=="'deleteDoc'"){
    $doc_conn->delete($_GET['id']);
}

