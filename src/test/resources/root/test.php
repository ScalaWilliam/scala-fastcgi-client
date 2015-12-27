<?php
header("X-Test: Test");
if ( $_SERVER['REQUEST_METHOD'] == 'POST') {
    echo "a=".($_POST['a'] + 1);
    echo "&b=".($_POST['b'] + 1);
} else {
    echo "Received";
}