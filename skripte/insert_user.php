<?php
	include_once("connection.php");

	if(isset($_POST['USERNAME']) && isset($_POST['PASSWORD']) && isset($_POST['EMAIL']) && isset($_POST['BIRTHDAY'])){
		$username = $_POST['USERNAME'];
		$password = $_POST['PASSWORD'];
		$email = $_POST['EMAIL'];
		$birthday = $_POST['BIRTHDAY'];

		$query = "INSERT INTO `users` ( `user_name`, `password`, `email`, `birthday`, `is_admin`) VALUES ('$username', '$password', '$email', '$birthday', '0')";

		$result = mysqli_query($conn, $query);

		if($result > 0){
			//echo "Insert Successfully";
			echo  $conn->insert_id;
		}else{
			echo "Error";
		}
	}
?>
